package br.com.gustavobarbosa.hidrat_ai.domain;

import br.com.gustavobarbosa.hidrat_ai.dto.enums.FormaPagamento;
import br.com.gustavobarbosa.hidrat_ai.dto.enums.StatusPagamento;
import br.com.gustavobarbosa.hidrat_ai.dto.enums.StatusPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Positive
    @Builder.Default
    @Column(name = "valor_subtotal", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorSubtotal = BigDecimal.ZERO;

    @PositiveOrZero
    @Builder.Default
    @Column(name = "valor_desconto", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorDesconto = BigDecimal.ZERO;

    @PositiveOrZero
    @Builder.Default
    @Column(name = "valor_entrega", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorEntrega = BigDecimal.ZERO;

    @Positive
    @Builder.Default
    @Column(name = "valor_total", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Column(name = "status_pedido", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @Column(name = "status_pagamento", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @Column(name = "forma_pagamento", nullable = false)
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrega_id", nullable = false)
    private Entrega entrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estabelecimento_id", nullable = false)
    private Estabelecimento estabelecimento;

    @Builder.Default
    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItemPedido> itens = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @Builder.Default
    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean ativo = true;

    @Column(name = "desativado_em")
    private LocalDateTime desativadoEm;

    public void adicionarItemPedido(ItemPedido itemPedido) {
        itens.add(Objects.requireNonNull(itemPedido));
        itemPedido.setPedido(this);
        recalcularValores();
    }

    public void removerItemPedido(ItemPedido itemPedido) {
        if (itens.remove(itemPedido)) {
            itemPedido.setPedido(null);
            recalcularValores();
        }
    }

    public void aplicarDesconto(BigDecimal desconto) {
        BigDecimal novoDesconto = desconto != null ? desconto : BigDecimal.ZERO;

        if (novoDesconto.signum() < 0) {
            throw new IllegalArgumentException("O desconto não pode ser negativo.");
        }

        if (novoDesconto.compareTo(valorSubtotal.add(valorEntrega)) > 0) {
            throw new IllegalArgumentException(
                    "O desconto não pode ser maior que o valor do pedido."
            );
        }

        valorDesconto = novoDesconto;
        recalcularValores();
    }

    public void aplicarEntrega(BigDecimal entrega) {
        valorEntrega = entrega != null ? entrega : BigDecimal.ZERO;

        if (entrega != null && entrega.signum() < 0) {
            throw new IllegalArgumentException("A entrega não pode ser negativa.");
        }

        recalcularValores();
    }

    private void recalcularValores() {
        valorSubtotal = itens
                .stream()
                .map(ItemPedido::calcularValorSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        valorTotal = valorSubtotal
                .add(valorEntrega)
                .subtract(valorDesconto);
    }
}

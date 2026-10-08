package br.com.gustavobarbosa.hidrat_ai.domain;

import br.com.gustavobarbosa.hidrat_ai.dto.enums.TipoVenda;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "valor_unitario", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorUnitario;

    @Column(name = "tipo_venda", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoVenda tipoVenda;

    @Positive
    @Column(nullable = false)
    private int quantidade;

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

    public BigDecimal calcularValorSubtotal() {
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}

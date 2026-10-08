CREATE TABLE pedidos (
    id UUID PRIMARY KEY,
    valor_subtotal NUMERIC(19, 2) NOT NULL,
    valor_desconto NUMERIC(19, 2) NOT NULL DEFAULT 0.00,
    valor_entrega NUMERIC(19, 2) NOT NULL DEFAULT 0.00,
    valor_total NUMERIC(19, 2) NOT NULL,
    status_pedido VARCHAR(255) NOT NULL,
    status_pagamento VARCHAR(255) NOT NULL,
    forma_pagamento VARCHAR(255) NOT NULL,
    data_pedido TIMESTAMP NOT NULL,
    observacoes TEXT,
    entrega_id UUID NOT NULL,
    cliente_id UUID NOT NULL,
    estabelecimento_id UUID NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    desativado_em TIMESTAMP,

    CONSTRAINT fk_pedidos_entrega
        FOREIGN KEY (entrega_id) REFERENCES entrega(id),
    CONSTRAINT fk_pedidos_cliente
        FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    CONSTRAINT fk_pedidos_estabelecimento
        FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimentos(id),
    CONSTRAINT uk_pedidos_entrega UNIQUE (entrega_id),
    CONSTRAINT ck_pedidos_valor_subtotal
        CHECK (valor_subtotal > 0),
    CONSTRAINT ck_pedidos_valor_desconto
        CHECK (valor_desconto >= 0),
    CONSTRAINT ck_pedidos_valor_entrega
        CHECK (valor_entrega >= 0),
    CONSTRAINT ck_pedidos_valor_total
        CHECK (valor_total > 0),
    CONSTRAINT ck_pedidos_desconto_limite
        CHECK (valor_desconto <= valor_subtotal + valor_entrega),
    CONSTRAINT ck_pedidos_calculo_total
        CHECK (valor_total = valor_subtotal + valor_entrega - valor_desconto),
    CONSTRAINT ck_pedidos_status
        CHECK (status_pedido IN ('REALIZADO', 'ENTREGUE', 'CANCELADO')),
    CONSTRAINT ck_pedidos_status_pagamento
        CHECK (status_pagamento IN ('NAO_EFETIVADO', 'EFETIVADO')),
    CONSTRAINT ck_pedidos_forma_pagamento
        CHECK (forma_pagamento IN ('DINHEIRO', 'CARTAO', 'PIX'))
);

CREATE TABLE itens_pedido (
    id UUID PRIMARY KEY,
    pedido_id UUID NOT NULL,
    produto_id UUID NOT NULL,
    valor_unitario NUMERIC(19, 2) NOT NULL,
    tipo_venda VARCHAR(255) NOT NULL,
    quantidade INTEGER NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    desativado_em TIMESTAMP,

    CONSTRAINT fk_itens_pedido_pedido
        FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    CONSTRAINT fk_itens_pedido_produto
        FOREIGN KEY (produto_id) REFERENCES produtos(id),
    CONSTRAINT ck_itens_pedido_valor_unitario
        CHECK (valor_unitario > 0),
    CONSTRAINT ck_itens_pedido_quantidade
        CHECK (quantidade > 0),
    CONSTRAINT ck_itens_pedido_tipo_venda
        CHECK (tipo_venda IN ('RECARGA', 'COM_VASILHAME'))
);

CREATE INDEX idx_pedidos_cliente_id
    ON pedidos (cliente_id);

CREATE INDEX idx_pedidos_estabelecimento_id
    ON pedidos (estabelecimento_id);

CREATE INDEX idx_itens_pedido_pedido_id
    ON itens_pedido (pedido_id);

CREATE INDEX idx_itens_pedido_produto_id
    ON itens_pedido (produto_id);

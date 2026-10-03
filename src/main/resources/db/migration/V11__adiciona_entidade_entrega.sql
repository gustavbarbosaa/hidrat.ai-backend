CREATE TABLE entrega (
    id UUID PRIMARY KEY,
    valor NUMERIC(19, 2) NOT NULL,
    estabelecimento_id UUID NOT NULL,
    rua VARCHAR(255),
    numero VARCHAR(20),
    bairro VARCHAR(150),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    desativado_em TIMESTAMP,
    CONSTRAINT fk_entrega_estabelecimento FOREIGN KEY (estabelecimento_id)
    REFERENCES estabelecimentos(id)
);

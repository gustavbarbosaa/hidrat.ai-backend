CREATE TABLE produtos (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    marca VARCHAR(100),
    valor_recarga NUMERIC(19, 2) NOT NULL,
    valor_com_vasilhame NUMERIC(19, 2) NOT NULL,
    validade TIMESTAMP,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    desativado_em TIMESTAMP
)
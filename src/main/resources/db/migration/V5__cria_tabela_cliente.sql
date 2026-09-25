CREATE TABLE clientes (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    apelido VARCHAR(100),
    contato VARCHAR(14),
    rua VARCHAR(255),
    numero VARCHAR(20),
    bairro VARCHAR(150)
)
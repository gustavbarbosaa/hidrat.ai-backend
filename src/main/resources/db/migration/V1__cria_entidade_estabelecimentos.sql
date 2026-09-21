CREATE TABLE estabelecimentos (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    apelido VARCHAR(100),
    cpf_cnpj VARCHAR(14) UNIQUE NOT NULL
)
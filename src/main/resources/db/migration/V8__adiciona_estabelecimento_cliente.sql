ALTER TABLE clientes
    ADD COLUMN estabelecimento_id UUID,
    ADD CONSTRAINT fk_clientes_estabelecimento
    FOREIGN KEY (estabelecimento_id)
    REFERENCES estabelecimentos(id);
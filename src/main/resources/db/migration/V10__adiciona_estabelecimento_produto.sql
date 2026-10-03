ALTER TABLE produtos
    ADD COLUMN estabelecimento_id UUID,
    ADD CONSTRAINT fk_produtos_estabelecimento
    FOREIGN KEY (estabelecimento_id)
    REFERENCES estabelecimentos(id);
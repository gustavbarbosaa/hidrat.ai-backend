package br.com.gustavobarbosa.hidrat_ai.dto;

import br.com.gustavobarbosa.hidrat_ai.domain.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ClienteDTO(
    UUID id,
    @NotBlank(message = "O campo nome é obrigatório.")
    @Size(max = 100, message = "O campo nome deve ter no máximo 100 caracteres.")
    String nome,
    @Size(max = 100, message = "O campo apelido deve ter no máximo 100 caracteres.")
    String apelido,
    @Size(max = 14, message = "O campo contato deve ter no máximo 14 caracteres.")
    String contato,
    Endereco endereco
) { }

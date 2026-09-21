package br.com.gustavobarbosa.hidrat_ai.dto.request;

import br.com.gustavobarbosa.hidrat_ai.validation.CpfOuCnpj;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstabelecimentoRequest(
        @NotBlank(message = "O campo nome é obrigatório.")
        @Size(max = 100, message = "O campo nome deve ter no máximo 100 caracteres.")
        String nome,
        @NotBlank(message = "O campo apelido é obrigatório.")
        @Size(max = 100, message = "O campo apelido deve ter no máximo 100 caracteres.")
        String apelido,
        @NotBlank(message = "O campo email é obrigatório.")
        @Size(max = 100, message = "O campo email deve ter no máximo 100 caracteres.")
        @Email(message = "O e-mail informado é inválido.")
        String email,
        @NotBlank(message = "O campo senha é obrigatório.")
        @Size(min = 8, max = 100, message = "A senha deve ter entre 8 a 100 caracteres.")
        String senha,
        @NotBlank(message = "O campo CPF/CNPJ é obrigatório.")
        @CpfOuCnpj
        String cpfCnpj
) { }

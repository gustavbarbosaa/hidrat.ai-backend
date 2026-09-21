package br.com.gustavobarbosa.hidrat_ai.dto.request;

import br.com.gustavobarbosa.hidrat_ai.validation.CpfOuCnpj;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record EstabelecimentoEdicaoRequest(
        @Size(max = 100, message = "O campo nome deve ter no máximo 100 caracteres.")
        String nome,
        @Size(max = 100, message = "O campo apelido deve ter no máximo 100 caracteres.")
        String apelido,
        @Size(max = 100, message = "O campo email deve ter no máximo 100 caracteres.")
        @Email(message = "O e-mail informado é inválido.")
        String email,
        @CpfOuCnpj
        String cpfCnpj
) { }

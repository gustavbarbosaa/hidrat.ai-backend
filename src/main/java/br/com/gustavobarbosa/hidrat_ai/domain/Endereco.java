package br.com.gustavobarbosa.hidrat_ai.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Endereco {
    private String rua;
    private String numero;
    private String bairro;
    private String pontoReferencia;
}

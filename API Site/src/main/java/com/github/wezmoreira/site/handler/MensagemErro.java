package com.github.wezmoreira.site.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MensagemErro {
    private String mensagem;
    private List<String> validaErros;

    public MensagemErro(List<String> validaErros) {
        this.validaErros = validaErros;
    }

    public MensagemErro(String mensagem) {
        this.mensagem = mensagem;
    }
}

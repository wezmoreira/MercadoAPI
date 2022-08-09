package com.github.wezmoreira.pagamento.service;

import com.github.wezmoreira.pagamento.consumer.model.RecebeDadosDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.request.RequestCartaoDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.request.RequestClienteDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.request.RequestPagamentoBancoDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.request.RequestToken;
import com.github.wezmoreira.pagamento.dto.pagamento.response.ResponsePagamentoBancoDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.response.ResponseToken;
import com.github.wezmoreira.pagamento.util.Criptografa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class ServicePagamento {
    @Autowired
    WebClient.Builder webClient;
    @Autowired
    WebClient web;

    @Autowired
    Criptografa criptografa;

    private final String bancoAprovacao = "https://pb-getway-payment.herokuapp.com/v1/payments/credit-card";
    private final String autenticar = "https://pb-getway-payment.herokuapp.com/v1/auth";


    public ResponseToken autenticacao() {
        RequestToken wesleyToken = RequestToken.builder().client_id("client_id_wesley")
                .api_key("95801864-8009-41f4-a9d3-a602fbca4466").build();
        var retorno = webClient.build()
                .post().uri(autenticar).bodyValue(wesleyToken)
                .retrieve().bodyToMono(ResponseToken.class).block();

        log.info("O valor do Token aqui no método AUTENTICACAO é : " + retorno);

        return retorno;
    }

    public ResponsePagamentoBancoDTO pagamentoBanco(RecebeDadosDTO mensagemPedidoDTO){  //mudei de response pra request
        String autenticador = autenticacao().getAccess_token();

        RequestPagamentoBancoDTO requestPagamentoBancoDTO = RequestPagamentoBancoDTO.builder()
                .seller_id("b2c3119f-e7f9-46ce-9531-9213e5dc5cb1")
                .customer(RequestClienteDTO.builder()
                        .document_type("CPF")
                        .document_number(mensagemPedidoDTO.getCpf()).build())
                .payment_type("CREDIT_CARD")
                .currency("BRL")
                .transaction_amount(mensagemPedidoDTO.getTotal())
                .card(RequestCartaoDTO.builder()
                        .number_token(criptografa.encriptografar(mensagemPedidoDTO.getPagamento().getNumero_cartao()))
                        .cardholder_name(mensagemPedidoDTO.getPagamento().getNome_cartao())
                        .security_code(mensagemPedidoDTO.getPagamento().getCodigo_seguranca())
                        .brand(mensagemPedidoDTO.getPagamento().getMarca())
                        .expiration_month(mensagemPedidoDTO.getPagamento().getMes_expiracao())
                        .expiration_year(mensagemPedidoDTO.getPagamento().getAno_expiracao())
                        .build()).build();

        log.info("O valor do TOKEN DO CARTAO é do banco é: " + requestPagamentoBancoDTO.getCard().getNumber_token());


        var retorno = web.post()
                .uri(bancoAprovacao)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(autenticador))
                .bodyValue(requestPagamentoBancoDTO)
                .retrieve()
                .bodyToMono(ResponsePagamentoBancoDTO.class)
                .block();

            log.info("O valor do RETORNO do banco é: " + retorno);
            return retorno;
    }
}

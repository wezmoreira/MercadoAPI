package com.github.wezmoreira.pagamento.dto.pagamento.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePagamentoBancoDTO {

    private String payment_id;
    private String seller_id;
    private Double transaction_amount;
    private String currency;
    private String status;
    private LocalDateTime received_at;  //mudei de localdatetime pra string
    private ResponseAutorizacaoDTO authorization;

}

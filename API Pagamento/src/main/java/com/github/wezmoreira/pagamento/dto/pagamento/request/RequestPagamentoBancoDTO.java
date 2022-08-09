package com.github.wezmoreira.pagamento.dto.pagamento.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPagamentoBancoDTO {

    @NotBlank
    private String seller_id;
    @NotNull
    private RequestClienteDTO customer;
    @NotBlank
    private String payment_type;
    @NotBlank
    private String currency;
    private double transaction_amount;
    @NotBlank
    private RequestCartaoDTO card;
}

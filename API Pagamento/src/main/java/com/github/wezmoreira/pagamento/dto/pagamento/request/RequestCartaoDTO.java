package com.github.wezmoreira.pagamento.dto.pagamento.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCartaoDTO {
    @NotBlank
    private String number_token;
    @NotBlank
    private String cardholder_name;
    @NotBlank
    private String security_code;
    @NotBlank
    private String brand;
    @NotBlank
    private String expiration_month;
    @NotBlank
    private String expiration_year;
}

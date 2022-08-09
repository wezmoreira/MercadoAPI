package com.github.wezmoreira.pagamento.dto.pagamento.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCartaoDTO {
    private String number_token;
    private String cardholder_name;
    private String security_code;
    private String brand;
    private String expiration_month;
    private String expiration_year;
}

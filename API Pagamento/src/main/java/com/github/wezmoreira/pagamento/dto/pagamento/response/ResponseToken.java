package com.github.wezmoreira.pagamento.dto.pagamento.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseToken {

    private String access_token;
    private String token_type;
    private String expires_in;
}

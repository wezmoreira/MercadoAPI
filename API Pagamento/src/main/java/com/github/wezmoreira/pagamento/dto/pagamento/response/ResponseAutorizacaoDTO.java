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
public class ResponseAutorizacaoDTO {

    private String authorization_code;
    private String authorized_at;
    private String reason_code;  //mudei de string
    private String reason_message;
}

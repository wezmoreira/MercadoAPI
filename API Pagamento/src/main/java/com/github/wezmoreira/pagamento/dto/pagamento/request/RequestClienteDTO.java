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
public class RequestClienteDTO {
    @NotBlank
    private String document_type;
    @NotBlank
    private String document_number;
}

package com.github.wezmoreira.site.dto.checkout.request;

import com.github.wezmoreira.site.entities.Cliente;
import com.github.wezmoreira.site.entities.Items;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCheckoutClienteDTO {
    private String clienteId;
    private String cartaoId;
}

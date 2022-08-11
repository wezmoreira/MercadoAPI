package com.github.wezmoreira.site.dto.request;

import com.github.wezmoreira.site.entities.Cliente;
import com.github.wezmoreira.site.entities.ClienteCartoes;
import com.github.wezmoreira.site.entities.Items;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCheckoutTO {
    @OneToMany(cascade = CascadeType.ALL)
    List<Items> itens;
    @OneToOne(cascade = CascadeType.ALL)
    Cliente cliente_info;
}

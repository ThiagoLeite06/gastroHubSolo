package com.thiagoleite.GastroHubSolo.entrypoint.dtos;

import com.thiagoleite.GastroHubSolo.core.domain.UserDomain;
import com.thiagoleite.GastroHubSolo.core.domain.enums.KitchenTypes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RestaurantDTO {
    @NotBlank(message = "O nome é obrigatório")
    @Length(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String name;

    @NotBlank(message = "O endereço é obrigatório")
    @Length(max = 200, message = "O endereço não pode ter mais que 200 caracteres")
    private String address;

    @NotNull(message = "O tipo de cozinha é obrigatório")
    private KitchenTypes kitchenType;

    @NotBlank(message = "O horário de funcionamento é obrigatório")
    private String openingHours;

    @NotNull(message = "O dono é obrigatório")
    private UserDomain owner;
}

package com.thiagoleite.GastroHubSolo.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateMenuItemInput {
    @NotBlank(message = "O nome é obrigatório")
    @Length(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;

    @NotBlank(message = "A descrição é obrigatória")
    @Length(max = 500, message = "A descrição não pode ter mais que 500 caracteres")
    private String description;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser um valor positivo")
    private BigDecimal price;

    private boolean availableOnlyInRestaurant;

    private String photoPath;

    @NotNull(message = "O ID do restaurante é obrigatório")
    private Long restaurantId;
}
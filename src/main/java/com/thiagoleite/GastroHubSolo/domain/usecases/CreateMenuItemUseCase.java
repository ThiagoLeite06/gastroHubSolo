package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.CreateMenuItemInput;
import com.thiagoleite.GastroHubSolo.application.dtos.MenuItemOutput;

public interface CreateMenuItemUseCase {
    MenuItemOutput execute(CreateMenuItemInput input);
}

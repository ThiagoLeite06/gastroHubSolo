package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.MenuItemOutput;
import com.thiagoleite.GastroHubSolo.application.dtos.UpdateMenuItemInput;

public interface UpdateMenuItemUseCase {
    MenuItemOutput execute(Long id, UpdateMenuItemInput input);
}

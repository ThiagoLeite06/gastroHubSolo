package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.UpdateUserInput;
import com.thiagoleite.GastroHubSolo.application.dtos.UserOutput;

public interface UpdateUserUseCase {
    UserOutput execute(Long id, UpdateUserInput input);
}

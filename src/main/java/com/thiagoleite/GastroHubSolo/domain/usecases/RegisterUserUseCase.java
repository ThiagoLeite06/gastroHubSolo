package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.AuthOutput;
import com.thiagoleite.GastroHubSolo.application.dtos.UserInput;

public interface RegisterUserUseCase {
    AuthOutput execute(UserInput userInput);
}

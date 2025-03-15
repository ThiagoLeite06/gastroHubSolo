package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.UserType;

public interface CreateUserTypeUseCase {
    UserType execute(UserType userType);
}

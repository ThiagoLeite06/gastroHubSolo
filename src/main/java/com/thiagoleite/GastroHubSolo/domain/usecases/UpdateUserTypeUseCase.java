package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.UserType;

public interface UpdateUserTypeUseCase {
    UserType execute(Long id, UserType userType);
}

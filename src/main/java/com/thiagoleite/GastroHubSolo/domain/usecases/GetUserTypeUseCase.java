package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.UserType;

import java.util.List;

public interface GetUserTypeUseCase {
    UserType getById(Long id);
    List<UserType> getAll();
}

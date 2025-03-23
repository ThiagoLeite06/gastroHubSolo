package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.UserOutput;

import java.util.List;

public interface GetUserUseCase {
    UserOutput getById(Long id);
    List<UserOutput> getAll();
}

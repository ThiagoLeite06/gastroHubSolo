package com.thiagoleite.GastroHubSolo.core.usecase;

import com.thiagoleite.GastroHubSolo.core.domain.UserDomain;

import java.util.List;

public interface GetUserUseCase {
    UserDomain getById(Long id);
    List<UserDomain> getAll();
}

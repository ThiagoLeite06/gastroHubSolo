package com.thiagoleite.GastroHubSolo.core.usecase;

import com.thiagoleite.GastroHubSolo.core.domain.UserTypeDomain;

import java.util.List;

public interface GetUserTypeUseCase {
    UserTypeDomain getById(Long id);
    List<UserTypeDomain> getAll();
}

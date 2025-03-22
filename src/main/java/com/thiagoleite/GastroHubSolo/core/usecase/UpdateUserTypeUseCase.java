package com.thiagoleite.GastroHubSolo.core.usecase;

import com.thiagoleite.GastroHubSolo.core.domain.UserTypeDomain;

public interface UpdateUserTypeUseCase {
    UserTypeDomain execute(Long id, UserTypeDomain userTypeDomain);
}

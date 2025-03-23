package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.application.exceptions.AuthenticationException;

public interface ChangePasswordUseCase {
    void execute(Long userId, String currentPassword, String newPassword) throws AuthenticationException;
}

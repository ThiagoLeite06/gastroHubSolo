package com.thiagoleite.GastroHubSolo.core.usecase;

import com.thiagoleite.GastroHubSolo.core.exceptions.AuthenticationException;

public interface ChangePasswordUseCase {
    void execute(Long userId, String currentPassword, String newPassword) throws AuthenticationException;
}

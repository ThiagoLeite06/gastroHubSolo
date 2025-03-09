package com.thiagoleite.GastroHubSolo.domain.usecases;

import javax.naming.AuthenticationException;

public interface ChangePasswordUseCase {
    void execute(Long userId, String currentPassword, String newPassword) throws AuthenticationException;
}

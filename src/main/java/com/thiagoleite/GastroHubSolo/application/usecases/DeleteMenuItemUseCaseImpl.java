package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.repositories.MenuItemRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.DeleteMenuItemUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeleteMenuItemUseCaseImpl implements DeleteMenuItemUseCase {
    private final MenuItemRepository menuItemRepository;

    public DeleteMenuItemUseCaseImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public void execute(Long id) {
        if (!menuItemRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Item de cardápio não encontrado");
        }
        menuItemRepository.deleteById(id);
    }
}

//package com.thiagoleite.GastroHubSolo.application.usecases;
//
//import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
//import com.thiagoleite.GastroHubSolo.domain.usecases.DeleteUserUseCase;
//import org.springframework.stereotype.Service;
//
//@Service
//public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
//    private final UserRepository userRepository;
//
//    public DeleteUserUseCaseImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public void execute(Long id) {
//        if (userRepository.findById(id).isEmpty()) {
//            throw new RuntimeException("User not found");
//        }
//        userRepository.deleteById(id);
//    }
//}

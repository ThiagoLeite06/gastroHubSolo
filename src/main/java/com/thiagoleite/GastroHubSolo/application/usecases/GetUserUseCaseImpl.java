//package com.thiagoleite.GastroHubSolo.application.usecases;
//
//import com.thiagoleite.GastroHubSolo.application.dtos.UserOutput;
//import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
//import com.thiagoleite.GastroHubSolo.domain.entities.User;
//import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
//import com.thiagoleite.GastroHubSolo.domain.usecases.GetUserUseCase;
//
//import com.thiagoleite.GastroHubSolo.presentation.mappers.UserMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class GetUserUseCaseImpl implements GetUserUseCase {
//    private final UserRepository userRepository;
//    private final UserMapper userMapper;
//
//    public GetUserUseCaseImpl(UserRepository userRepository, UserMapper userMapper) {
//        this.userRepository = userRepository;
//        this.userMapper = userMapper;
//    }
//
//    @Override
//    public UserOutput getById(Long id) {
////        User user = userRepository.findById(id)
////                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
////        return userMapper.toOutput(user);
//    }
//
//    @Override
//    public List<UserOutput> getAll() {
////        List<User> users = userRepository.findAll();
////        return users.stream()
////                .map(userMapper::toOutput)
////                .collect(Collectors.toList());
//    }
//}

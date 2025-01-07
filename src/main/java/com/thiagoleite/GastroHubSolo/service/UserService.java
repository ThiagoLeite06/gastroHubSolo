package com.thiagoleite.GastroHubSolo.service;

import com.thiagoleite.GastroHubSolo.config.UserMapper;
import com.thiagoleite.GastroHubSolo.dto.UserRequestDto;
import com.thiagoleite.GastroHubSolo.dto.UserResponseDto;
import com.thiagoleite.GastroHubSolo.model.User;
import com.thiagoleite.GastroHubSolo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<UserResponseDto> getAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toDto);
    }

    public User create(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
}

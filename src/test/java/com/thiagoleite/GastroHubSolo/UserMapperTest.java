package com.thiagoleite.GastroHubSolo;

import com.thiagoleite.GastroHubSolo.config.UserMapper;
import com.thiagoleite.GastroHubSolo.dto.UserResponseDto;
import com.thiagoleite.GastroHubSolo.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testToDto() {
        User user = new User();
        user.setName("Test Name");
        user.setEmail("test@email.com");
        user.setLogin("testLogin");
        user.setAddress("Test Address");

        UserResponseDto dto = userMapper.toDto(user);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals("Test Name", dto.getName());
        Assertions.assertEquals("test@email.com", dto.getEmail());
        Assertions.assertEquals("testLogin", dto.getLogin());
        Assertions.assertEquals("Test Address", dto.getAddress());
    }
}

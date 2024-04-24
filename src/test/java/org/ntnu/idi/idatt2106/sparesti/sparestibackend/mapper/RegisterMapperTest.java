package org.ntnu.idi.idatt2106.sparesti.sparestibackend.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.ntnu.idi.idatt2106.sparesti.sparestibackend.dto.token.LoginRegisterResponse;
import org.ntnu.idi.idatt2106.sparesti.sparestibackend.dto.user.RegisterRequest;
import org.ntnu.idi.idatt2106.sparesti.sparestibackend.model.User;
import org.ntnu.idi.idatt2106.sparesti.sparestibackend.model.enums.Role;

public class RegisterMapperTest {

    @Test
    public void toEntity() {
        RegisterRequest request =
                new RegisterRequest(
                        "firstName", "lastName", "username", "password", "testEmail@mail.com");

        User user = RegisterMapper.INSTANCE.toEntity(request, Role.USER, "encodedPassword");

        assertEquals(request.firstName(), user.getFirstName());
        assertEquals(request.lastName(), user.getLastName());
        assertEquals(request.username(), user.getUsername());
        assertNotEquals(request.password(), user.getPassword());
        assertEquals(request.email(), user.getEmail());
        assertEquals(0, Role.USER.compareTo(user.getUserConfig().getRole()));
        assertNull(user.getUserConfig().getChallengeConfig());
    }

    @Test
    public void toLoginRegisterResponse() {
        User user = User.builder().id(1L).build();
        String accessToken = "accessToken";
        String refreshToken = "refreshToken";
        LoginRegisterResponse response =
                RegisterMapper.INSTANCE.toDTO(user, accessToken, refreshToken);

        assertEquals(accessToken, response.accessToken());
        assertEquals(refreshToken, response.refreshToken());
    }
}

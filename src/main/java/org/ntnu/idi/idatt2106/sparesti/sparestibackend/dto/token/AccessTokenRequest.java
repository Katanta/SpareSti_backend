package org.ntnu.idi.idatt2106.sparesti.sparestibackend.dto.token;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO used when requesting for renewing access token
 *
 * @author Harry L.X & Lars M.L.N
 * @version 1.0
 * @since 17.4.24
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccessTokenRequest {
    @NotNull @NotBlank @NotEmpty private String refreshToken;
}

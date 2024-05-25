package med.voll.api.application.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record SignInDto(
        @NotBlank
        String login,
        @NotBlank
        String password
) {
}

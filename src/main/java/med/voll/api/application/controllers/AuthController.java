package med.voll.api.application.controllers;

import jakarta.validation.Valid;
import med.voll.api.application.dtos.auth.SignInDto;
import med.voll.api.application.dtos.auth.TokenDto;
import med.voll.api.application.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("sign-in")
    public ResponseEntity<TokenDto> signIn(@RequestBody @Valid SignInDto dto) {
        return ResponseEntity.ok(authService.signIn(dto));
    }
}

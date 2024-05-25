package med.voll.api.application.services;

import med.voll.api.application.dtos.auth.SignInDto;
import med.voll.api.application.dtos.auth.TokenDto;
import med.voll.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public TokenDto signIn(SignInDto dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var authentication = authenticationManager.authenticate(token);
        return new TokenDto(tokenService.generate((User) authentication.getPrincipal()));
    }
}

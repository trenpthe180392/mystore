package mo.project.sellbook.service;

import lombok.RequiredArgsConstructor;
import mo.project.sellbook.dto.request.LoginRequest;
import mo.project.sellbook.dto.response.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login(LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                );

        authenticationManager.authenticate(authenticationToken);

        return new LoginResponse(
                "AccessToken1243",
                "RefreshToken1243"
        );
    }
}


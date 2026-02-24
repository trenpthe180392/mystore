package mo.project.sellbook.service;

import lombok.RequiredArgsConstructor;
import mo.project.sellbook.dto.request.LoginRequest;
import mo.project.sellbook.dto.response.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //Tra ve token
        return LoginResponse.builder()
                .accessToken("AccessToken123")
                .refreshToken("RefreshToken123")
                .build();
    }
}


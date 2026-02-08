package mo.project.sellbook.controller;

import lombok.RequiredArgsConstructor;
import mo.project.sellbook.dto.request.LoginRequest;
import mo.project.sellbook.dto.response.LoginResponse;
import mo.project.sellbook.dto.response.RegisterResponse;
import mo.project.sellbook.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }
}

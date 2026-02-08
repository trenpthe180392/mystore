package mo.project.sellbook.controller;

import mo.project.sellbook.dto.request.RegisterRequest;
import mo.project.sellbook.dto.response.RegisterResponse;
import mo.project.sellbook.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/create")
    public RegisterResponse create(@RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }
}

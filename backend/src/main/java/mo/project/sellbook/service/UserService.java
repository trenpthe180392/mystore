package mo.project.sellbook.service;

import mo.project.sellbook.dto.request.RegisterRequest;
import mo.project.sellbook.dto.response.RegisterResponse;
import mo.project.sellbook.mapper.UserMapper;
import mo.project.sellbook.model.UserRole;
import mo.project.sellbook.model.Users;
import mo.project.sellbook.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final UserMapper userMap;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepo,
            UserMapper userMap,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepo = userRepo;
        this.userMap = userMap;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponse register(RegisterRequest registerRequest) {

        if (userRepo.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        Users user = userMap.toEntity(registerRequest);
        user.setPasswordHash(
                passwordEncoder.encode(registerRequest.getPassword())
        );
        user.setRole(UserRole.USER);
        user.setActive(true);
        Users savedUser = userRepo.save(user);
        return userMap.toResponse(savedUser);
    }
}


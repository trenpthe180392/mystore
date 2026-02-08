package mo.project.sellbook.service;

import mo.project.sellbook.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceCustomizer implements UserDetailsService {
    private UserRepository userRepository;
    public UserDetailServiceCustomizer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}

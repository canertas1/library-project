package library.demo.library.service;
import library.demo.library.config.JwtService;
import library.demo.library.dto.AuthenticationRequest;
import library.demo.library.dto.AuthenticationResponse;
import library.demo.library.dto.RegisterRequest;
import library.demo.library.entity.Role;
import library.demo.library.entity.User;
import library.demo.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest registerRequest) {



        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setRole(Role.USER);
        user.setName(registerRequest.getName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        repository.save(user);

        String token = jwtService.generateToken(user);
        AuthenticationResponse auth = new AuthenticationResponse();
        auth.setToken(token);
        return auth;

    }

    public AuthenticationResponse authentication(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        User user = repository.findByEmail(request.getEmail()).orElseThrow();

        String token = jwtService.generateToken(user);
        AuthenticationResponse auth = new AuthenticationResponse();
        auth.setToken(token);
        return auth;

    }
}

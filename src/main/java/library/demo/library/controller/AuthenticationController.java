package library.demo.library.controller;

import library.demo.library.dto.AuthenticationRequest;
import library.demo.library.dto.AuthenticationResponse;
import library.demo.library.dto.RegisterRequest;
import library.demo.library.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){

        return ResponseEntity.ok(authenticationService.register(registerRequest));

    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){

        return ResponseEntity.ok(authenticationService.authentication(request));
    }

    @PostMapping("/confirmEmail")
    public ResponseEntity<String> confirmEmail(String confirmationToken){

        this.authenticationService.confirmMail(confirmationToken);
        return ResponseEntity.ok("The mail confirmed");
    }



}

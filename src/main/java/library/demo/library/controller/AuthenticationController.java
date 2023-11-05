package library.demo.library.controller;

import library.demo.library.dto.AuthenticationRequest;
import library.demo.library.dto.AuthenticationResponse;
import library.demo.library.dto.RegisterRequest;
import library.demo.library.dto.ResetPasswordDto;
import library.demo.library.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<AuthenticationResponse> authenticate( @RequestBody AuthenticationRequest request){

        return ResponseEntity.ok(authenticationService.authentication(request));
    }

    @PostMapping("/confirmEmail/{confirmationToken}")
    public ResponseEntity<String> confirmEmail(@PathVariable  String confirmationToken){

        this.authenticationService.confirmMail(confirmationToken);
        return ResponseEntity.ok("The mail confirmed");
    }

    @PostMapping ("/resetPassword")
    ResponseEntity<String> resetPassword(@RequestParam String email){

        this.authenticationService.resetPassword(email);
        return ResponseEntity.ok("The mail sended your email account");
    }

    @PostMapping("/confirmResetToken")
    public ResponseEntity<String> confirmResetToken(@RequestBody ResetPasswordDto resetPasswordDto){
        this.authenticationService.confirmResetToken(resetPasswordDto);
        return ResponseEntity.ok("The token will be confirmed");
    }



}

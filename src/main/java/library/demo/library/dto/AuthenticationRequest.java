package library.demo.library.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class AuthenticationRequest {

    // not null kullan javax validation


    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
}

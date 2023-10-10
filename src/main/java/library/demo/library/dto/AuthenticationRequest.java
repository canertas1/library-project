package library.demo.library.dto;

import lombok.Data;

@Data

public class AuthenticationRequest {

    private String email;
    private String password;
}

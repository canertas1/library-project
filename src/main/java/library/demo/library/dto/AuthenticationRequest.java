package library.demo.library.dto;

import lombok.Data;

@Data

public class AuthenticationRequest {

    // not null kullan javax validation
    private String email;
    private String password;
}

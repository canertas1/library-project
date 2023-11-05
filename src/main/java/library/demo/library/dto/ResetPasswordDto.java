package library.demo.library.dto;


import lombok.Data;

@Data
public class ResetPasswordDto {

    private String email;
    private String password;
    private String newPassword;
    private String token;

}

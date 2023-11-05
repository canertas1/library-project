package library.demo.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
public class PasswordResetToken {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private static final int EXPIRATION = 60*24;

    private String token;

    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "user_id")
    private User user;


    private Date expiryDate;


    public PasswordResetToken(User user, String token){
        this.user = user;
        this.token= token;
        this.expiryDate=new Date();
    }


}

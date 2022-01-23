package pl.partyka.shopapplication.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.partyka.shopapplication.domain.model.Adress;
import pl.partyka.shopapplication.domain.model.User;
import pl.partyka.shopapplication.domain.model.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

@Data
public class RegistrationForm {
    @NotNull
    @Email
    private String login;
    @ValidPassword
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    private String zipCode;

    public User createUserObject(PasswordEncoder encoder){
        Adress adress = new Adress(country, city, street, zipCode);
        HashSet<UserRole> roles = new HashSet<>();
        roles.add(UserRole.ROLE_USER);
        return User.builder()
                .login(login)
                .password(encoder.encode(password))
                .name(name)
                .lastName(lastName)
                .adress(adress)
                .roles(roles)
                .build();
    }
}

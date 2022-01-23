package pl.partyka.shopapplication.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.partyka.shopapplication.domain.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final PasswordEncoder encoder;
    private final UserService userService;

    @GetMapping
    public String getRegistrationView(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping
    public String registerNewUser(@Valid RegistrationForm registrationForm, Errors errors){
        if(errors.hasErrors()){
            return "register";
        }
        userService.addUser(registrationForm.createUserObject(encoder));
        return "redirect:/login";
    }
}

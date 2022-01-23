package pl.partyka.shopapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.partyka.shopapplication.domain.model.Adress;
import pl.partyka.shopapplication.domain.model.Category;
import pl.partyka.shopapplication.domain.model.User;
import pl.partyka.shopapplication.domain.model.UserRole;
import pl.partyka.shopapplication.domain.service.CategoryService;
import pl.partyka.shopapplication.domain.service.UserService;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Adress adress = new Adress("Polska", "Warszawa", "Administracyjna 10", "01-000");
        HashSet<UserRole> roles = new HashSet<>();
        roles.add(UserRole.ROLE_USER);
        roles.add(UserRole.ROLE_ADMIN);
        User admin = User.builder()
                .login("admin@admin.pl")
                .password(encoder.encode("Admin1234!"))
                .name("Admin")
                .lastName("Admin")
                .adress(adress)
                .roles(roles)
                .build();
        userService.addUser(admin);
        HashSet<UserRole> rolesOnlyUser = new HashSet<>();
        rolesOnlyUser.add(UserRole.ROLE_USER);
        User asdUser = User.builder()
                .login("asd@asd.pl")
                .password(encoder.encode("Asd1234!"))
                .name("Asd")
                .lastName("Asd")
                .adress(adress)
                .roles(rolesOnlyUser)
                .build();
        userService.addUser(asdUser);
        Category category1 = new Category();
        category1.setCategoryName("RTV i AGD");
        Category category2 = new Category();
        category2.setCategoryName("Artykuły spozywcze");
        categoryService.addOrUpdateCategory(category1);
        categoryService.addOrUpdateCategory(category2);
    }
}

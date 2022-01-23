package pl.partyka.shopapplication.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.partyka.shopapplication.domain.model.User;
import pl.partyka.shopapplication.domain.repository.UserRepository;
import pl.partyka.shopapplication.exceptions.UserAlreadyExistsException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User addUser(User user) throws UserAlreadyExistsException{
        List<User> allUsers = userRepository.findAll();
        if(allUsers.contains(user)){
            throw new UserAlreadyExistsException("Użytkownik o podanym loginie już istnieje");
        }
        return userRepository.save(user);
    }
}

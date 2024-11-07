package unused;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shop.backend.Repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository
     userRepository
    ;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userRepository
        .registerUser(user);
    }

    @PostMapping("/login")
    public boolean loginUser(@RequestParam String username, @RequestParam String password) {
        return userRepository
        .loginUser(username, password);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return userRepository
        .updateUser(id, user);
    }
}

package br.com.gabriel.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserModel usermodel)  {
        var user = this.userRepository.findByUsername(usermodel.getUsername());

        if (user != null) {
            System.out.println("Usuário já existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists!");
        }

        var passwordHashed = BCrypt.withDefaults().hashToString(12, usermodel.getPassword().toCharArray());

        usermodel.setPassword(passwordHashed);

        var userCreated = this.userRepository.save(usermodel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

}

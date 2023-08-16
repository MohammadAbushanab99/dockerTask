package example.service1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
public class AuthService {
    @Autowired
    private UserRepository usersRepository;

    @PostMapping("/authentication-service")
    public ResponseEntity<String> authenticate(@RequestBody String requestBody,
                                               @RequestHeader("Authorization") String authorizationHeader) {

        String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
        byte[] decoded = Base64Utils.decodeFromString(base64Credentials);
        String credentials = new String(decoded);
        String[] values = credentials.split(":", 2);
        String username = values[0];
        String password = values[1];
        String user = usersRepository.checkUserInput(username,password);
        if (user != null && user.equals(password)) {
            return ResponseEntity.ok("Received request and credentials");
        }
        return ResponseEntity.ok("");
    }
}

package service4.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {

        String url = "https:/localhost:5001/authentication-service";

        String credentials = username + ":" + password;
        String base64Credentials = Base64Utils.encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + base64Credentials);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);


        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Boolean.class);

        if (response.getBody() != null && response.getBody()) {
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid credentials. Please try again.");
            return "login.html";
        }
    }
}

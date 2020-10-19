package ru.panifidkin.resttemplate.resttemplate.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.panifidkin.resttemplate.resttemplate.model.User;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final String TARGET_URL = "http://91.241.64.178:7081/api/users";

    private final RestTemplate restTemplate;

    @Autowired
    public ApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/users")
    public ResponseEntity<String> getUsers() {
        ResponseEntity<String> get = restTemplate.exchange(TARGET_URL, HttpMethod.GET, null, String.class);

        User user = createUser();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", String.join(";", get.getHeaders().get("Set-Cookie")));

        String postCode = restTemplate.exchange(TARGET_URL, HttpMethod.POST, new HttpEntity<>(user, headers), String.class).getBody();
        String putCode = restTemplate.exchange(TARGET_URL, HttpMethod.PUT, new HttpEntity<>(updateUser(user), headers), String.class).getBody();
        String deleteCode = restTemplate.exchange(TARGET_URL + "/" + user.getId(), HttpMethod.DELETE, new HttpEntity<>(headers), String.class).getBody();

        return ResponseEntity.ok(postCode + putCode + deleteCode);
    }

    private User createUser() {
        return new User(3L, "James", "Brown", Byte.valueOf("38"));
    }

    private User updateUser(User user) {
        user.setName("Thomas");
        user.setLastName("Shelby");
        return user;
    }

}

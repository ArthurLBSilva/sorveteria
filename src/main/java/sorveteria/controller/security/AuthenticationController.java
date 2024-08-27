package sorveteria.controller.security;

import lombok.AllArgsConstructor;
import sorveteria.dto.security.LoginDto;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import sorveteria.service.security.*;


@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final TokenService service;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/token")
    public String getToken(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password())
                );

        return service.generateToken(authentication);
    }
}
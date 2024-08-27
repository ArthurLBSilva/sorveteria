package sorveteria;

import jakarta.annotation.PostConstruct;
import sorveteria.core.security.RsaKeyProperties;
import sorveteria.model.Endereco;
import sorveteria.model.Usuario;
import sorveteria.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class SpringRestWeb2024Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestWeb2024Application.class, args);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @PostConstruct
    public void started() {
        Usuario usuario = new Usuario();
        usuario.setNome("Joao");
        usuario.setAdmin(true);
        usuario.setEmail("admin@example.com");
        usuario.setSenha(encoder.encode("admin"));

        Endereco endereco = new Endereco();
        endereco.setNumero("10");
        endereco.setRua("Natal");

        usuario.setEndereco(endereco);

        usuarioRepository.save(usuario);
    }
}


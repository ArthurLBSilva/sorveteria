package sorveteria.dto.DtoUsuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDto {

    @NotBlank(message = "O nome não pode estar em branco.")
    String nome;

    @NotBlank(message = "O e-mail não pode estar em branco.")
    @Email(message = "O e-mail deve ser válido.")
    String email;

    @NotBlank(message = "A senha não pode estar em branco.")
    String senha;

    @NotBlank(message = "O ID do endereço não pode estar em branco.")
    Long enderecoId;
}

package sorveteria.dto.DtoEndereço;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDto {

    @NotBlank(message = "A rua não pode estar em branco.")
    String rua;

    @NotBlank(message = "O número não pode estar em branco.")
    String numero;
}

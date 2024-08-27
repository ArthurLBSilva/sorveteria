package sorveteria.dto.DtoSabor;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaborRequestDto {

    @NotBlank(message = "O nome não pode estar em branco.")
    String nome;

    String estoque;
}
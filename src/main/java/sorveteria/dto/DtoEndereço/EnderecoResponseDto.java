package sorveteria.dto.DtoEndereço;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponseDto {

    Long id;
    String rua;
    String numero;
}

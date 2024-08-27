package sorveteria.dto.DtoPeso;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PesoRequestDto {

    @NotBlank(message = "O nome do peso não pode estar em branco.")
    String peso;

    @Min(value = 0, message = "O valor do peso deve ser maior ou igual a zero.")
    Double valor;
}

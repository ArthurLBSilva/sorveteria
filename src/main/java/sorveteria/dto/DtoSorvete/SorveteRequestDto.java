package sorveteria.dto.DtoSorvete;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SorveteRequestDto {

    @Min(value = 0, message = "O preço do sorvete deve ser maior ou igual a zero.")
    Double preco;

    @NotNull(message = "O ID do peso não pode estar em branco.")
    Long pesoId;

    @NotNull(message = "A lista de IDs de sabores não pode estar em branco.")
    List<Long> saborIds;

    @NotNull(message = "O ID da calda não pode estar em branco.")
    Long caldaId;

    @NotNull(message = "O ID do carrinho não pode estar em branco.")
    Long carrinhoId;
}

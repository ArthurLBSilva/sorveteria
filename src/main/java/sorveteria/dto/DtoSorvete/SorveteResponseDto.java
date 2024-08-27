package sorveteria.dto.DtoSorvete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sorveteria.dto.DtoCalda.CaldaResponseDto;
import sorveteria.dto.DtoPeso.PesoResponseDto;
import sorveteria.dto.DtoSabor.SaborResponseDto;
import sorveteria.dto.DtoCarrinho.CarrinhoResponseDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SorveteResponseDto {

    Long id;
    Double preco;
    PesoResponseDto peso;
    List<SaborResponseDto> sabores;
    CaldaResponseDto calda;
    CarrinhoResponseDto carrinho;
}

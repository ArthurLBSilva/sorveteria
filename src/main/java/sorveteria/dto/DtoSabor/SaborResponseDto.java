package sorveteria.dto.DtoSabor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sorveteria.dto.DtoSorvete.SorveteResponseDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaborResponseDto {

    Long id;
    String nome;
    String estoque;
    List<SorveteResponseDto> sorvetes;

    
}
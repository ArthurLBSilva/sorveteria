package sorveteria.dto.DtoPeso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sorveteria.dto.DtoSorvete.SorveteResponseDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PesoResponseDto {

    Long id;
    String peso;
    Double valor;
    List<SorveteResponseDto> sorvetes;
}

package sorveteria.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sorveteria.dto.DtoSabor.SaborRequestDto;
import sorveteria.dto.DtoSabor.SaborResponseDto;
import sorveteria.dto.DtoSorvete.SorveteResponseDto;
import sorveteria.model.Sabor;
import sorveteria.service.SaborService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sabores/")
@AllArgsConstructor
public class SaborController {

    private final SaborService saborService;
    private final ModelMapper mapper;

    @GetMapping
    public Page<SaborResponseDto> listAll(Pageable pageable) {
        Page<Sabor> saboresPage = saborService.listAll(pageable);
        return saboresPage.map(this::convertToDto);
    }

    @PostMapping
    public ResponseEntity<SaborResponseDto> create(@RequestBody SaborRequestDto saborRequestDto) {
        Sabor saborToCreate = convertToEntity(saborRequestDto);
        Sabor createdSabor = saborService.create(saborToCreate);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdSabor.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(createdSabor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaborResponseDto> listById(@PathVariable("id") Long id) {
        Sabor sabor = saborService.listById(id);
        return ResponseEntity.ok(convertToDto(sabor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaborResponseDto> update(@RequestBody SaborRequestDto saborRequestDto, @PathVariable("id") Long id) {
        Sabor saborToUpdate = convertToEntity(saborRequestDto);
        saborToUpdate.setId(id);

        Sabor updatedSabor = saborService.update(saborToUpdate, id);
        return ResponseEntity.ok(convertToDto(updatedSabor));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        saborService.deleteById(id);
    }

    private SaborResponseDto convertToDto(Sabor sabor) {
        SaborResponseDto saborResponseDto = mapper.map(sabor, SaborResponseDto.class);

        List<SorveteResponseDto> sorvetesDto = sabor.getSorvetes().stream()
                .map(sorvete -> mapper.map(sorvete, SorveteResponseDto.class))
                .collect(Collectors.toList());

        saborResponseDto.setSorvetes(sorvetesDto);
        return saborResponseDto;
    }

    private Sabor convertToEntity(SaborRequestDto saborRequestDto) {
        Sabor sabor = mapper.map(saborRequestDto, Sabor.class);

        if (sabor.getEstoque() == null) {
            sabor.setEstoque("Disponível");
        }

        return sabor;
    }
}

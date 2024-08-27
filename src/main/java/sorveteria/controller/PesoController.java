package sorveteria.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sorveteria.dto.DtoPeso.PesoRequestDto;
import sorveteria.dto.DtoPeso.PesoResponseDto;
import sorveteria.dto.DtoSorvete.SorveteResponseDto;
import sorveteria.model.Peso;
import sorveteria.service.PesoService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pesos/")
@AllArgsConstructor
public class PesoController {

    private final PesoService pesoService;
    private final ModelMapper mapper;

    @GetMapping
    public Page<PesoResponseDto> listAll(Pageable pageable) {
        Page<Peso> pesosPage = pesoService.listAll(pageable);
        return pesosPage.map(this::convertToDto);
    }

    @PostMapping
    public ResponseEntity<PesoResponseDto> create(@RequestBody PesoRequestDto pesoRequestDto) {
        Peso pesoToCreate = convertToEntity(pesoRequestDto);
        Peso createdPeso = pesoService.create(pesoToCreate);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPeso.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(createdPeso));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PesoResponseDto> listById(@PathVariable("id") Long id) {
        Peso peso = pesoService.listById(id);
        return ResponseEntity.ok(convertToDto(peso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PesoResponseDto> update(@RequestBody PesoRequestDto pesoRequestDto, @PathVariable("id") Long id) {
        Peso pesoToUpdate = convertToEntity(pesoRequestDto);
        pesoToUpdate.setId(id);

        Peso updatedPeso = pesoService.update(pesoToUpdate, id);
        return ResponseEntity.ok(convertToDto(updatedPeso));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        pesoService.deleteById(id);
    }

    private PesoResponseDto convertToDto(Peso peso) {
        PesoResponseDto pesoResponseDto = mapper.map(peso, PesoResponseDto.class);

        List<SorveteResponseDto> sorvetesDto = peso.getSorvetes().stream()
                .map(sorvete -> mapper.map(sorvete, SorveteResponseDto.class))
                .collect(Collectors.toList());

        pesoResponseDto.setSorvetes(sorvetesDto);
        return pesoResponseDto;
    }

    private Peso convertToEntity(PesoRequestDto pesoRequestDto) {
        return mapper.map(pesoRequestDto, Peso.class);
    }
}

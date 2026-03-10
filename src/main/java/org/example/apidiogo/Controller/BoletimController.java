package org.example.apidiogo.Controller;
import jakarta.validation.Valid;
import org.example.apidiogo.Dto.AlunoResponseDto;
import org.example.apidiogo.Dto.BoletimRequestDto;
import org.example.apidiogo.Dto.BoletimResponseDto;
import org.example.apidiogo.Model.Boletim;
import org.example.apidiogo.Openapi.BoletimOpenApi;
import org.example.apidiogo.Service.BoletimService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/boletim")
public class BoletimController implements BoletimOpenApi {

    private final BoletimService service;
    private final BoletimService boletimService;

    public BoletimController(BoletimService service, BoletimService boletimService) {
        this.service = service;
        this.boletimService = boletimService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoletimResponseDto>> listBoletins() {
        List<BoletimResponseDto> boletins = service.listAll();
        return ResponseEntity.ok(boletins);
    }

    @GetMapping("/findBoletim/{id}")
    public ResponseEntity<List<BoletimResponseDto>> findBoletim(@PathVariable Long id) {
        return ResponseEntity.ok(service.listById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<BoletimResponseDto> createBoletim(@RequestBody @Valid BoletimRequestDto dto) {
        BoletimResponseDto response = service.createBoletim(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boletim> deleteBoletim(@PathVariable Long id) {
        service.deleteBoletim(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BoletimResponseDto> updateBoletim(@PathVariable Long id, @RequestBody @Valid BoletimRequestDto dto) {
        BoletimResponseDto response = boletimService.updateBoletim(dto, id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<BoletimResponseDto> patchBoletim(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        BoletimResponseDto response = service.updatePatchBoletim(updates, id);
        return ResponseEntity.ok(response);
    }

}

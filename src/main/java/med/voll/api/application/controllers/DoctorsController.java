package med.voll.api.application.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.application.dtos.doctor.CreateDoctorDto;
import med.voll.api.application.dtos.doctor.ListDoctorsDto;
import med.voll.api.application.dtos.doctor.ShowDoctorDto;
import med.voll.api.application.dtos.doctor.UpdateDoctorDto;
import med.voll.api.application.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorsController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    @Transactional
    public ResponseEntity<ShowDoctorDto> create(@RequestBody @Valid CreateDoctorDto dto, UriComponentsBuilder uriBuilder) {
        var showDoctorDto = doctorService.create(dto);
        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(showDoctorDto.id()).toUri();
        return ResponseEntity.created(uri).body(showDoctorDto);
    }

    @GetMapping
    public ResponseEntity<Page<ListDoctorsDto>> index(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(doctorService.getAll(pageable));
    }

    @PatchMapping("{id}")
    @Transactional
    public ResponseEntity<ShowDoctorDto> update(@PathVariable Long id, @RequestBody @Valid UpdateDoctorDto dto) {
        return ResponseEntity.ok(doctorService.update(id, dto));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ShowDoctorDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getById(id));
    }
}

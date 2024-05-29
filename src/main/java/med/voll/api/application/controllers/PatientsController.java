package med.voll.api.application.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.application.dtos.patient.CreatePatientDto;
import med.voll.api.application.dtos.patient.ListPatientsDto;
import med.voll.api.application.dtos.patient.ShowPatientDto;
import med.voll.api.application.dtos.patient.UpdatePatientDto;
import med.voll.api.application.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
@SecurityRequirement(name = "bearer-key")
public class PatientsController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    @Transactional
    public ResponseEntity<ShowPatientDto> create(@RequestBody @Valid CreatePatientDto dto, UriComponentsBuilder uriBuilder) {
        var showPatientDto = patientService.create(dto);
        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(showPatientDto.id()).toUri();
        return ResponseEntity.created(uri).body(showPatientDto);
    }

    @GetMapping
    public ResponseEntity<Page<ListPatientsDto>> index(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(patientService.getAll(pageable));
    }

    @PatchMapping("{id}")
    @Transactional
    public ResponseEntity<ShowPatientDto> update(@PathVariable Long id, @RequestBody @Valid UpdatePatientDto dto) {
        return ResponseEntity.ok(patientService.update(id, dto));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ShowPatientDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getById(id));
    }
}

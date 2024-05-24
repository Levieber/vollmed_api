package med.voll.api.application.controllers;

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

@RestController
@RequestMapping("doctors")
public class DoctorsController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> create(@RequestBody @Valid CreateDoctorDto dto) {
        doctorService.create(dto);
        return ResponseEntity.noContent().build();
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
}

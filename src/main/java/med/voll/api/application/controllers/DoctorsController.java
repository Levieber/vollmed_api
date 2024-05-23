package med.voll.api.application.controllers;

import jakarta.validation.Valid;
import med.voll.api.application.dtos.CreateDoctorDto;
import med.voll.api.application.dtos.ReadDoctorDto;
import med.voll.api.application.dtos.UpdateDoctorDto;
import med.voll.api.application.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctors")
public class DoctorsController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid CreateDoctorDto dto) {
        doctorService.create(dto);
    }

    @GetMapping
    public Page<ReadDoctorDto> index(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return doctorService.getAll(pageable);
    }

    @PatchMapping("{id}")
    @Transactional
    public void update(@PathVariable Long id, @RequestBody @Valid UpdateDoctorDto dto) {
        doctorService.update(id, dto);
    }

    @DeleteMapping("{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        doctorService.delete(id);
    }
}

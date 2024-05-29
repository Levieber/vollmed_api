package med.voll.api.application.controllers;

import jakarta.validation.Valid;
import med.voll.api.application.dtos.appointment.CancelAppointmentDto;
import med.voll.api.application.dtos.appointment.CreateAppointmentDto;
import med.voll.api.application.dtos.appointment.ShowAppointmentDto;
import med.voll.api.application.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
public class AppointmentsController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity<ShowAppointmentDto> create(@RequestBody @Valid CreateAppointmentDto dto) {
        return ResponseEntity.ok(appointmentService.create(dto));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> cancel(@PathVariable Long id, @RequestBody @Valid CancelAppointmentDto dto) {
        appointmentService.cancel(id, dto);
        return ResponseEntity.noContent().build();
    }
}

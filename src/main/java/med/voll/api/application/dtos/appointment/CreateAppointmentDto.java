package med.voll.api.application.dtos.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.enums.DoctorSpeciality;

import java.time.LocalDateTime;

public record CreateAppointmentDto(
        Long doctorId,
        @NotNull
        Long patientId,
        @NotNull
        @Future
        @JsonFormat(pattern = "yyyy-MM-dd HH:00:00")
        LocalDateTime date,
        DoctorSpeciality speciality
) {
}

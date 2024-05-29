package med.voll.api.application.dtos.appointment;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.enums.CancelAppointmentReason;

public record CancelAppointmentDto(
        @NotNull
        CancelAppointmentReason reason
) {
}

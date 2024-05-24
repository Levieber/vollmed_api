package med.voll.api.application.dtos.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import med.voll.api.application.dtos.address.UpdateAddressDto;

public record UpdatePatientDto(
        String name,
        @Email
        String email,
        String phone,
        @Valid
        UpdateAddressDto address
) {
}

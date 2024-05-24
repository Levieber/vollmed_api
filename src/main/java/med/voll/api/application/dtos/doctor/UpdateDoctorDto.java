package med.voll.api.application.dtos.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import med.voll.api.application.dtos.address.UpdateAddressDto;

public record UpdateDoctorDto(
        String name,
        @Email
        String email,
        String phone,
        @Valid
        UpdateAddressDto address
) {
}

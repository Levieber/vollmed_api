package med.voll.api.application.dtos;

import jakarta.validation.constraints.Email;

public record UpdateDoctorDto(
        String name,
        @Email
        String email,
        String phone,
        UpdateAddressDto address
) {
}

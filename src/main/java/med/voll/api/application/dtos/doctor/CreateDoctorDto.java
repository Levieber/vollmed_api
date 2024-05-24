package med.voll.api.application.dtos.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.application.dtos.address.CreateAddressDto;
import med.voll.api.domain.enums.DoctorSpeciality;

public record CreateDoctorDto(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        DoctorSpeciality speciality,
        @NotNull
        @Valid
        CreateAddressDto address
) {
}

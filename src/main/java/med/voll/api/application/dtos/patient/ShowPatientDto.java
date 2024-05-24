package med.voll.api.application.dtos.patient;

import med.voll.api.application.dtos.address.ReadAddressDto;
import med.voll.api.domain.entities.Patient;

public record ShowPatientDto(
        Long id,
        String name,
        String email,
        String phone,
        String cpf,
        ReadAddressDto address
) {
    public ShowPatientDto(Patient entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getCpf(),
                new ReadAddressDto(entity.getAddress())
        );
    }
}

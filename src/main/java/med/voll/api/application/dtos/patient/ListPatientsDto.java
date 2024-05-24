package med.voll.api.application.dtos.patient;

import med.voll.api.domain.entities.Patient;

public record ListPatientsDto(Long id, String name, String email, String cpf) {
    public ListPatientsDto(Patient entity) {
        this(entity.getId(), entity.getName(), entity.getEmail(), entity.getCpf());
    }
}

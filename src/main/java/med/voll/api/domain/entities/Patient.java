package med.voll.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.application.dtos.patient.CreatePatientDto;
import med.voll.api.application.dtos.patient.UpdatePatientDto;

@Entity
@Table(name = "patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    @Embedded
    private Address address;
    private Boolean active = true;

    public Patient(CreatePatientDto dto) {
            name = dto.name();
            email = dto.email();
            phone = dto.phone();
            cpf = dto.cpf();
            address = new Address(dto.address());
    }

    public void update(UpdatePatientDto dto) {
        if (dto.name() != null && !dto.name().isBlank()) {
            name = dto.name();
        }
        if (dto.email() != null && !dto.email().isBlank()) {
            email = dto.email();
        }
        if (dto.phone() != null && !dto.phone().isBlank()) {
            phone = dto.phone();
        }
        if (dto.address() != null) {
            address.update(dto.address());
        }
    }

    public void delete() {
        active = false;
    }
}

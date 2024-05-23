package med.voll.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.application.dtos.CreateDoctorDto;
import med.voll.api.application.dtos.UpdateDoctorDto;
import med.voll.api.domain.enums.DoctorSpeciality;

@Entity
@Table(name = "doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;
    private boolean active = true;
    @Enumerated(EnumType.STRING)
    private DoctorSpeciality speciality;
    @Embedded
    private Address address;

    public Doctor(CreateDoctorDto dto) {
        name = dto.name();
        email = dto.email();
        phone = dto.phone();
        crm = dto.crm();
        speciality = dto.speciality();
        address = new Address(dto.address());
    }

    public void update(UpdateDoctorDto dto) {
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

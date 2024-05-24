package med.voll.api.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.application.dtos.address.CreateAddressDto;
import med.voll.api.application.dtos.address.UpdateAddressDto;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String neighborhood;
    private String cep;
    private String city;
    private String state;
    private String number;
    private String complement;

    public Address(CreateAddressDto dto) {
        street = dto.street();
        neighborhood = dto.neighborhood();
        cep = dto.cep();
        city = dto.city();
        state = dto.state();
        number = dto.number();
        complement = dto.complement();
    }

    public void update(UpdateAddressDto dto) {
            if (dto.street() != null) {
                street = dto.street();
            }
            if (dto.neighborhood() != null) {
                neighborhood = dto.neighborhood();
            }
            if (dto.cep() != null) {
                cep = dto.cep();
            }
            if (dto.state() != null) {
                state = dto.state();
            }
            if (dto.city() != null) {
                city = dto.city();
            }
            if (dto.number() != null) {
                number = dto.number();
            }
            if (dto.complement() != null) {
                complement = dto.complement();
            }
    }
}

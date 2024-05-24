package med.voll.api.application.dtos.address;

import med.voll.api.domain.entities.Address;

public record ReadAddressDto(
        String street,
        String neighborhood,
        String cep,
        String city,
        String state,
        String number,
        String complement
) {
    public ReadAddressDto(Address entity) {
        this(
                entity.getStreet(),
                entity.getNeighborhood(),
                entity.getCep(),
                entity.getCity(),
                entity.getState(),
                entity.getNumber(),
                entity.getComplement()
        );
    }
}
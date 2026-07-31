package io.github.alnszzx.brokercontrol.Application.Support;

import io.github.alnszzx.brokercontrol.Application.Dto.AddressDto;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Address;

public final class AddressMapper {

    private AddressMapper() {
    }

    public static Address toAddress(AddressDto dto) {
        if (dto == null) {
            return null;
        }
        return new Address(
                dto.street(),
                dto.number(),
                dto.complement(),
                dto.neighborhood(),
                dto.city(),
                dto.state(),
                dto.postalCode(),
                dto.country()
        );
    }
}

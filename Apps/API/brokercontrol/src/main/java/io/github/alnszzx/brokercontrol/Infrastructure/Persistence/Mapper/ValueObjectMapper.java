package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Mapper;

import io.github.alnszzx.brokercontrol.Domain.ValueObject.Address;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Email;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Money;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.PhoneNumber;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.ValueObject.AddressJpa;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.ValueObject.EmailJpa;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.ValueObject.MoneyJpa;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.ValueObject.PhoneNumberJpa;

import java.util.Currency;

public final class ValueObjectMapper {

    private ValueObjectMapper() {
    }

    public static EmailJpa toEmailJpa(Email email) {
        if (email == null) {
            return null;
        }
        return EmailJpa.builder().value(email.getValue()).build();
    }

    public static Email toEmail(EmailJpa emailJpa) {
        if (emailJpa == null || emailJpa.getValue() == null) {
            return null;
        }
        return new Email(emailJpa.getValue());
    }

    public static PhoneNumberJpa toPhoneNumberJpa(PhoneNumber phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }
        return PhoneNumberJpa.builder().value(phoneNumber.getValue()).build();
    }

    public static PhoneNumber toPhoneNumber(PhoneNumberJpa phoneNumberJpa) {
        if (phoneNumberJpa == null || phoneNumberJpa.getValue() == null) {
            return null;
        }
        return new PhoneNumber(phoneNumberJpa.getValue());
    }

    public static AddressJpa toAddressJpa(Address address) {
        if (address == null) {
            return null;
        }
        return AddressJpa.builder()
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }

    public static Address toAddress(AddressJpa addressJpa) {
        if (addressJpa == null) {
            return null;
        }
        return new Address(
                addressJpa.getStreet(),
                addressJpa.getNumber(),
                addressJpa.getComplement(),
                addressJpa.getNeighborhood(),
                addressJpa.getCity(),
                addressJpa.getState(),
                addressJpa.getPostalCode(),
                addressJpa.getCountry()
        );
    }

    public static MoneyJpa toMoneyJpa(Money money) {
        if (money == null) {
            return null;
        }
        return MoneyJpa.builder()
                .amount(money.getAmount())
                .currency(money.getCurrency().getCurrencyCode())
                .build();
    }

    public static Money toMoney(MoneyJpa moneyJpa) {
        if (moneyJpa == null || moneyJpa.getAmount() == null || moneyJpa.getCurrency() == null) {
            return null;
        }
        return new Money(moneyJpa.getAmount(), Currency.getInstance(moneyJpa.getCurrency()));
    }
}

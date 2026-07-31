package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.ValueObject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailJpa {

    @Column(name = "email")
    private String value;
}

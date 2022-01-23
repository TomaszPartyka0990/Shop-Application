package pl.partyka.shopapplication.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adress {
    private String country;
    private String city;
    private String street;
    private String zipCode;
}

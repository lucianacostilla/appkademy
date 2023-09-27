package com.equipo2.Appkademy.core.model.entity;

import com.equipo2.Appkademy.core.model.enums.City;
import com.equipo2.Appkademy.core.model.enums.Country;
import com.equipo2.Appkademy.core.model.enums.Province;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverrides({
        //TODO revertir eventualemtne countr, province, city, streetname y streetnumber a nullable false cuando se pida direccion al alumno
        @AttributeOverride( name = "country", column = @Column(name = "country", nullable = true)),
        @AttributeOverride( name = "province", column = @Column(name = "province", nullable = true)),
        @AttributeOverride( name = "city", column = @Column(name = "city", nullable = true)),
        @AttributeOverride( name = "streetName", column = @Column(name = "street_name", nullable = true)),
        @AttributeOverride( name = "streetNumber", column = @Column(name = "street_number", nullable = true)),
        @AttributeOverride( name = "floorApt", column = @Column(name = "floor_apt", nullable = true))
})
public class Address {

    //TODO zip code?

    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.STRING)
    private Province province;

    @Enumerated(EnumType.STRING)
    private City city;

    private String streetName;
    private String streetNumber;
    private String floorApt;

}

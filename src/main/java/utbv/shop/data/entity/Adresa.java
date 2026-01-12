package utbv.shop.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "adresa")
public class Adresa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adresa_gen")
    @SequenceGenerator(name = "adresa_gen", sequenceName = "adresa_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "zip_code")
    private String zipcode;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;

    public Adresa() {
        // Required by JPA
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}

package utbv.shop.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "adresa", indexes = @Index(name = "idx_adresa_zip_code", columnList = "zip_code"))
public class Adresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Pattern(regexp = "\\d{6}")
    @Column(name = "zip_code", nullable = false, length = 6)
    private String zipcode;

    @NotBlank
    @Size(max = 120)
    @Column(name = "street", nullable = false, length = 120)
    private String street;

    @NotBlank
    @Size(max = 120)
    @Column(name = "city", nullable = false, length = 120)
    private String city;

    public Adresa() {
        // Required by JPA
    }

    public String getZipcode() {
        return zipcode;
    }

    public Long getId() {
        return id;
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

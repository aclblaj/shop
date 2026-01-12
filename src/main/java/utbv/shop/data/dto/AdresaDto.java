package utbv.shop.data.dto;

public class AdresaDto {
    private String zipcode;
    private String street;
    private String city;

    public AdresaDto() {
    }

    public AdresaDto(String zipcode, String street, String city) {
        this.zipcode = zipcode;
        this.street = street;
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }
}

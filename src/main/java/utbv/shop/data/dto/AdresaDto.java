package utbv.shop.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AdresaDto(
        @NotBlank
        @Pattern(regexp = "\\d{6}", message = "must contain exactly 6 digits")
        String zipcode,

        @NotBlank
        @Size(max = MAX_FIELD_LENGTH)
        String street,

        @NotBlank
        @Size(max = MAX_FIELD_LENGTH)
        String city
) {
    private static final int MAX_FIELD_LENGTH = 120;
}

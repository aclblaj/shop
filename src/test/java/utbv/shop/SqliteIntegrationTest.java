package utbv.shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import utbv.shop.data.entity.Adresa;
import utbv.shop.data.repository.AdresaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("sqlite")
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:sqlite:target/test-shop.db",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class SqliteIntegrationTest {

    @Autowired
    private AdresaRepository adresaRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void seedsTenAddressesAndSupportsSqlitePersistenceAndRestRequests() throws Exception {
        List<Adresa> seededAddresses = adresaRepository.findAll();
        assertThat(seededAddresses).hasSize(10);
        assertThat(seededAddresses).allSatisfy(address -> {
            assertThat(address.getId()).isNotNull();
            assertThat(address.getZipcode()).matches("\\d{6}");
            assertThat(address.getCity()).isNotBlank();
            assertThat(address.getStreet()).isNotBlank();
        });
        Set<String> zipcodes = new HashSet<>();
        seededAddresses.forEach(address -> zipcodes.add(address.getZipcode()));
        assertThat(zipcodes).hasSize(10);

        Adresa existingAddress = seededAddresses.getFirst();
        mockMvc.perform(get("/adresa").param("zc", existingAddress.getZipcode()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].zipcode").value(existingAddress.getZipcode()));

        mockMvc.perform(get("/adresa").param("zc", "missing"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(0)));

        mockMvc.perform(post("/adresa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"zipcode":"500091","street":"Iuliu Maniu 12","city":"Brasov"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.zipcode").value("500091"));

        mockMvc.perform(post("/adresa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"zipcode":"500091","street":"IULIU MANIU 12","city":"Brasov"}
                                """))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/adresa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"zipcode":"500091","street":"Republicii 9","city":"Brasov"}
                                """))
                .andExpect(status().isCreated());

        List<Adresa> sameZipcodeAddresses = adresaRepository.findAllByZipcode("500091");
        assertThat(sameZipcodeAddresses).hasSize(3);
        assertThat(adresaRepository.findAllByStreetIgnoreCase("iuliu maniu 12")).hasSize(2);
        assertThat(adresaRepository.findAllByCityIgnoreCase("brasov"))
                .extracting(Adresa::getId)
                .containsAll(sameZipcodeAddresses.stream().map(Adresa::getId).toList());
        Long addressId = sameZipcodeAddresses.getFirst().getId();

        mockMvc.perform(get("/adresa").param("zc", "500091"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(3)));

        mockMvc.perform(get("/adresa/{id}", addressId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(addressId));

        mockMvc.perform(put("/adresa/{id}", addressId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"zipcode":"500091","street":"Updated Street 1","city":"Brasov"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.street").value("Updated Street 1"));

        mockMvc.perform(put("/adresa/{id}", Long.MAX_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"zipcode":"500091","street":"Updated Street 1","city":"Brasov"}
                                """))
                .andExpect(status().isNotFound());

        mockMvc.perform(delete("/adresa/{id}", addressId))
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/adresa/{id}", addressId))
                .andExpect(status().isNotFound());

        mockMvc.perform(delete("/adresa").param("zc", "500091"))
                .andExpect(status().isNoContent());

        assertThat(adresaRepository.findAllByZipcode("500091")).isEmpty();

        mockMvc.perform(delete("/adresa").param("zc", "500091"))
                .andExpect(status().isNotFound());

        mockMvc.perform(post("/adresa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"zipcode":"","street":"","city":""}
                                """))
                .andExpect(status().isBadRequest());

        mockMvc.perform(post("/adresa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"zipcode":"ABC123","street":"Valid Street","city":"Brasov"}
                                """))
                .andExpect(status().isBadRequest());

        assertThat(adresaRepository.count()).isEqualTo(10);
    }
}

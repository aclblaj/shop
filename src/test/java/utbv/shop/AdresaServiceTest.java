package utbv.shop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utbv.shop.data.dto.AdresaDto;
import utbv.shop.data.entity.Adresa;
import utbv.shop.data.repository.AdresaRepository;
import utbv.shop.data.service.AdresaService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdresaServiceTest {

    @Mock
    private AdresaRepository adresaRepository;

    @InjectMocks
    private AdresaService adresaService;

    @Test
    void saveCopiesAllFieldsFromDto() {
        when(adresaRepository.save(any(Adresa.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Adresa saved = adresaService.save(new AdresaDto("500091", "Iuliu Maniu", "Brasov"));

        assertThat(saved.getZipcode()).isEqualTo("500091");
        assertThat(saved.getStreet()).isEqualTo("Iuliu Maniu");
        assertThat(saved.getCity()).isEqualTo("Brasov");
        verify(adresaRepository).save(any(Adresa.class));
    }

    @Test
    void findByZipcodeReturnsAllMatchingAddresses() {
        Adresa first = new Adresa();
        first.setZipcode("500091");
        Adresa second = new Adresa();
        second.setZipcode("500091");
        when(adresaRepository.findAllByZipcode("500091")).thenReturn(List.of(first, second));

        assertThat(adresaService.findByZipcode("500091")).containsExactly(first, second);
    }
}

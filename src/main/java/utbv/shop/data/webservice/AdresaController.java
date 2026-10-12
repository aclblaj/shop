package utbv.shop.data.webservice;

import utbv.shop.data.dto.AdresaDto;
import utbv.shop.data.entity.Adresa;
import utbv.shop.data.service.AdresaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class AdresaController {

    private final AdresaService adresaService;

    public AdresaController(AdresaService adresaService) {
        this.adresaService = adresaService;
    }

    @GetMapping("/adresa")
    List<Adresa> findAdreseByZipcode(@RequestParam(name = "zc") String zipcode) {
        return adresaService.findByZipcode(zipcode);
    }

    @DeleteMapping("/adresa")
    ResponseEntity<Void> deleteAdresa(@RequestParam(name = "zc") String zipcode) {
        if (adresaService.deleteByZipcode(zipcode) == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/adresa/{id}")
    ResponseEntity<Adresa> findAdresaById(@PathVariable Long id) {
        return ResponseEntity.of(adresaService.findById(id));
    }

    @PutMapping("/adresa/{id}")
    ResponseEntity<Adresa> updateAdresaById(@PathVariable Long id, @Valid @RequestBody AdresaDto adresaDto) {
        return ResponseEntity.of(adresaService.updateById(id, adresaDto));
    }

    @DeleteMapping("/adresa/{id}")
    ResponseEntity<Void> deleteAdresaById(@PathVariable Long id) {
        if (!adresaService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/adresa/all")
    List<Adresa> findAllAdresa() {
        return adresaService.findAll();
    }

    @PostMapping("/adresa")
    ResponseEntity<Adresa> saveAdresa(@Valid @RequestBody AdresaDto adresaDto) {
        Adresa saved = adresaService.save(adresaDto);
        URI location = UriComponentsBuilder.fromPath("/adresa/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

}

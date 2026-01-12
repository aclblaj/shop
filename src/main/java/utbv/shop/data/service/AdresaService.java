package utbv.shop.data.service;

import utbv.shop.data.dto.AdresaDto;
import utbv.shop.data.entity.Adresa;
import utbv.shop.data.repository.AdresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresaService {

    @Autowired
    private AdresaRepository adresaRepository;
    public Adresa save(AdresaDto adresaDto) {
        Adresa adresa = new Adresa();
        adresa.setCity(adresaDto.getCity());
        adresa.setStreet(adresaDto.getStreet());
        System.out.println("saving adresa= " + adresa.getZipcode());
        Adresa adresaRes = adresaRepository.save(adresa);
        return adresaRes;
    }

    public Adresa findByZipcode(String zipcode) {
        Optional<Adresa> adresa = adresaRepository.findByZipcode(zipcode);
        if (adresa.isPresent()) {
            return adresa.get();
        } else {
            System.out.println("Adresa not found");
            return null;
        }
    }

    public List<Adresa> findAll() {
        return (List<Adresa>) adresaRepository.findAll();
    }
}

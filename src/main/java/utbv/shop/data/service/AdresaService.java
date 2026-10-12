package utbv.shop.data.service;

import utbv.shop.data.dto.AdresaDto;
import utbv.shop.data.entity.Adresa;
import utbv.shop.data.repository.AdresaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdresaService {

    private final AdresaRepository adresaRepository;

    public AdresaService(AdresaRepository adresaRepository) {
        this.adresaRepository = adresaRepository;
    }

    @Transactional
    public Adresa save(AdresaDto adresaDto) {
        Adresa adresa = new Adresa();
        adresa.setZipcode(adresaDto.zipcode());
        adresa.setCity(adresaDto.city());
        adresa.setStreet(adresaDto.street());
        return adresaRepository.save(adresa);
    }

    @Transactional(readOnly = true)
    public List<Adresa> findByZipcode(String zipcode) {
        return adresaRepository.findAllByZipcode(zipcode);
    }

    @Transactional
    public int deleteByZipcode(String zipcode) {
        List<Adresa> matchingAddresses = adresaRepository.findAllByZipcode(zipcode);
        adresaRepository.deleteAll(matchingAddresses);
        return matchingAddresses.size();
    }

    @Transactional(readOnly = true)
    public Optional<Adresa> findById(Long id) {
        return adresaRepository.findById(id);
    }

    @Transactional
    public Optional<Adresa> updateById(Long id, AdresaDto adresaDto) {
        return adresaRepository.findById(id).map(adresa -> {
            adresa.setZipcode(adresaDto.zipcode());
            adresa.setStreet(adresaDto.street());
            adresa.setCity(adresaDto.city());
            return adresaRepository.save(adresa);
        });
    }

    @Transactional
    public boolean deleteById(Long id) {
        return adresaRepository.findById(id).map(adresa -> {
            adresaRepository.delete(adresa);
            return true;
        }).orElse(false);
    }

    @Transactional(readOnly = true)
    public List<Adresa> findAll() {
        return adresaRepository.findAll();
    }
}

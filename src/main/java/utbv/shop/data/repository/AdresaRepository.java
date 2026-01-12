package utbv.shop.data.repository;

import utbv.shop.data.entity.Adresa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AdresaRepository extends CrudRepository<Adresa, String> {
    Optional<Adresa> findByZipcode(String zipcode);

    Optional<Adresa> findByStreetEqualsIgnoreCase(String street);

    List<Adresa> findAll();

    List<Adresa> findByCity(String city);
}

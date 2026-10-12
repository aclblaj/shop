package utbv.shop.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utbv.shop.data.entity.Adresa;

import java.util.List;

public interface AdresaRepository extends JpaRepository<Adresa, Long> {
    List<Adresa> findAllByZipcode(String zipcode);

    List<Adresa> findAllByStreetIgnoreCase(String street);

    List<Adresa> findAllByCityIgnoreCase(String city);
}

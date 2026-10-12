package utbv.shop.data.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import utbv.shop.data.entity.Adresa;
import utbv.shop.data.repository.AdresaRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Profile("sqlite")
public class SqliteAddressSeeder implements ApplicationRunner {

    private static final int SEED_COUNT = 10;
    private static final String[] CITIES = {
            "Brasov", "Bucuresti", "Cluj-Napoca", "Sibiu", "Timisoara",
            "Iasi", "Oradea", "Constanta", "Craiova", "Suceava"
    };
    private static final String[] STREETS = {
            "Mihai Eminescu", "Libertatii", "Florilor", "Independentei", "Republicii",
            "Stefan cel Mare", "Muresenilor", "Primaverii", "Victoriei", "Lalelelor"
    };

    private final AdresaRepository adresaRepository;

    public SqliteAddressSeeder(AdresaRepository adresaRepository) {
        this.adresaRepository = adresaRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (adresaRepository.count() != 0) {
            return;
        }

        ThreadLocalRandom random = ThreadLocalRandom.current();
        Set<String> zipcodes = new HashSet<>();
        List<Adresa> addresses = new ArrayList<>(SEED_COUNT);

        while (addresses.size() < SEED_COUNT) {
            String zipcode = Integer.toString(random.nextInt(100_000, 1_000_000));
            if (!zipcodes.add(zipcode)) {
                continue;
            }

            Adresa address = new Adresa();
            address.setZipcode(zipcode);
            address.setCity(CITIES[random.nextInt(CITIES.length)]);
            address.setStreet(STREETS[random.nextInt(STREETS.length)] + " " + random.nextInt(1, 151));
            addresses.add(address);
        }

        adresaRepository.saveAll(addresses);
    }
}

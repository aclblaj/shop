package utbv.shop.data.webservice;

import utbv.shop.data.dto.AdresaDto;
import utbv.shop.data.entity.Adresa;
import utbv.shop.data.repository.AdresaRepository;
import utbv.shop.data.service.AdresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdresaController {

    @Autowired
    private AdresaRepository adresaRepository;

    @Autowired
    private AdresaService adresaService;

    @RequestMapping(value="/adresa", method= RequestMethod.GET)
    Adresa findAdresa (@RequestParam(required=false, name = "zc") String zipcode) {
        Adresa adr = adresaService.findByZipcode(zipcode);
        System.out.println("found adr= " + adr.toString());
        return adr;
    }

    @RequestMapping(value="/adresa/all", method= RequestMethod.GET)
    List<Adresa> findAllAdresa () {
        List<Adresa> adresaList = adresaService.findAll();
        System.out.println("found adr no: " + adresaList.size());
        return adresaList;
    }

    @RequestMapping(value="/adresa", method= RequestMethod.POST)
    Adresa saveAdresa (@RequestBody AdresaDto adresaDto) {
        return adresaService.save(adresaDto);
    }

    @RequestMapping(value="/student", method= RequestMethod.POST)
    String saveStudent () {
        
        return "student saved";
    }

}

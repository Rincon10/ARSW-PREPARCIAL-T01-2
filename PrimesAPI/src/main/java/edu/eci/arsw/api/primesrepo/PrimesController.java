package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
public class PrimesController
{

    PrimeService primeService;


    @RequestMapping( value = "/primes", method = GET )
    public List<FoundPrime> getPrimes()
    {
        return primeService.getFoundPrimes();
    }


    //TODO implement additional methods provided by PrimeService



}

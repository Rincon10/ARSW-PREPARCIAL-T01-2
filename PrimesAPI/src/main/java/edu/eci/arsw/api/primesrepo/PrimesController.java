package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.persistence.PrimesPersistenceException;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
@RequestMapping( value = "/primes")
public class PrimesController
{


    @Autowired
    PrimeService primeService;


    @RequestMapping(method = GET )
    public ResponseEntity<?> getAllPrimes()
    {
        try {
            List<FoundPrime> data =primeService.getFoundPrimes();
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (PrimesPersistenceException ex) {
            ex.printStackTrace();
            Logger.getLogger(PrimesController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error del servidor", HttpStatus.valueOf(500));
        }
    }

    @RequestMapping(method = GET, value = "/{primenumber}")
    public ResponseEntity<?> getPrimeByNumber(@PathVariable("primenumber") String primenumber)
    {
        try {
            FoundPrime data = primeService.getPrime( primenumber);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (PrimesPersistenceException ex) {
            ex.printStackTrace();
            Logger.getLogger(PrimesController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping( value = "/add",method = POST )
    @ResponseBody
    public ResponseEntity<?> addFoundPrime(@RequestBody FoundPrime foundPrime)
    {
        try {
            primeService.addFoundPrime(foundPrime);
            return new ResponseEntity<>(HttpStatus.CREATED.getReasonPhrase() , HttpStatus.CREATED);
        } catch (PrimesPersistenceException ex) {
            ex.printStackTrace();
            Logger.getLogger(PrimesController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.CREATED.getReasonPhrase(),HttpStatus.FORBIDDEN);
        }
    }


    //TODO implement additional methods provided by PrimeService



}

package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.persistence.PrimesPersistence;
import edu.eci.arsw.api.primesrepo.persistence.PrimesPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@Service
public class PrimeServiceStub implements PrimeService
{

    @Autowired
    @Qualifier("opt1")
    PrimesPersistence persistence;


    public void addFoundPrime( FoundPrime foundPrime ) throws PrimesPersistenceException {
        //TODO
        persistence.addPrime( foundPrime );
    }


    public List<FoundPrime> getFoundPrimes() throws PrimesPersistenceException {
        //TODO
        return persistence.getPrimes();
    }


    public FoundPrime getPrime( String prime ) throws PrimesPersistenceException {
        //TODO
        return persistence.getPrimeByname(prime);
    }
}

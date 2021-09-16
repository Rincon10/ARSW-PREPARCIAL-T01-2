package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.persistence.PrimesPersistenceException;

import java.util.List;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
public interface PrimeService
{

    void addFoundPrime( FoundPrime foundPrime ) throws PrimesPersistenceException;

    List<FoundPrime> getFoundPrimes() throws PrimesPersistenceException;

    FoundPrime getPrime( String prime )throws PrimesPersistenceException;

}

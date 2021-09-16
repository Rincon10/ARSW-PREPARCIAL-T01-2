package edu.eci.arsw.api.primesrepo.persistence;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import java.util.List;


/**
 * @author Iván Camilo Rincón Saavedra
 * @version 9/15/2021
 */
public interface PrimesPersistence {
    public List<FoundPrime> getPrimes() throws PrimesPersistenceException;
    public void addPrime( FoundPrime foundPrime ) throws PrimesPersistenceException;
    public FoundPrime getPrimeByname( String name ) throws PrimesPersistenceException;
}

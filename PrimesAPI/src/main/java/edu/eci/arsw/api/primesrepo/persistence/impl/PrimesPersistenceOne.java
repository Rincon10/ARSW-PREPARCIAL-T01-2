package edu.eci.arsw.api.primesrepo.persistence.impl;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.persistence.PrimesPersistence;
import edu.eci.arsw.api.primesrepo.persistence.PrimesPersistenceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 9/15/2021
 */
@Component
@Qualifier("opt1")
public class PrimesPersistenceOne implements PrimesPersistence {
    private  ConcurrentHashMap<String, FoundPrime> primes;

    public PrimesPersistenceOne(){
        primes = new ConcurrentHashMap<>();
        int times = 4;
        for (int i = 0; i < times; i++) {
            primes.put(i+"", new FoundPrime("test"+i,i+""));
        }
    }

    @Override
    public List<FoundPrime> getPrimes() throws PrimesPersistenceException {
        List<FoundPrime> list = new ArrayList<>();
        for( String key : primes.keySet()){
            list.add( primes.get(key));
        }
        return list;
    }

    @Override
    public void addPrime(FoundPrime foundPrime) throws PrimesPersistenceException {
        String key = foundPrime.getPrime();
        if( primes.containsKey(key)) throw new PrimesPersistenceException(PrimesPersistenceException.ALREADY_EXISTS);
        primes.put(key, foundPrime);
    }

    @Override
    public FoundPrime getPrimeByname(String name) throws PrimesPersistenceException {
        Optional<FoundPrime> optionalFoundPrime = Optional.ofNullable(primes.get(name));
        optionalFoundPrime.orElseThrow( () -> new PrimesPersistenceException(PrimesPersistenceException.NOT_FOUND) );
        return optionalFoundPrime.get();
    }


}

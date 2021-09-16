package edu.eci.arsw.api.primesrepo.persistence;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 9/15/2021
 */
public class PrimesPersistenceException extends Exception{
    public final static String NOT_FOUND="No se pudo encontrar";
    public final static String ALREADY_EXISTS="El numero primo a almacenar ya existe";

    public PrimesPersistenceException( String message){
        super(message);
    }
    public PrimesPersistenceException( String message, Throwable cause){
        super(message, cause);
    }
}

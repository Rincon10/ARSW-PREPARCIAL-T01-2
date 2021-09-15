package edu.eci.arsw.api.primesrepo.model;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
public class FoundPrime
{
    String user;

    String prime;

    public FoundPrime()
    {
    }

    public String getUser()
    {
        return user;
    }

    public void setUser( String user )
    {
        this.user = user;
    }

    public String getPrime()
    {
        return prime;
    }

    public void setPrime( String prime )
    {
        this.prime = prime;
    }
}

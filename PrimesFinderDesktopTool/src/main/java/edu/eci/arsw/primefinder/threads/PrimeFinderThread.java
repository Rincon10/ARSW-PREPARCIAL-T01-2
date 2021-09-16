package edu.eci.arsw.primefinder.threads;

import edu.eci.arsw.math.MathUtilities;
import edu.eci.arsw.primefinder.PrimesResultSet;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 9/15/2021
 */
public class PrimeFinderThread extends Thread{
    private BigInteger _a;
    private BigInteger _b;
    private PrimesResultSet prs;
    private boolean stop = false;

    public PrimeFinderThread(BigInteger _a, BigInteger _b, PrimesResultSet prs){
        this._a = _a;
        this._b = _b;
        this.prs = prs;
        this.start();
    }

    public void stopThread() {
        stop = true;
    }

    public synchronized void resumeThread(){
        stop = false;
        notifyAll();
    }

    @Override
    public void run() {
        try{

            MathUtilities mt=new MathUtilities();

            int itCount=0;

            BigInteger i=_a;
            while (i.compareTo(_b)<0){
                while(stop){
                    synchronized ( this ){wait();}

                }
                itCount++;
                if (mt.isPrime(i)){
//                    System.out.println(this.getName()+" "+i);
                    prs.addPrime(i);
                }

                i=i.add(BigInteger.ONE);
            }
        }
        catch (InterruptedException ex) {
            Logger.getLogger(PrimeFinderThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}

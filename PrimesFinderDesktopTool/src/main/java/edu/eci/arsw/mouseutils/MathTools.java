/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.mouseutils;

import java.math.BigInteger;

/**
 *
 * @author hcadavid
 */
public class MathTools {

    public boolean checkIsPrime(BigInteger n) {
        int count = 0;

        BigInteger i=BigInteger.valueOf(2);
        while (i.compareTo(n.divide(BigInteger.valueOf(2)))<=0){
            if (n.mod(i).compareTo(BigInteger.ZERO)==0){
                count++;                
            }
            i=i.add(BigInteger.ONE);
        }
        
        
        if ( count > 0 )
        {
            //number is prime
            return true;
        }
        else
        {
            //number is not prime
            return false;
        }

    }

}

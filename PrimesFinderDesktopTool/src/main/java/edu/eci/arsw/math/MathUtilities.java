/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.math;

import java.math.BigInteger;

/**
 *
 * @author hcadavid
 */
public class MathUtilities {
    
    	public boolean isPrime(BigInteger n) {
            
            if (n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) return false;
            BigInteger i=BigInteger.valueOf(3);
            
            while(i.multiply(i).compareTo(n)<=0){
                if (n.mod(i).compareTo(BigInteger.ZERO)==0){
                    return false;
                }
                i=i.add(BigInteger.valueOf(2));
            }
            
            return true;
	}
    
}

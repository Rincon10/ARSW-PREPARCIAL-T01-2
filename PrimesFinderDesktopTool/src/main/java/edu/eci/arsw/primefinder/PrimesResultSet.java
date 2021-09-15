/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author hcadavid
 */


//NO MODIFICAR ESTA CLASE!!!!!!!!!!!!
// !!!!!!!!!!!!
// !!!!!!!!!!!!



public class PrimesResultSet {

    //NO MODIFICAR ESTA CLASE!!!!!!!!!!!!
        
    private final Collection<BigInteger> primes;
    private String username;

    public PrimesResultSet(String username) {
        this.primes = new ConcurrentLinkedQueue<>();
        this.username=username;
    }
    
    
    private final Map<String,Integer> threadHits=new ConcurrentHashMap<>();
    AtomicInteger accessCount=new AtomicInteger(0);
    String lastConfig=null;
    
    
    public void addPrime(BigInteger bi){
        threadHits.computeIfPresent(Thread.currentThread().getName(), (k, v) -> v + 1);
        threadHits.putIfAbsent(Thread.currentThread().getName(), 1);        
        primes.add(bi);
        
        ResultReporter.report(username, bi);
        
        if (System.getProperty("threadsinfo")!=null &&  System.getProperty("threadsinfo").compareToIgnoreCase("true")==0) {
            lastConfig=threadHits.toString();            
            accessCount.incrementAndGet();            
        }        
    }
    
    public Collection getPrimes(){
        
        if (System.getProperty("threadsinfo")!=null &&  System.getProperty("threadsinfo").compareToIgnoreCase("true")==0) {
            System.out.println("Total threads:"+threadHits.keySet().size());
            System.out.println(lastConfig);
            System.out.println("** REAL Access count:"+accessCount.get());
        }
        
        return primes;
    }    

    
    
    
}

//NO MODIFICAR ESTA CLASE!!!!!!!!!!!!
// !!!!!!!!!!!!
// !!!!!!!!!!!!


class ResultReporter {

    public static void report(String user,BigInteger prime) {
        java.lang.String payload = "{"
                + "\"user\": \""+user+"\", "
                + "\"prime\": \""+prime+"\" "                
                + "}";
        
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("http://localhost:8080/primes");
            request.setEntity(entity);

            HttpResponse response;
            response = httpClient.execute(request);
            System.out.println(response.getStatusLine().getStatusCode());
            
            
        } catch (IOException ex) {
            //Logger.getLogger(PrimesResultSet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

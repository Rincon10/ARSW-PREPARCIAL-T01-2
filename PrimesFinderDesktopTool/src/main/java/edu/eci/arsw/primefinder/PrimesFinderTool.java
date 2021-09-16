package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.primefinder.threads.PrimeFinderThread;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {
    public static List<PrimeFinderThread> threads = new ArrayList<>();

    public static void prepareThreads( int numberOfThreads, int a ,int b,int size, PrimesResultSet set){
        int start = a;
        int end;

        int partition = size/numberOfThreads;
        for (int i = 0; i < numberOfThreads-1; i++) {
            end = start + partition;
            end += ( i == 0 ) ? size%numberOfThreads:0;
            threads.add( new PrimeFinderThread(new BigInteger(start+""), new BigInteger(end+""), set));
//            System.out.println(start+" "+end);
            start = end;
        }
        end = b;
//        System.out.println(start+" "+end);
        threads.add( new PrimeFinderThread(new BigInteger(start+""), new BigInteger(end+1+""), set));

    }

    public static void resumeThreads() {
        System.out.println("=============================Resuming=============================");
        threads.forEach( t -> t.resumeThread());
    }

    public static void stopThreads() {
        System.out.println("=============================Stopping=============================");
        threads.forEach( t -> t.stopThread());
    }

	public static void main(String[] args) {
		            
            int maxNumber=100;
            int minNumber=1;
            int size = Math.abs(maxNumber-minNumber);
            int numberOfThreads = 4;
            PrimesResultSet prs=new PrimesResultSet("john");
            prepareThreads(numberOfThreads,minNumber,maxNumber, size, prs);


            System.out.println("Prime numbers found:");
            while(true){
                try {
                    //check every 10ms if the idle status (10 seconds without mouse
                    //activity) was reached.
                    Thread.sleep(1000);
                    if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement()>10000){
//                        System.out.println("Idle CPU ");
                        stopThreads();
                        if (endProgram())break;
                    }
                    else{
//                        System.out.println("User working again!");
                        resumeThreads();
                        System.out.println(prs.getPrimes());
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("==========================termine====================================");
            System.out.println(prs.getPrimes());

	}

    private static boolean endProgram() {
        System.out.println("escriba exit si desea salir");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return line.equals("exit");
    }

}



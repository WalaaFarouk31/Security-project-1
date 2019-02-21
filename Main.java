package ccAttack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.NANOS;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File("msg.txt");
        Scanner myObj = new Scanner(file);  // Create a Scanner object
        String msg = myObj.nextLine();  // Read user input
        System.out.println(msg);
        for (int i=3;i<=12;++i) {
            Random rnd = new Random();
            int bitLength = (1<<i);
            RSA rsa = new RSA(BigInteger.probablePrime(bitLength,rnd),BigInteger.probablePrime(bitLength,rnd));
            rsa.calcKeys();
            Sender sender = new Sender();
            LocalTime t1 = java.time.LocalTime.now();
            String result = sender.encryptMsg(msg,rsa.n,rsa.e);
            LocalTime t2 = java.time.LocalTime.now();
            System.out.println(NANOS.between(t1,t2));
           // System.out.println(rsa.decryptMsg(result));
        }
    }
}

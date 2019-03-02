/*
Author:Ghada
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Scanner;

public class RSA {

    public BigInteger P,Q,phi,k,n,d;
    public Integer e = 3;
    public RSA(BigInteger a,BigInteger b){
        P = a;
        Q = b;
    }
    public RSA(){
    }



    public void calcKeys() throws FileNotFoundException, UnsupportedEncodingException {
        n = P.multiply(Q);
        BigInteger negOne = BigInteger.valueOf(-1);
        phi = P.add(negOne).multiply(Q.add(negOne));
        while (!(phi.gcd(BigInteger.valueOf(e)).equals(BigInteger.valueOf(1)) && n.gcd(BigInteger.valueOf(e)).equals(BigInteger.valueOf(1))))
            e++;
        k = BigInteger.valueOf(2);
        d = BigInteger.valueOf(e).modInverse(phi);
        PrintWriter pw1 = new PrintWriter("public.txt","UTF-8");
        PrintWriter pw2 = new PrintWriter("private.txt","UTF-8");
        pw1.println(n);
        pw1.println(e);
        pw2.println(d);
        pw1.close();
        pw2.close();
    }

    public void readKeys() throws FileNotFoundException {
        Scanner sc1 = new Scanner(new File("public.txt"));
        Scanner sc2 = new Scanner(new File("private.txt"));
        n = sc1.nextBigInteger();
        e = sc1.nextInt();
        d = sc2.nextBigInteger();
    }
        public String decryptMsg(String s){
        String org_msg =  "";
        String tmp="";
        String res2 = "";
        for(int i=0;i<s.length();++i){
            if (s.charAt(i)=='-'){
                BigInteger num = new BigInteger(tmp);
                String msg = num.modPow(d,n).toString();
                org_msg += msg;
                tmp = "";
            }else tmp+=s.charAt(i);
        }
        if (tmp.length()>0){
            BigInteger num = new BigInteger(tmp);
            String msg = num.modPow(d,n).toString();
            org_msg += msg;
        }
        return org_msg;

    }
    public String toAscii(String msg){
        String res = "";
        String tmp = "";
        for (int i=0;i<msg.length();i+=3){
            if (msg.charAt(i) > '1')
                tmp = msg.substring(i,i+2);
            else tmp = msg.substring(i,i+3);
            res+= Character.toString((char)(Integer.parseInt(tmp)));
        }
        return res;
    }

}

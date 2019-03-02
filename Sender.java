/*
Author:Ghada
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Sender {

    public static String encryptMsg(String toBeEnc,BigInteger n , Integer e){
        String msg = new String();
        String res = "";
        for (int i=0;i<toBeEnc.length();++i){
            Integer tmp = (int)toBeEnc.charAt(i);
            String num_str = tmp.toString();
            //String tmp = num.toString();
            if (num_str.length() < 3)
                num_str += '0';
            //System.out.println(num);
            BigInteger num = new BigInteger(num_str);
            if ((new BigInteger(msg+num.toString())).compareTo(n) == 1){
                BigInteger bi = new BigInteger(msg);
                bi = bi.modPow(BigInteger.valueOf(e),n);
                res += bi.toString();
                res += '-';

                msg = num.toString();
            }else msg += num.toString();
        }

        BigInteger bi = new BigInteger(msg);
        bi = bi.modPow(BigInteger.valueOf(e),n);
        res += bi.toString();
        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {
        BigInteger n;
        Integer e;
        File file = new File("public.txt");
        Scanner sc = new Scanner(file);
        n = sc.nextBigInteger();
        e = sc.nextInt();
        String msg = "Hello Ghada!";
        String encMsg = encryptMsg(msg,n,e);
        System.out.println("encMsg "+encMsg);
        PrintWriter pw = new PrintWriter("recived.txt");
        pw.print(encMsg);
        pw.close();
    }
}


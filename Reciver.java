package ccAttack;
import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class Reciver {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("keys.txt"));
        BigInteger P = sc.nextBigInteger();
        BigInteger Q = sc.nextBigInteger();
    	RSA rsa = new RSA(P,Q);
        rsa.calcKeys();
        File file = new File("recived.txt");
        Scanner sc2 = new Scanner(file);
        String encMsg = sc2.nextLine();
        String org_msg=rsa.decryptMsg(encMsg);
        PrintWriter bf = new PrintWriter("msg.txt","UTF-8");
        bf.write(rsa.toAscii(org_msg));
        System.out.println(rsa.toAscii(org_msg));
    }
}

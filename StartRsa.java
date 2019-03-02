import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Scanner;

public class StartRsa {
    public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("keys.txt"));
        BigInteger P = sc.nextBigInteger();
        BigInteger Q = sc.nextBigInteger();
        RSA rsa = new RSA(P,Q);
        rsa.calcKeys();
    }
}

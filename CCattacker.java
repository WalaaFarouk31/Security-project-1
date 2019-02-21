package ccAttack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Scanner;

public class CCattacker {
	private static BigInteger CCA(BigInteger C, int e, BigInteger n, RSA rsa)
			throws FileNotFoundException, UnsupportedEncodingException {

		BigInteger M, Cdash, rpowe, Cdashd;
		// int r;
		M = BigInteger.valueOf(10); /// hashelha s 7ta 3shan ashel lerror

		// Random rand = new Random();
		// rand.nextInt((max - min) + 1) + min;
		// r=rand.nextInt(4) + 2;
		rpowe = (BigInteger.valueOf(2)).pow(e);
		Cdash = (C.multiply(rpowe)).mod(n);

		
		Cdashd = new BigInteger(rsa.decryptMsg(Cdash.toString()));
		M = Cdashd.divide(BigInteger.valueOf(2));

		return M;
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		RSA rsa = new RSA(BigInteger.valueOf(524287), BigInteger.valueOf(6700417));
		rsa.calcKeys();
		BigInteger C = null, n = null;
		int e = 1;
		String str = "";
		Scanner scanner, scanner2;
		File file = new File("public.txt");
		File sfile = new File("Sentmsg.txt");
		try {
			scanner = new Scanner(file);
			scanner2 = new Scanner(sfile);

			n = scanner.nextBigInteger();
			e = scanner.nextInt();
			str = scanner2.nextLine();

			scanner.close();
		} catch (FileNotFoundException er) {
			er.printStackTrace();
		}
		String tmp = "";
		String res = "";
		for (int i = 0; i < str.length(); ++i) {
			if (str.charAt(i) == '-') {
				BigInteger M = CCA(new BigInteger(tmp), e, n, rsa);
				res += M.toString();
				tmp = "";
			} else
				tmp += str.charAt(i);
		}
		if (tmp.length() > 0) {
			BigInteger M = CCA(new BigInteger(tmp), e, n, rsa);
			res += M.toString();
		}

		String org_msg = "";
		tmp = "";
		for (int i = 0; i < res.length(); i += 3) {
			if (res.charAt(i) > '1')
				tmp = res.substring(i, i + 2);
			else
				tmp = res.substring(i, i + 3);
			org_msg += Character.toString((char) (Integer.parseInt(tmp)));
		}
		System.out.println("The msg is: " + org_msg);

	}

}

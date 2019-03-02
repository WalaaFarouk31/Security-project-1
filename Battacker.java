/*
Author:Walaa
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.time.Duration;
import java.time.Instant;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Battacker {

	private static BigInteger get_phi(BigInteger n) {

		BigInteger one = BigInteger.valueOf(1);
		BigInteger two = BigInteger.valueOf(2);
		BigInteger i, z;
		BigInteger phi = null;

		// search for prime numbers
		for (i = two; i.compareTo(n) == -1; i = i.add(BigInteger.ONE)) {
			if (n.mod(i).compareTo(BigInteger.ZERO) != 0)
				continue;
			if (checkprime(i)) {
				z = n.divide(i);
				if (checkprime(z)) {
					System.out.println("p: " + i);
					System.out.println("q: " + z);
					phi = (i.subtract(one)).multiply(z.subtract(one));
					break;
				}
			}

		}

		System.out.println("phi: " + phi);
		return phi;
	}

	private static boolean checkprime(BigInteger i) {

		BigInteger zero = BigInteger.valueOf(0);
		BigInteger one = BigInteger.valueOf(1);
		BigInteger two = BigInteger.valueOf(2);
		boolean flag = true;

		if (i.compareTo(two) == 0)
			return true;
		if (i.mod(two).compareTo(zero) == 0)
			return false;
		for (BigInteger j = two; (j.multiply(j)).compareTo(i) == -1; j = j.add(one)) {

			if (j.mod(i).compareTo(zero) == 0) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public static void main(String[] args) {

		BigInteger n = null, d = null;
		int e = 1;
		String msg="";
		Scanner scanner,scanner2;
		File file = new File("public.txt");
		File sfile = new File("Sentmsg.txt");
		try {
			scanner = new Scanner(file);
			scanner2 = new Scanner(sfile);

			n = scanner.nextBigInteger();
			e = scanner.nextInt();
			msg = scanner2.nextLine();

			scanner.close();
		} catch (FileNotFoundException er) {
			er.printStackTrace();
		}

		// Instant start = Instant.now();
		long lStartTime = System.currentTimeMillis();

		BigInteger phi = get_phi(n);
		if (BigInteger.valueOf(e).gcd(phi).equals(BigInteger.ONE))
			d = BigInteger.valueOf(e).modInverse(phi);
		else
			System.out.println("nop");


		long lEndTime = System.currentTimeMillis();

		System.out.println("d= " + d);
		long output = lEndTime - lStartTime;
		System.out.println("time= " + output);

		String m=decryptMsg(msg,d,n);
		System.out.println("The original message is: "+ toAscii(m));

	}

	public static String decryptMsg(String s,BigInteger d,BigInteger n) {
		String org_msg = "";
		String tmp = "";
		String res2 = "";
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == '-') {
				BigInteger num = new BigInteger(tmp);
				String msg = num.modPow(d, n).toString();
				org_msg += msg;
				tmp = "";
			} else
				tmp += s.charAt(i);
		}
		if (tmp.length() > 0) {
			BigInteger num = new BigInteger(tmp);
			String msg = num.modPow(d, n).toString();
			org_msg += msg;
		}
		return org_msg;

	}

	public static String toAscii(String msg) {
		String res = "";
		String tmp = "";
		for (int i = 0; i < msg.length(); i += 3) {
			if (msg.charAt(i) > '1')
				tmp = msg.substring(i, i + 2);
			else
				tmp = msg.substring(i, i + 3);
			res += Character.toString((char) (Integer.parseInt(tmp)));
		}
		return res;
	}

}

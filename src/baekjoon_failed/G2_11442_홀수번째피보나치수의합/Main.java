package baekjoon_failed.G2_11442_홀수번째피보나치수의합;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
	
	static long N;
	static final long M = 1_000_000_007;
	static HashMap<Long, Long> memo = new HashMap<>();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextLong();
		
		long sum = 0;
		
		for (long n = 1; n <= N; n += 2) {
			sum = (sum + fibo(n)) % M;
		}

		System.out.println(sum);
		
	}
	
	static long fibo(long n) {
		
		if (memo.containsKey(n))
			return memo.get(n);
		
		if (n == 0 || n == 1)
			return n;
		
		long K = n / 2;
		long result;
		
        if (n % 2 == 0) {
            long fbK = fibo(K);
            long fbKplus1 = fibo(K - 1);
            result = (fbK * ((2 * fbKplus1) % M + fbK) % M) % M;
        } else {
            long fbK = fibo(K);
            long fbKplus1 = fibo(K + 1);
            result = (fbK * fbK % M + fbKplus1 * fbKplus1 % M) % M;
        }
        
        if (n % 2 != 0)
        	memo.put(n, result);
        
        return result;
		
	}
}
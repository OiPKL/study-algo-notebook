package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N, cnt = 0;
	static int[] arr1, arr2;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr1 = new int[N];
		arr2 = new int[N];

		String str1 = br.readLine();
		String str2 = br.readLine();
		for (int n = 0; n < N; n++) {

			int num1 = str1.charAt(n);
			int num2 = str2.charAt(n);

			if (num1 != num2) {

				
			}
		}
    }
}
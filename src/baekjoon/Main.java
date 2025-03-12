package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] before = new int[N];
		int[] after = new int[N];

		String line = br.readLine();
		for (int i = 0; i < N; i++)
			before[i] = line.charAt(i);
		line = br.readLine();
		for (int i = 0; i < N; i++)
			after[i] = line.charAt(i);
    }
}
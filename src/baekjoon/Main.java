package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	static int[] dn = {-1, 1, 0, 0};
	static int[] dm = {0, 0, -1, 1};

	static int N, M, K, time = -1;
	static int[][] map, sharkNM;
	static int[][][] sharkPQ;
	static HashSet<Integer> sharkList;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		sharkNM = new int[M + 1][3];
		sharkPQ = new int[M + 1][4][4];
		sharkList = new HashSet<>();

		/*
		map			냄새
		sharkNM		상어 | n, m, d
		sharkPQ		상어 | 방향 | 우선순위
		sharkList	생존상어
		sharkDelete	삭제상어
		sharkCheck	삭제체크
		 */
		
		// HashMap<mapKey, time>[M+1] 추가 필요

		for (int n1 = 0; n1 < N; n1++) {
			st = new StringTokenizer(br.readLine());
			for (int n2 = 0; n2 < N; n2++) {
				int num = Integer.parseInt(st.nextToken());
				sharkList.add(num);
				if (num != 0) {
					sharkNM[num][0] = n1;
					sharkNM[num][1] = n2;
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int m = 1; m <= M; m++)
			sharkNM[m][2] = Integer.parseInt(st.nextToken());

		for (int m = 1; m <= M; m++) {
			for (int d1 = 0; d1 < 4; d1++) {
				st = new StringTokenizer(br.readLine());
				for (int d2 = 0; d2 < 4; d2++)
					sharkPQ[m][d1][d2] = Integer.parseInt(st.nextToken());
			}
		}

		for (int t = 1; t <= 1000; t++) {

			HashSet<Integer> sharkDelete = new HashSet<>();
			HashMap<Integer, Integer> sharkCheck = new HashMap<>();

			for (int shark : sharkList) {

			}
		}

		System.out.println(time);
	}
}
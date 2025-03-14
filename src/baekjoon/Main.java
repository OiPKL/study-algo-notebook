package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[] dn = {-1, 1, 0, 0};
	static int[] dm = {0, 0, -1, 1};
	static int N, M;
	static int[] B, R, O;
	static char[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		for (int n = 0; n < N; n++) {
			String line = br.readLine();
			for (int m = 0; m < M; m++) {
				map[n][m] = line.charAt(m);
				if (map[n][m] == 'B')
					B = new int[] {n, m};
				if (map[n][m] == 'R')
					R = new int[] {n, m};
				if (map[n][m] == 'O')
					O = new int[] {n, m};
			}
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[4] - b[4]);
		pq.add(new int[] {B[0], B[1], R[0], R[1], 0});

		int answer = -1;
		while (!pq.isEmpty()) {

			int[] now = pq.poll();
			int b0Now = now[0];
			int b1Now = now[1];
			int r0Now = now[2];
			int r1Now = now[3];
			int cnt = now[4];

			if (cnt > 10)
				break;

			if (r0Now == O[0] && r1Now == O[1]){
				answer = cnt;
				break;
			}

			int position = getPosition(b0Now, b1Now, r0Now, r1Now);
			switch (position) {
				case 1: // R B R B
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 0);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 0);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] blueMoved = move(b0Now, b1Now, r0Now, r1Now, 1);
					int[] redMoved = move(r0Now, r1Now, -1, -1, 1);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 2);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 2);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] blueMoved = move(b0Now, b1Now, r0Now, r1Now, 3);
					int[] redMoved = move(r0Now, r1Now, -1, -1, 3);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				break;
				case 2: // R B - -
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 0);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 0);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] blueMoved = move(b0Now, b1Now, r0Now, r1Now, 1);
					int[] redMoved = move(r0Now, r1Now, -1, -1, 1);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 2);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 2);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 3);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 3);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				break;
				case 3: // R B B R
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 0);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 0);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] blueMoved = move(b0Now, b1Now, r0Now, r1Now, 1);
					int[] redMoved = move(r0Now, r1Now, -1, -1, 1);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] blueMoved = move(b0Now, b1Now, r0Now, r1Now, 2);
					int[] redMoved = move(r0Now, r1Now, -1, -1, 2);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 3);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 3);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				break;
				case 4: // - - R B
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 0);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 0);
					pq.add(new int[]{blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 1);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 1);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 2);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 2);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] blueMoved = move(b0Now, b1Now, r0Now, r1Now, 3);
					int[] redMoved = move(r0Now, r1Now, -1, -1, 3);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				break;
				case 6: // - - B R
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 0);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 0);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 1);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 1);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] blueMoved = move(b0Now, b1Now, r0Now, r1Now, 2);
					int[] redMoved = move(r0Now, r1Now, -1, -1, 2);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 3);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 3);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				break;
				case 7: // B R R B
				{
					int[] blueMoved = move(b0Now, b1Now, r0Now, r1Now, 0);
					int[] redMoved = move(r0Now, r1Now, -1, -1, 0);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 1);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 1);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 2);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 2);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] blueMoved = move(b0Now, b1Now, r0Now, r1Now, 3);
					int[] redMoved = move(r0Now, r1Now, -1, -1, 3);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				break;
				case 8: // B R - -
				{
					int[] blueMoved = move(b0Now, b1Now, r0Now, r1Now, 0);
					int[] redMoved = move(r0Now, r1Now, -1, -1, 0);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 1);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 1);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 2);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 2);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 3);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 3);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				break;
				case 9: // B R B R
				{
					int[] blueMoved = move(b0Now, b1Now, r0Now, r1Now, 0);
					int[] redMoved = move(r0Now, r1Now, -1, -1, 0);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 1);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 1);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] blueMoved = move(b0Now, b1Now, r0Now, r1Now, 2);
					int[] redMoved = move(r0Now, r1Now, -1, -1, 2);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				{
					int[] redMoved = move(r0Now, r1Now, b0Now, b1Now, 3);
					int[] blueMoved = move(b0Now, b1Now, -1, -1, 3);
					pq.add(new int[] {blueMoved[0], blueMoved[1], redMoved[0], redMoved[1], cnt + 1});
				}
				break;
			}
		}

		System.out.println(answer);
	}

	static int getPosition(int B0, int B1, int R0, int R1) {

		if (B0 < R0) {
			if (B1 < R1) 		return 1;
			else if (B1 == R1) 	return 2;
			else 				return 3;
		} else if (B0 == R0) {
			if (B1 < R1) 		return 4;
			else                return 6;
		} else {
			if (B1 < R1) 		return 7;
			else if (B1 == R1) 	return 8;
			else 				return 9;
		}
	}

	static int[] move(int f0, int f1, int l0, int l1, int d) {

		while (true) {

			int nf0 = f0 + dn[d];
			int nf1 = f1 + dm[d];

			if (map[nf0][nf1] != '.' || nf0 == l0 && nf1 == l1)
				break;

			f0 = nf0;
			f1 = nf1;
		}

		return new int[] {f0, f1};
	}
}

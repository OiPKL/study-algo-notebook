package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
        boolean[][][] visited = new boolean[N][M][2];
		
		@SuppressWarnings("unchecked")
		List<Integer>[] listN = new ArrayList[N];				// axis 1
		@SuppressWarnings("unchecked")
		List<Integer>[] listM = new ArrayList[M];				// axis 0
		
		for (int n = 0; n < N; n++)
			listN[n] = new ArrayList<>();
		for (int m = 0; m < M; m++)
			listM[m] = new ArrayList<>();
		
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int btnM = Integer.parseInt(st.nextToken()) - 1;
			int btnN = Integer.parseInt(st.nextToken()) - 1;

			listN[btnN].add(btnM);
			listM[btnM].add(btnN);
		}
		
		// 종료지점 스위치 CASE
        if (!listN[N-1].contains(M-1)) {
            listN[N-1].add(M-1);
            listM[M-1].add(N-1);
        }
		
		PriorityQueue<int[]> bfs = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		bfs.add(new int[] {0, 0, 0, 0});
		
		// 시작지점 스위치 CASE
		if (listN[0].contains(0))
			bfs.add(new int[] {0, 0, 1, 1});
		
		while (!bfs.isEmpty()) {
			
			int[] now = bfs.poll();
			int nNow = now[0];
			int mNow = now[1];
			int time = now[2];
			int axis = now[3];
			
//			for (int i = 0; i <= 3; i++)
//				System.out.print(now[i] + " ");
//			System.out.println();
			
			if (visited[nNow][mNow][axis])
				continue;
			
			visited[nNow][mNow][axis] = true;
			
			if (nNow == N - 1 && mNow == M -1) {
				System.out.println(time - 1);
				return;
			}

			if (axis == 0) {
				
				for (int nNext : listM[mNow]) {
					
					if (!visited[nNext][mNow][1]) {
						
						int tmp = time + Math.abs(nNext - nNow);
						if (bfs.size() < 100)
							bfs.add(new int[] {nNext, mNow, tmp + 1, 1});
					}
				}
			} else {
				
				for (int mNext : listN[nNow]) {
					
					if (!visited[nNow][mNext][1]) {
						
						int tmp = time + Math.abs(mNext - mNow);
						if (bfs.size() < 100)
							bfs.add(new int[] {nNow, mNext, tmp + 1, 0});
					}
				}
			}
		}
		
		System.out.println(-1);
	}
}
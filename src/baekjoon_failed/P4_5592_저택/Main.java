package baekjoon_failed.P4_5592_저택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][][] visited = new boolean[N][M][2];

        HashMap<Integer, List<Integer>> keys = new HashMap<>();

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int btnM = Integer.parseInt(st.nextToken()) - 1;
            int btnN = Integer.parseInt(st.nextToken()) - 1;

            int keyN = btnN;
            if (!keys.containsKey(keyN))
                keys.put(keyN, new ArrayList<>());
            keys.get(keyN).add(btnM);

            int keyM = 100000 + btnM;
            if (!keys.containsKey(keyM))
                keys.put(keyM, new ArrayList<>());
            keys.get(keyM).add(btnN);
        }

        // 종료지점 스위치 CASE
        if (!(keys.containsKey(N - 1) && keys.get(N - 1).contains(M - 1))) {

            int keyN = N - 1;
            if (!keys.containsKey(keyN))
                keys.put(keyN, new ArrayList<>());
            keys.get(keyN).add(M - 1);

            int keyM = 100000 + (M - 1);
            if (!keys.containsKey(keyM))
                keys.put(keyM, new ArrayList<>());
            keys.get(keyM).add(N - 1);
        }

        PriorityQueue<int[]> bfs = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        bfs.add(new int[]{0, 0, 0, 0});

        // 시작지점 스위치 CASE
        if (keys.containsKey(0) && keys.get(0).contains(0))
            bfs.add(new int[]{0, 0, 1, 1});

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

            if (nNow == N - 1 && mNow == M - 1) {
                System.out.println(time - 1);
                return;
            }

            if (axis == 0) {

                int keyM = 100000 + mNow;
                if (keys.containsKey(keyM)) {
                    for (int nNext : keys.get(keyM)) {
                        if (!visited[nNext][mNow][1]) {
                            int tmp = time + Math.abs(nNext - nNow);
                            bfs.add(new int[]{nNext, mNow, tmp + 1, 1});
                        }
                    }
                }
            } else {

                int keyN = nNow;
                if (keys.containsKey(keyN)) {
                    for (int mNext : keys.get(keyN)) {
                        if (!visited[keyN][mNext][0]) {
                            int tmp = time + Math.abs(mNext - mNow);
                            bfs.add(new int[]{keyN, mNext, tmp + 1, 0});
                        }
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
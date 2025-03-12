package baekjoon_failed.P5_3197_백조의호수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int R, C, wayCnt, minMeltingTime;
	static char[][] map;
	static List<int[]>swans;
	static int[] swan1, swan2;
	static int[][][] visited1;
	static boolean complete;
	static Stack<int[]> validWay;
	static int[][] meltingTimeMap;
	static boolean[][] visited2;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		swans = new ArrayList<>();
		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
				if (map[r][c] == 'L')
					swans.add(new int[] {r, c});
			}
		}
		
		swan1 = swans.get(0);
		swan2 = swans.get(1);
		
		visited1 = new int[R][C][2];
		for (int r = 0; r < R; r++)
			for (int c = 0; c < C; c++)
//				visited1[r][c][0] = Integer.MAX_VALUE;
				visited1[r][c][0] = 9;
		
		// 1. 최소 얼음 부수는 최적의 경로 찾기
		wayCnt = -1;
		findMinBreakingWay();
		
//		System.out.println();
//		System.out.println("visited1[0] : 편의상 visited1[0] 초기화를 9로");
//		for (int r = 0; r < R; r++) {
//			for (int c = 0; c < C; c++)
//				System.out.printf("%2d ", visited1[r][c][0]);
//			System.out.println();
//		}
//		System.out.println();
//		System.out.println("visited1[1]");
//		for (int r = 0; r < R; r++) {
//			for (int c = 0; c < C; c++)
//				System.out.printf("%2d ", visited1[r][c][1]);
//			System.out.println();
//		}
//		System.out.println();
//		System.out.println("wayCnt: " + wayCnt);
		
		// 2. 최적 경로 역추적
		complete = false;
		validWay = new Stack<>();
		findValidWay(swan2[0], swan2[1], wayCnt - 1);
		
//		System.out.println();
//		System.out.println("validWay");
//		while (!validWay.isEmpty())
//			System.out.println(Arrays.toString(validWay.pop()));
		
		// 3. 최적 경로 상의 meltingTime 계산
		minMeltingTime = 0;
		meltingTimeMap = new int[R][C];
		while (!validWay.isEmpty()) {
			
			int[] now = validWay.pop();
			int rNow = now[0];
			int cNow = now[1];
			
			if (map[rNow][cNow] == 'X')
				calMeltingTime(rNow, cNow);
		}
		
//		System.out.println("meltingTimeMap");
//		for (int r = 0; r < R; r++)
//			System.out.println(Arrays.toString(meltingTimeMap[r]));
		
		System.out.println(minMeltingTime);
	}
	
	static void findMinBreakingWay() {
		PriorityQueue<int[]> bfs = new PriorityQueue<>((a, b) -> a[3] - b[3]);
		
		bfs.add(new int[] {swan1[0], swan1[1], 0, 0});
		visited1[swan1[0]][swan1[1]][0] = 0;
		
		while (!bfs.isEmpty()) {
			
			int[] now = bfs.poll();
			int rNow = now[0];
			int cNow = now[1];
			int cnt = now[2];
			int ice = now[3];
			
//			System.out.println(rNow + " | " + cNow + " | " + cnt + " | " + ice);
			
			if (rNow == swan2[0] && cNow == swan2[1]) {
				wayCnt = cnt;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				
				int rNext = rNow + dr[d];
				int cNext = cNow + dc[d];
				
				if (rNext < 0 || R <= rNext || cNext < 0 || C <= cNext) continue;
				
				if (map[rNext][cNext] == 'X') {
					if (ice + 1 < visited1[rNext][cNext][0]) {
						bfs.add(new int[] {rNext, cNext, cnt + 1, ice + 1});
						visited1[rNext][cNext][0] = ice + 1;
						visited1[rNext][cNext][1] = cnt + 1;
					}
				} else {	// . or L
					if (ice < visited1[rNext][cNext][0]) {
						bfs.add(new int[] {rNext, cNext, cnt + 1, ice});
						visited1[rNext][cNext][0] = ice;
						visited1[rNext][cNext][1] = cnt + 1;
					}
				}
			}
		}
	}
	
	static void findValidWay(int rNow, int cNow, int target) {

		if (rNow == swan1[0] && cNow == swan1[1]) {
	        validWay.add(new int[]{rNow, cNow});
	        complete = true;
	        return;
	    }
	    
	    if (complete) return;
	    
	    validWay.add(new int[]{rNow, cNow});
	    
	    List<int[]> nextWay = new ArrayList<>();

	    for (int d = 0; d < 4; d++) {
	    	
			int rNext = rNow + dr[d];
			int cNext = cNow + dc[d];
			
			if (rNext < 0 || R <= rNext || cNext < 0 || C <= cNext) continue;

	        if (visited1[rNext][cNext][1] == target)
	        	nextWay.add(new int[] {rNext, cNext});
	    }
	    
	    nextWay.sort((a, b) -> {
	    	return visited1[a[0]][a[1]][0] - visited1[b[0]][b[1]][0];
	    });
	    
	    for (int[] next : nextWay)
	    	findValidWay(next[0], next[1], target - 1);

	    if (!complete) validWay.pop();
	}
	
	static void calMeltingTime(int r, int c) {
		
		Queue<int[]> bfs = new LinkedList<>();
		visited2 = new boolean[R][C];
		
		bfs.add(new int[] {r, c, 0});
		visited2[r][c] = true;
		
		while (!bfs.isEmpty()) {
			
			int[] now = bfs.poll();
			int rNow = now[0];
			int cNow = now[1];
			int cnt = now[2];
			
			if (map[rNow][cNow] != 'X') {
				minMeltingTime = Math.max(minMeltingTime, cnt);
				meltingTimeMap[r][c] = cnt;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				
				int rNext = rNow + dr[d];
				int cNext = cNow + dc[d];
				
				if (rNext < 0 || R <= rNext || cNext < 0 || C <= cNext ||
					visited2[rNext][cNext]) continue;
				
				bfs.add(new int[] {rNext, cNext, cnt + 1});
				visited2[rNext][cNext] = true;
			}
		}
	}
}

/*

1 2
LL
0

2 1
L
L
0

1 7
LXX.XXL
= 1

 */
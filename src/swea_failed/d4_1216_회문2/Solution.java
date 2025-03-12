package swea_failed.d4_1216_회문2;

import java.util.Scanner;

public class Solution {
	
	static int[] isSame(int rNow, int cNow, int way) {
		// 델타배열(way) : 상(0) 하(1) 좌(2) 우(3)
		int[] rWay = {-1, 1, 0, 0};
		int[] cWay = {0, 0, -1, 1};

		int[] next = new int[2];
		next[0] = rNow + rWay[way];	// >> upNext && downNext
		next[1] = cNow + cWay[way];	// >> leftNext && rightNext
		// 바운더리 에러 -> 조사 종료 -99 반환
		if (next[0] < 0 && next[0] >= 100 && next[1] < 0 && next[1] >= 100)
			return new int[] {-99, -99};
		else
			return next;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for (int t = 1; t <= 1; t++) {
			
			int T = sc.nextInt();
			
			char[][] nemo = new char[100][100];
			
			for (int n = 0; n < 100; n++) {
				nemo[n] = sc.next().toCharArray();
			}
			
//			for (int r = 0; r < 100; r++) {
//				for (int c = 0; c < 100; c++) {
//					System.out.print(nemo[r][c]);
//				}
//				System.out.println();
//			}

			// isSame 호출 -> next[0], next[1] 반환
			int[] upNext = new int[2];		// way = 0[0]
			int[] upNext2 = new int[2];		// way = 0[0]
			int[] downNext = new int[2];	// way = 1[0]
			int[] downNext2 = new int[2];	// way = 1[0]
			int[] leftNext = new int[2];	// way = 2[1]
			int[] leftNext2 = new int[2];	// way = 2[1]
			int[] rightNext = new int[2];	// way = 3[1]
			int[] rightNext2 = new int[2];	// way = 3[1]
			int max = 0;
			int cnt = 1;	// 나 자신 +1
			int rNow = 0;
			int cNow = 0;
			for (int r = 5; r < 95; r++) {
				for (int c = 5; c < 95; c++) {
					// (상하)ABCBA ..
					cnt = 1;
					rNow = r;
					cNow = c;
					upNext = isSame(rNow, cNow, 0);
					downNext = isSame(rNow, cNow, 1);
					if (upNext[0] != -99 && downNext[0] != -99 && upNext[0] == downNext[0]) {
						while (upNext[0] != -99 && downNext[0] != -99 && upNext[0] == downNext[0])
							cnt += 2;
					}
					max = Math.max(max, cnt);
					// (좌우)ABCBA ..
					cnt = 1;
					rNow = r;
					cNow = c;
					leftNext = isSame(rNow, cNow, 2);
					rightNext = isSame(rNow, cNow, 3);
					if (leftNext[1] != -99 && rightNext[1] != -99 && leftNext[1] == rightNext[1]) {
						while (leftNext[1] != -99 && rightNext[1] != -99 && leftNext[1] == rightNext[1])
							cnt += 2;
					}
					max = Math.max(max, cnt);
					// (상)ABCCBA ..
					cnt = 1;
					rNow = r;
					cNow = c;
					upNext = isSame(rNow, cNow, 0);
					upNext2 = isSame(rNow-1, cNow, 0);
					downNext = isSame(rNow, cNow, 1);
					if (upNext[0] != -99 && upNext[0] == nemo[rNow][cNow]) {
						cnt++;			// CC
						if (upNext2[0] != -99 && downNext[0] != -99 && upNext2[0] == downNext[0])
							cnt += 2;	// BB
						while (upNext[0] != -99 && downNext[0] != -99 && upNext[0] == downNext[0])
							cnt += 2;	// AA ..
					}
					max = Math.max(max, cnt);
					// (하)ABCCBA ..
					cnt = 1;
					rNow = r;
					cNow = c;
					upNext = isSame(rNow, cNow, 0);
					downNext = isSame(rNow, cNow, 1);
					downNext2 = isSame(rNow+1, cNow, 1);
					if (downNext[0] != -99 && downNext[0] == nemo[rNow][cNow]) {
						cnt++;			// CC
						if (upNext[0] != -99 && downNext2[0] != -99 && upNext[0] == downNext2[0])
							cnt += 2;	// BB
						while (upNext[0] != -99 && downNext[0] != -99 && upNext[0] == downNext[0])
							cnt += 2;	// AA ..
					}
					max = Math.max(max, cnt);
					// (좌)ABCCBA ..
					cnt = 1;
					rNow = r;
					cNow = c;
					leftNext = isSame(rNow, cNow, 2);
					leftNext2 = isSame(rNow, cNow-1, 2);
					rightNext = isSame(rNow, cNow, 3);
					if (leftNext[1] != -99 && leftNext[1] == nemo[rNow][cNow]) {
						cnt++;			// CC
						if (leftNext2[1] != -99 && rightNext[1] != -99 && leftNext2[1] == rightNext[1])
							cnt += 2;	// BB
						while (leftNext[1] != -99 && rightNext[1] != -99 && leftNext[1] == rightNext[1])
							cnt += 2;	// AA ..
					}
					max = Math.max(max, cnt);
					// (우)ABCCBA ..
					cnt = 1;
					rNow = r;
					cNow = c;
					leftNext = isSame(rNow, cNow, 2);
					rightNext = isSame(rNow, cNow, 3);
					rightNext2 = isSame(rNow, cNow+1, 3);
					if (rightNext[1] != -99 && rightNext[1] == nemo[rNow][cNow]) {
						cnt++;			// CC
						if (leftNext[1] != -99 && rightNext2[1] != -99 && leftNext2[1] == rightNext2[1])
							cnt += 2;	// BB
						while (leftNext[1] != -99 && rightNext[1] != -99 && leftNext[1] == rightNext[1])
							cnt += 2;	// AA ..
					}
					max = Math.max(max, cnt);
				}
			}
			
			System.out.println("#" + T + " " + max);
		}
		
	}
}
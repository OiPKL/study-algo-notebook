package swea;

import java.util.Scanner;

public class Solution {
	
	static int check = (1 << 10) - 1;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {
			
			int N = sc.nextInt();
			int cnt = 0;
			int shp = 0;
			int tmp = 0;
			
			while (true) {
				cnt++;
				shp = N * cnt;
				
				while (shp > 0) {
					tmp = (1 << (shp % 10));
					shp /= 10;
				}
				
				if (tmp == check) break;
			}
			
			System.out.println("#" + tc + " " + cnt);
			
		}//tc
	}
}
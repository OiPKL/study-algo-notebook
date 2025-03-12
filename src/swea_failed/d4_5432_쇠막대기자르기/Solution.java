package swea_failed.d4_5432_쇠막대기자르기;

import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			
			Stack<Character> stack = new Stack<>();
			
			char[] makdae = sc.nextLine().toCharArray();
			int[] marker = new int[makdae.length];	// >> 레이저 제외
			
			// 막대기의 시작과 끝 1로 표시 -> 레이저는 --00--
			for (int i = 0; i < makdae.length; i++) {
				
				if (makdae[i] == '(') {
					if (makdae[i+1] == ')')
						continue;
					else
						marker[i] = 1;
				} else {
					if (makdae[i-1] == '(')
						continue;
					else
						marker[i] = 1;
				}
				
			}
			
			for (int num : marker)
				System.out.print(num + " ");
			System.out.println();
		
		}
		
	}
}
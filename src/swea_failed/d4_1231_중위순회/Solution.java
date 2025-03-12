package swea_failed.d4_1231_중위순회;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			
			int N = sc.nextInt();
			sc.nextLine();
			String[] tree = new String[N+1];
			
			for (int i = 1; i < N+1; i++) {
				String str = sc.nextLine();
				String[] word = str.split(" ");
				tree[i] = word[1];
			}
			
			System.out.println("#" + tc + " ");
			inorder(tree, 1);
			System.out.println();
			
		}
	}

	static void inorder(String[] tree, int root) {
		if (root >= tree.length || tree[root] == null)
			return;
		inorder(tree, root * 2);
		System.out.print(tree[root]);
		inorder(tree, root * 2 + 1);
	}
	
}
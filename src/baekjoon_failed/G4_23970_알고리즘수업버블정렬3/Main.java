package baekjoon_failed.G4_23970_알고리즘수업버블정렬3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long[] arr1 = new long[N];
        long[] arr2 = new long[N];
        
        for (int n = 0; n < N; n++) {
        	arr1[n] = sc.nextInt();
        }
        
        for (int n = 0; n < N; n++) {
        	arr2[n] = sc.nextInt();
        }
        
        for (int i = arr1.length-1; i > 0; i--) {
        	for (int j = 0; j < i; j++) {
        		if (arr1[j] > arr1[j+1]) {
        			long tmp = arr1[j+1];
        			arr1[j+1] = arr1[j];
        			arr1[j] = tmp;
        		}
        		
        		boolean isEqual = true;
        		for (int k = 0; k < N; k++) {
        			if (arr1[k] != arr2[k]) {
        				isEqual = false;
        				break;
        			}
        		}
        		
        		if (isEqual) {
        			System.out.println(1);
        			return;
        		}
        		
        	}
		}
		System.out.println(0);
    }
}
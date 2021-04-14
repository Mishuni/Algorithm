import java.util.Arrays;
import java.util.Scanner;
public class Bj10815 {
	public static int N,M;
	public static void main(String[] args) {
	    
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int[] list = new int[N];
		for(int i=0; i<N; i++) {
			list[i] = sc.nextInt();
		}
		Arrays.sort(list);
		M = sc.nextInt();
		for(int j=0; j<M; j++) {
			System.out.print(binarySearch(0,N-1,sc.nextInt(),list)+" ");
		}
		sc.close(); sc= null;
	}
	
	public static int binarySearch(int left, int right, int n, int[] list) {
		if(left>right) return 0;
		
		int half = (left+right)/2;
		
		if(list[half]==n) {
			return 1;
		}
		else if(list[half]>n) {
			return binarySearch(left,half-1,n,list);
			
		}else {
			return binarySearch(half+1,right,n,list);
		}
		
	}

}

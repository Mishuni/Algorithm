import java.util.Scanner;

public class Bj2805 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] trees = new int[N];
		int res = 0;
		int right = 0; int left =0 ; 
		for (int i = 0; i < N; i++) {
			trees[i] = sc.nextInt();
			if (trees[i] > right) {
				right = trees[i];
			}
		}
		while(left<=right) {
			// 합을 구해서 행동 취하기
			int half = (left + right) /2;
			int sum = remain(trees, half, M);
			if(sum==1) {
				// 1-1 합이 M보다 크면
				if(res<half)
					res = half;
				left = half+1;
			}
			else if(sum==-1)  
				{
				// 1-2 합이 M보다 작으면
				right = half-1;
			}
			else {
				// 1-3 합이 M과 같으면 
				// 그게 최대 높이니까 바로 break
				res = half;
				break;
			}
		}
		System.out.println(res);
	}

	public static int remain(int[] trees, int half, int M) {
		// sum이 M 보다 커지기만 하면 바로 1 return
		// sum이 M 보다 작으면 -1, 같으면 0 return
		long sum = 0; // 만약 원하는 나무 길이가 매우 크면
		// 합이 int 범위를 넘어 설 수 있기 때문에 long 타입으로 선언
		for (int i = 0; i < trees.length; i++) {
			if (trees[i] > half) {
				sum += trees[i] - half;
				if(sum>M)
					return 1;
			}
		}
		return (sum>M)?1:(sum==M)?0:-1;
	}

}

import java.util.Scanner;

public class Bj7568 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		System.out.println(num);
		
		int[][] list = new int[num][2];
		int[] result = new int[num];

		// Input  받기 및 처음 사람의 덩치와 비교
		for(int i=0; i<num; ++i) {
			list[i][0]=sc.nextInt();
			list[i][1]=sc.nextInt();
			
			for(int j=0; j<i; ++j) {
				
				if(list[i][0]>list[j][0] && list[i][1]>list[j][1]) {
					++result[j];
				}
				else if(list[i][0]<list[j][0] && list[i][1]<list[j][1]) {
					++result[i];
				}
				
			}
			
		}
		
		for(int i=0; i<num; ++i) {
			System.out.print((result[i]+1)+" ");
		}
		

	}

}

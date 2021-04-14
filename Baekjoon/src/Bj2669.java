import java.util.Scanner;

public class Bj2669 {
 	public static void main(String[] args) {
 		Scanner sc = new Scanner(System.in);
 		// 전체 좌표 크기의 배열
 		boolean[][] area = new boolean[101][101];
 		int res = 0;
 		for(int i=0; i<4; i++) {
 			int x1 = sc.nextInt(), y1 = sc.nextInt();
 			int x2 = sc.nextInt(), y2 = sc.nextInt();
 			for(int x=x1; x<x2; ++x) {
 				for(int y=y1; y<y2; ++y) {
 					if(!area[y][x]) {
 						area[y][x] = true;
 						++res;
 					}
 				}
 			}
 		}
 		System.out.println(res);
 		sc.close(); sc= null;
 	}
}

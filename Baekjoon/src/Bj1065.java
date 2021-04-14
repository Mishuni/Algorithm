import java.util.Scanner;

public class Bj1065 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int total = 99;
		if(n<100)
			total = n;
		else if(n>110){
			for(int j=111; j<=n; j++) {
				int f = j/100;
				int s = ( j - f*100 ) / 10;
				int t = (j - f*100 - s*10);
				if(f-s == s-t)
					total++;
			}
		}
		System.out.println(total);
	}
}

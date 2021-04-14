import java.util.Scanner;

public class Bj2869 {
	static int A,B,V,gap;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		V = sc.nextInt();

		int ans = (int)Math.ceil((double)(V-A)/(A-B))+1;
		System.out.println(ans);
		
	}

}

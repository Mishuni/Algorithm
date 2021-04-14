import java.util.Scanner;

public class Sw1926 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
			
		int N = sc.nextInt();
		
		for(int i=1; i<=N; ++i) {
			
			char[] tmp = Integer.toString(i).toCharArray();
			StringBuilder clap = new StringBuilder();
			boolean check = false;
			for(char a : tmp) {
				if(a=='3'|a=='6'|a=='9') {
					clap.append("-");
					check = true;
					
				}
			}
			if(!check) {
				System.out.printf("%d ",i);
			}else {
				System.out.printf("%s ", clap);
			}
		}
	}

}

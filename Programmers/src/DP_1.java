import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DP_1 {

	public static void main(String[] args) {
		int N = 1, number = 8;

		
		if (N == number) {
			System.out.println(1);
			return;
		}
		int[] check = new int[(N==1)?number*10:(int) Math.pow(N, 8)+1];
		Map<Integer,LinkedList<Integer>> count = new HashMap<Integer,LinkedList<Integer>>();
		LinkedList<Integer> tmp = new LinkedList<Integer>();
		tmp.add(N);
		count.put(1,tmp);
		check[N]=1;
		int repeat = N;
		for(int i=2; i<=8; ++i) {
			tmp= new LinkedList<Integer>();
			repeat = repeat*10 + N;
			if(repeat==number) {
				System.out.println(i);
				return;
			}
			tmp.add(repeat);
			
			for(int j=1; j<=i/2; ++j) {
				LinkedList<Integer> test = count.get(j);
				LinkedList<Integer> test2 = count.get(i-j);
				
				for(int t1 = 0; t1<test.size(); ++t1) {
					int num1 = test.get(t1);
					for(int t2=0; t2<test2.size(); ++t2) {
						int num2 = test2.get(t2);
						//System.out.println(num1 + ":"+num2);
						int a = num1 + num2;
						int b = Math.abs(num1-num2);
						int c = num1 * num2 ;
						//System.out.println(c);
						int d = (num1>num2)? num1/num2 : num2/num1 ;
						if( a== number || b ==number || c == number|| d==number) {
							System.out.println(i);
							return;
						}
						if(a>0 &&a<check.length&&check[a]==0) {
							tmp.add(a);
							check[a]=i;
						}
						if(b>0 &&b<check.length&&check[b]==0) {
							tmp.add(b);
							check[b]=i;
						}
						if(c>0 &&c<check.length&&check[c]==0) {
							tmp.add(c);
							check[c]=i;
						}
						if(d>0 && d<check.length && check[d]==0) {
							tmp.add(d);
							check[d]=i;
						}
						
					}
				}
			}
			count.put(i, tmp);
		}
		System.out.println(-1);

		// 4개 : 1+3, 2+2
		// 5개 : 1+4, 2+3
		// 6개 : 1+5, 2+4, 3+3
		// 7개 : 1+6, 2+5, 3+4

	}

}

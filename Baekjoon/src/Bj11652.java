import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bj11652 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		int max = -1; long tmp = 0 ;
		long ans = 0;
		for(int i=0; i<N; i++) {
			try {
			tmp = sc.nextLong();
			map.put(tmp,map.get(tmp)+1);}
			catch(Exception e){
				map.put(tmp,1);
			}
			if(map.get(tmp)>=max) {
				if(map.get(tmp)==max) {
					if(tmp<ans)
						ans = tmp;
				}
				else {
				max  = map.get(tmp);
				ans = tmp;}
			}
		}
		//System.out.println(map);
		System.out.println(ans);
	}

}

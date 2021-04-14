import java.util.HashMap;
import java.util.Map;

public class Hash_3 {

	public static void main(String[] args) {
		String[][] clothes = {
				{"yellow_hat", "headgear"}, 
				{"green_turban", "headgear"},
				
				{"blue_sunglasses", "eyewear"}, 
				
				{"green_turban", "top"},
				
				{"ss","pants"},
				{"ss","pants"}
		};
		//int sum = clothes.length;
		int sum = 1;
		int index = 0 ;
		int[] num = new int[30];
		Map<String,Integer> cnt = new HashMap<String,Integer>();
		for(int i=0; i<clothes.length; ++i) {
			String wear = clothes[i][1];
			try {
				
				++num[cnt.get(wear)];
				
			}catch(Exception e) {
				
				cnt.put(wear,index);
				num[index++] = 1;
			}
		}
		for(int i=0; i<cnt.size(); ++i) {
			sum *= num[i]+1;
		}
		System.out.println(--sum);

	}

}

/*
 * 가능한 모든 조합을 직접 구해서 계산할 필요가 없습니다. 
 * 예를들어 머리:3, 얼굴:2, 옷:1 이라면 총 가능한 개수는
 * 순열 이니까, 0인 경우만 추가해서 해주면 끝.
 * (3+1) * (2+1)*(1+1) -1 = 13
 */
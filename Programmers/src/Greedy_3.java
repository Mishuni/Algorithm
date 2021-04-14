
public class Greedy_3 {

	public static void main(String[] args) {
		String number="4177252841"; 
		int k=4;
		// 선택가능한 자릿 수가 남아 있으면서
		// 가장 큰 수를 앞자리서부터 선택
		// 제거되지 않을 숫자 갯수
		int num_cnt = number.length()-k; 
		
		int start = 0 ;
		int end = k;
		
		StringBuilder target = new StringBuilder();
		
		while(target.length()!=num_cnt) {
			int max=0;
			if(start>=end) {
				target.append(number.substring(start));
				break;
			}
			for(int i=start; i<=end; ++i) {
				if(number.charAt(i)-'0' > max ) {
					max = number.charAt(i)-'0';
					start = i;
				}
			}
			++end;
			target.append(number.charAt(start++));
			
		}
		System.out.println(target);

	}

}

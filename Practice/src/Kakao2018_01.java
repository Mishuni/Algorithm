
public class Kakao2018_01 {
	
	public static void main(String[] args) {
		String[] lines = {
				"2016-09-15 23:59:59.999 0.001s"
		};
		
		System.out.println(solution(lines));
	
	}
	
	 public static int solution(String[] lines) {
		 // 초기화
		 int answer = 0;
		 int[] start_list = new int[lines.length];
		 int[] end_list = new int[lines.length];
		 int i = 0;
		 // 2. 각 log 들의 start,end 시간 ms단위로 계산 후 저장
		 for(String line : lines) {
			 String[] log = line.split(" ");
			 String[] date = log[1].split(":");
			 int end_ms = getMs(date);
			 int start_ms = end_ms-(int)(Double.parseDouble(log[2].substring(0,log[2].length()-1))*1000)+1;
			 end_list[i]=end_ms;
			 start_list[i++]=start_ms;
		}
		 
		 // 3. 종료시점 순대로 겹치는 로그 갯수 계산
	        for(i=0; i<start_list.length; ++i) {
	        	int end_ms = end_list[i];
	        	int max_start_ms = end_ms + 1000;
	        	int count = 1;
	        	// 3-1. 종료시점 + 1초 보다 빨리 시작하고 더 늦게 끝나는 로그들 찾기
	        	for(int j=i+1; j<start_list.length; ++j) {
	        		if(start_list[j]<max_start_ms) {
	        			++count;
	        		}
	        	}
	        	// 3-2. 겹치는 로그가 max 값이면 결과 변경
	        	if(count>answer) {
	        		answer = count;
	        	}
	        }
	        return answer;
	 }
	 
	 public static int getMs(String[] date) {
		 // 해당 시간을 ms(milliseconds)단위로 변환 
		 return (Integer.parseInt(date[0])*3600*1000 + Integer.parseInt(date[1])*60*1000 
				 + (int)(Double.parseDouble(date[2])*1000d));
	 }

}

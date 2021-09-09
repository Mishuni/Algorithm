import java.util.Arrays;
import java.util.HashMap;

public class Kakao2019_01 {

	public static void main(String[] args) {
		String[] record = new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo",
				"Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		
		
		String[] answer = solution(record);
		
		System.out.println(Arrays.toString(answer));
		
		
	}
	
	public static String[] solution(String[] record) {
        String[] answer = {};
        HashMap<String,String> users = new HashMap<String,String>();//HashMap생성
        int cnt = 0;
        for(String r : record) {
			
			String[] details = r.split(" ");
			switch(details[0]) {
			case "Leave":
				++cnt;
				break;
			case "Enter":
				++cnt;
				users.put(details[1], details[2]);
				break;
			default:
				users.put(details[1], details[2]);
				break;
			}
			
		}
		answer = new String[cnt];
		cnt = 0;
		for(String r : record) {
			String[] details = r.split(" ");
			switch(details[0]) {
			case "Change":
				break;
			case "Enter":
				// Prodo님이 들어왔습니다.
				answer[cnt++]=users.get(details[1])+"님이 들어왔습니다.";
				break;
			case "Leave":
				// Prodo님이 나갔습니다.
				answer[cnt++]=users.get(details[1])+"님이 나갔습니다.";
				
			}
		}
		
		
        return answer;
    }

}

import java.util.HashMap;
import java.util.Map;

public class Hash_1 {

	public static void main(String[] args) {

		String[] participant = {"marina", "josipa", 
				"nikola", "vinko", "filipa"};
		String[] completion = {"josipa", "filipa", 
				"marina", "nikola"};
		Map<String,Integer> list = new HashMap<String,Integer>();
		for(String data: participant) {
			if(list.containsKey(data)) {
				list.put(data, list.get(data)+1);
			}else
				list.put(data, 0);
		}
		for(String data: completion) {
			int tmp = list.get(data);
			if(tmp==0) {
				list.remove(data);
			}
			else if(tmp > 0) {
				list.put(data,--tmp);
			}
		}
		System.out.println(list.keySet().iterator().next());

	}

}


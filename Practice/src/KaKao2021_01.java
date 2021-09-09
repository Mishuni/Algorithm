
public class KaKao2021_01 {

	public static String[] numbers = {
			"zero","one","two",
			"three","four","five","six",
			"seven","eight","nine"
			};
	
	public static void main(String[] args) {

		String s  = "one4seveneight"; // 1478
		int answer = solution(s);
		System.out.println(answer);
		
	}
	
	public static int solution(String s) {
        int answer = 0;
        
        for(int i=0; i<numbers.length; ++i) {
        	s=s.replaceAll(numbers[i], Integer.toString(i));
        }
        
        return Integer.parseInt(s);
    }

}

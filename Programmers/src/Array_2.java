import java.util.Comparator;
import java.util.LinkedList;

public class Array_2 {

	public static void main(String[] args) {
		
		int[] numbers = {34,343,212,21,36,363}; 
		System.out.println(numbers.length);
		// 0,0,0,0
		// 212,21 => 21212 21221
		// 34 , 340
		// 34 , 343 => 34343 
		// 36,363
		StringBuilder answer = new StringBuilder("");
		LinkedList<String> list = new LinkedList<String>();
		for( int number : numbers) {
			list.add(Integer.toString(number));
		}

		list.sort(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				if(o1.length()==o2.length()) {
					return o1.compareTo(o2) * -1;
				}
				if(o1.charAt(0)>o2.charAt(0)) return -1;
				if(o1.charAt(0)<o2.charAt(0)) return 1;
				
				int length = (o1.length()>o2.length())? o1.length() : o2.length();
				String newO1=o1, newO2=o2;
				while(newO1.length()!=length) {
					newO1 = newO1 +  o1.charAt(0);
				}
				while(newO2.length()!=length) {
					newO2 = newO2 + o2.charAt(0);
				}
				if(newO1.compareTo(newO2)==0) {
					if(newO1.charAt(0)>newO1.charAt(1)) {
						return (o1.length()>o2.length())? -1: 1;
					}
					return (o1.length()>o2.length())? 1: -1;
				}
				return newO1.compareTo(newO2) * -1;
			}
			
			
		});
		
		if(list.get(0).compareTo("0")==0) {System.out.println(0); return;}
		for(String num : list) {
			answer.append(num);
		}
		System.out.println(answer.toString()); 

	}
	

}


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Es_01 {

	public static void main(String[] args) {
		
		//유형 1 : 010-XXXX-XXXX
		//유형 2 : 010XXXXXXXX
		//유형 3 : +82-10-XXXX-XXXX
		 String phone_number = "010-1212-333";
		 int answer = -1;
		 Pattern telephone1 = Pattern.compile("010-(\\d{4})-(\\d{4})");
		 Pattern telephone2 = Pattern.compile("010(\\d{8})");
		 Pattern telephone3 = Pattern.compile("\\+82-10-(\\d{4})-(\\d{4})");
		 Matcher m1 = telephone1.matcher(phone_number); 
		 Matcher m2 = telephone2.matcher(phone_number); 
		 Matcher m3 = telephone3.matcher(phone_number); 
		 if (m1.matches()){
			 answer = 1;
			}
		 else if(m2.matches()) {
			 answer = 2;
			 
		 }
		 else if(m3.matches()) {
			 answer  = 3;
		 }else {
			 answer = -1;
		 }
		 


//		 if(Pattern.matches("^010 -( \\d{4}) - (\\d{4})$" , phone_number)) {
//			// (\\d{3})(\\d{3,4})(\\d{4})
//			 answer = 1;
//		 }
		 System.out.println(answer);

	}

}

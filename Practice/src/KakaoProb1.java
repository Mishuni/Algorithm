import java.util.*;
public class KakaoProb1 {

	public static void main(String[] args) {

		
		String new_id = "...!@BaT#*..y.abcdefghijklm";
		StringBuilder id = new StringBuilder(new_id.toLowerCase());
		
		int ori = id.length();
		int remove = 0;
		for(int i=0; i<ori; ++i) {
			
			char one = id.charAt(i-remove);
			if( one >=48 &&  one <= 57) {
				// one is number
				continue;
			}
			else if(one >=97 && one <=122) {
				// ons is alpha
				continue;
			}
			else if(one=='-'  ||  one =='_' || one=='.' ) {
				continue;
			}
			else if(one==' ') {
				id.replace(i-remove, i-remove+1, "a");
			}
			else {
				id.deleteCharAt(i-remove);
				++remove;
			}
			
		}
		StringBuilder tmp = new StringBuilder(id.toString());
		for(int c =0; c<id.length(); ++c) {
			tmp = new StringBuilder(tmp.toString().replace("..", "."));
		}
		id = new StringBuilder(tmp.toString());
		
		while( id.length()>0 && ( id.indexOf(".")==0 || id.lastIndexOf(".")==id.length()-1)) {
			if(id.indexOf(".")==0) {
				id.delete(0, 1);
			}
			if(id.length() > 0 && id.lastIndexOf(".")==id.length()-1) {
				id.delete(id.length()-1, id.length());
			}
		}
		
		if(id.length() >= 16) {
			id.delete(15, id.length());
			if(id.charAt(14)=='.') {
				id.delete(14, 15);
			}
		}
		if(id.length()<=2) {
			char last = (id.length()!=0)?id.charAt(id.length()-1):'a';
			while(id.length()<3) {
				id.append(last);
			}
		}
		
		System.out.println(id);
		
	}

}

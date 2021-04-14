
public class Prob1 {

	public static void main(String[] args) {
		String a = "?cab???";
		
		
		
		System.out.println(Solution(a));
		//System.out.println(b.toString());

	}
	
	public static String Solution(String a) {
		StringBuilder b = new StringBuilder(a);
		int half  =  b.length()/2;
			for(int i=0; i<half; ++i) {
				char left = a.charAt(i);
				char right = a.charAt(a.length()-i-1);
				//System.out.println(left);
				//System.out.println(right);
				
				if(left=='?'&&right=='?') {
					b.replace(i, i+1, "z");
					b.replace(a.length()-i-1, a.length()-i, "z");
				}
				else if(left=='?'&&right!='?') {
					b.replace(i, i+1,Character.toString(right));
				}
				else if(right=='?'&&left!='?') {
					b.replace(a.length()-i-1, a.length()-i,Character.toString(left));
				}
				else if(right==left) {
					continue;
				}
				else {
					a  = "NO"; return a;
				}
				
			}
			return b.toString();
	}

}


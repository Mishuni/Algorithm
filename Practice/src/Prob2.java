// 12:34 시작 13:07 끝
import java.util.Stack;

public class Prob2 {

	public static void main(String[] args) {
		String p =  ")(";
		if(check(p)) {
			System.out.println(p);
			return;
			// return p;
		}
		System.out.println(divide(p));
		
	}
	
	public static String divide(String p) {
		// 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환
		if(p.isEmpty()) {
			return p;
		}
		if(check(p)) {
			return p;
		}
		
		// 2. 매개변수p를 "균형잡힌 괄호 문자열" u, v로 분리 
		int a = 0 ; int b = 0;
		String u = null, v = null;
		// 2-1. u는 더 이상 분리할 수 없는 "균형잡힌 괄호 문자열"로 만들기
		for(int i=0; i<p.length(); ++i) {
			char t = p.charAt(i);
			if(t=='(') {
				++a;
			}else {
				++b;
			}
			if(a==b) {
				u = p.substring(0,i+1);
				v = (i+1<p.length())? p.substring(i+1) : "";
				break;
			}
		}
		
		// 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행해서 뒤에 붙이기
		// 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 오른쪽 명령문 수행
		return (check(u))? u + divide(v) : "("+divide(v)+")"+correct(u);
		
	}
	
	// u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙여 반환하는 함수 
	public static String correct(String p) {
		
		StringBuilder tmp = new StringBuilder(p.substring(1, p.length()-1));
		for(int i=0; i<tmp.length(); ++i) {
			if(tmp.charAt(i)=='(') {
				tmp.setCharAt(i, ')');
			}
			else {
				tmp.setCharAt(i, '(');
			}
		
			
		}
		return tmp.toString();
		
	}
	
	// "올바른 괄호 문자열"인지 체크 하는 함수 (스택 활용)
	public static boolean check(String tmp) {
		
		Stack<Character> stack = new Stack<Character>();
		for(int i=0; i<tmp.length(); ++i) {
			if(tmp.charAt(i)=='(') {
				stack.add('(');
			}
			else {
				if(stack.isEmpty()) {
					return false;
				}
				else {
					stack.pop();
				}
			}
		}
		return true;
		
	}
}

import java.util.HashMap;

public class QuickRepeat {

	public static void main(String[] args) {
		
		HashMap<String,String> map6 = new HashMap<String,String>(){{//초기값 지정
		    put("a","b");
		}};
		
		// 정규표현식
		// 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
		// 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
		// 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
		// 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
		String new_id = "...!_@BaT#*..y.abcdefghijkl.m.";
		new_id = new_id.toLowerCase() // 1번
                .replaceAll("[^\\w-\\.]", "") // 2번
                .replaceAll("[\\.]{2,}", ".") // 3번
                .replaceAll("^\\.|\\.$", ""); // 4번

	}

}

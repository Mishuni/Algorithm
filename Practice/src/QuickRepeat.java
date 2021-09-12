import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class QuickRepeat {

	public static void main(String[] args) {
		
		HashMap<String,String> map6 = new HashMap<String,String>(){{//초기값 지정
		   put("a","b");
		}};
		String max_cnt = Collections.max(map6.values());
		System.out.println(max_cnt);
		
		HashSet<Integer> set1 = new HashSet<Integer>();//HashSet생성
		set1.add(1);
		Integer[] tmp = set1.toArray(new Integer[0]);
		System.out.println(Arrays.toString(tmp));
		Iterator itr = set1.iterator();
        while (itr.hasNext()) {
           System.out.println(itr.next());
       }
		
        
        // ArrayList to array
        ArrayList<String> strList = new ArrayList();
        String[] arrays = strList.toArray(new String[0]);
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
		
		
		// 진수 변환
		int i = 127;
		 
		String binaryString = Integer.toBinaryString(i); //2진수
		String octalString = Integer.toOctalString(i);   //8진수
		String hexString = Integer.toHexString(i);       //16진수
		 
		System.out.println(binaryString); //1111111
		System.out.println(octalString);  //177
		System.out.println(hexString);    //7f
		 
		int binaryToDecimal = Integer.parseInt(binaryString, 2);
		int binaryToOctal = Integer.parseInt(octalString, 8);
		int binaryToHex = Integer.parseInt(hexString, 16);
		 
		System.out.println(binaryToDecimal); //127
		System.out.println(binaryToOctal);   //127
		System.out.println(binaryToHex);     //127
		System.out.println(127&8); // 1111111 & 0001000
		binaryString = Integer.toBinaryString(127&8);
		System.out.println(binaryString);

		System.out.println();
		Integer[] array = {1,2,3,4,5,6};
		System.out.println(Arrays.asList(array).indexOf(4));
	}

}

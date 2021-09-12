
import java.util.HashSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;



public class KaKao2021_04 {

	public static void main(String[] args) {
		
		String[] orders= {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD" };
		int[] course = {2,3,5};
		
		
        int[] binary_orders = new int[orders.length];
        //HashSet<String> candidates = new HashSet<String>();//HashSet생성
        ArrayList<String> candidates = new ArrayList();
        HashMap<String,Integer>[] course_prior = new HashMap[11];
        for(int i : course){
            course_prior[i] = new HashMap();
        }
        
        for(int or =0; or<orders.length; ++or){
            String order = orders[or];
            char[] alphabets = new char[26];
            char[] menus = order.toCharArray();
            for(char menu : menus){
                alphabets[menu-'A']='1';
            }
            for(int i=0; i<alphabets.length; ++i){
                if(alphabets[i]!='1'){
                    alphabets[i]='0';
                }
            }
            String str = new String(alphabets);
            int order_int = Integer.parseInt(str, 2);
            binary_orders[or]=order_int;
            //System.out.println(str);
        }
        System.out.println(Arrays.toString(binary_orders));
        
        //answer = candidates.toArray(new String[0]);
        //Arrays.sort(answer);
     
	}

}
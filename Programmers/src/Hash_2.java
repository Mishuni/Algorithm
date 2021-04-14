
public class Hash_2 {

	public static void main(String[] args) {
		String[] phone_book = {"12","123","1235","567","88"};
		System.out.println(solution(phone_book));
	}
	public static boolean solution(String[] phone_book) {

		for(int i=0; i<phone_book.length-1; ++i) {
			for(int j=i+1; j<phone_book.length; ++j) {
				if(phone_book[j].startsWith(phone_book[i]))
					return false;
				if(phone_book[i].startsWith(phone_book[j]))
					return false;
			}
		}
		return true;
    }
}

import java.util.LinkedList;
import java.util.Scanner;
public class Bj11726 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		LinkedList<Long> list = new LinkedList<Long>();
		if(N==1) {
			System.out.println(1);
			return;
		}
		
		list.add(1l);
		list.add(2l);
		for(int i=1; i<N-1; i++) {
			list.add((list.getFirst()+list.getLast())%10007);
			list.remove();
		}
		System.out.println(list.getLast());

	}

}

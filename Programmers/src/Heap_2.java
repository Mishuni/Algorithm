import java.util.Comparator;
import java.util.Collections;
import java.util.PriorityQueue;

public class Heap_2 {
	public static void main(String[] args) {
		int stock=5;
		int[] dates= {5,6,7,10,11,15,16,20,22,30,36};
			//{3,4,6,10,15,16,17};
		int[] supplies= {10,10,20,10,10,10,1,1,1,1,10} ; // 정답은 3
			//{10,6,10,10,30,20,5}; // 정답은 3
		int k = 40;
		int cnt = 0 ;

		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
				//Collections.reverseOrder());
		if(stock >= k) {
			System.out.println(0);
		}
		else {
			int i= -1;
			while(stock<k) {
				while(i<dates.length-1&&dates[++i]<=stock) {
					pq.offer(supplies[i]);
				}
				--i;
				
				stock += pq.remove();
				++cnt;
			}
		}
		System.out.println(cnt);
	}
}

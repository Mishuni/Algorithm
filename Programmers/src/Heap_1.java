import java.util.PriorityQueue;


public class Heap_1 {
	public static void main(String[] args) {
		int[] scoville = {1,2,3,9,10,12};
		int K = 7;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=0; i<scoville.length; ++i) {
			pq.add(scoville[i]);
			
		}
		int tmp = pq.remove();
		int cnt = 0 ;
		while(tmp<K && !pq.isEmpty()) {
			++cnt;
			int tmp2 = pq.remove();
			pq.add(tmp+tmp2*2);
			tmp = pq.remove();
		}
		if(pq.isEmpty()&&tmp<K) {
			cnt = -1;
		}
		System.out.println(cnt);

	}
}

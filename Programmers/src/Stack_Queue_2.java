import java.util.LinkedList;
import java.util.Queue;

public class Stack_Queue_2 {

	public static void main(String[] args) {
		int bridge_length = 2, weight = 10;
		int[] truck_weights = {7,4,5,6};
		int current = truck_weights[0];
		int day = 1;
		int complete = 1;
		
		
		Queue<Integer> cur = new LinkedList<Integer>();
		cur.add(truck_weights[0]);
		while(complete<truck_weights.length) {
			if(cur.size()==bridge_length) {
				current -= cur.remove();
				if(current+truck_weights[complete]<=weight) {
					cur.add(truck_weights[complete]);
					current += truck_weights[complete++];
				}
				else {
					cur.add(0);
				}
				
			}
			else {
				if(current+truck_weights[complete]<=weight) {
					cur.add(truck_weights[complete]);
					current += truck_weights[complete++];
				}else {
					cur.add(0);
				}
			}
			//System.out.println(cur);
			++day;
		}
		
		System.out.println(day+bridge_length);
	}

}


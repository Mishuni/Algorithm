import java.util.*;
public class LinkProb1 {

	public static void main(String[] args) {
		int[][] boxes = {{1, 2}, {2, 1}, {3, 3}, {4, 5}, {5, 6}, {7, 8}};
		// 4 6 7 8 -> 2
		// 1 2 3 4 5 6 -> 3
		boolean[] need = new boolean[1000001];
		int com = boxes.length;
		
		for(int i=0; i<boxes.length; ++i) {
			int[] box= boxes[i];
			if(box[0]==box[1]) {
				--com;
			}
			else {
				
				for(int j=0; j<2; ++j) {
					if(need[box[j]]) {
						need[box[j]] =false;
						--com;
					}
					else {
						need[box[j]] = true;
					}
				
				}

			}
		}
		System.out.println(com);
		
	}

}

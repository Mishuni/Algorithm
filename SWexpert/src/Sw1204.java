import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Sw1204 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("./src/1204.txt"));

		Scanner sc = new Scanner(System.in);
		
		int T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			int max = 0 ;
			PriorityQueue<Integer> max_poses = 
					new PriorityQueue<>(Collections.reverseOrder());
			sc.nextInt();
			int[] scores = new int[101];
			
			for(int i=0; i<1000; ++i) {
				int score = sc.nextInt();
				scores[score] += 1;
				if(scores[score]==max) {
					max_poses.add(score);
				}
				else if(scores[score]>max) {
					max = scores[score];
					max_poses.clear();
					max_poses.add(score);
				}
			}
			System.out.printf("#%d %d%n",test_case,max_poses.poll());
			
		}
	}

}

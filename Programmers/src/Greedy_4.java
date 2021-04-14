import java.util.Arrays;

public class Greedy_4 {

	public static void main(String[] args) {
		
		int[] people = {70,80,50};
		int limit = 100;
		int answer = 0;
		Arrays.sort(people);

		int left =0, right = people.length-1;
		int sum = people[right];
		while(left<=right) {
			sum += people[left];
			if(sum<=limit) {
				++left;
				if(right<=left) {
					++answer;
					break;
				}
			}
			else {
				++answer;
				--right;
				if(left<=right)
					sum = people[right];
			}
		}
		System.out.println(answer);
		
	}

}

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Set<T> mySet = new HashSet<T>(Arrays.asList(someArray));
		int[] A = {2,4,2,2,3,2,1};
		List<int[]> a = Arrays.asList(A);
		Set<int[]> mySet = new HashSet<int[]>(Arrays.asList(A));
		
		int sum = IntStream.of(A).sum();
        //Collections.addAll(mySet, A);
	}

}

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Prob8 {

	public static void main(String[] args) {
		// Prob3. 최적의 시리얼
		// x만큼 나눠서 순차적으로 볼 때
		// 그 당시 구간에서 최소값들을 뽑아내고
		// 그 뽑아낸 값들 중에서 최대값의 가격을 갖는 시리얼 구매하기
		int x = 3;
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(2);
		arr.add(2);
		arr.add(4);
		arr.add(8);
		arr.add(5);
		System.out.println(segment(x, arr));

	}

	public static int segment(int x, List<Integer> arr) {

		int max = Collections.max(arr);
		if (x == 1) {
			return max;
		}
		int min = Collections.min(arr);
		int n = arr.size();
		if (x == n || min==max) {
			return min;
		}

		int extra = x;

		List<Integer> subArr = new LinkedList<Integer>();
		for (int i = 0; i < x; ++i) {
			subArr.add(arr.get(i));
		}
		int result = Collections.min(subArr);
		int subResult = result;

		for (; extra < n; ++extra) {
			int rmv = subArr.get(0);
			int apd = arr.get(extra);
			subArr.remove(0);
			subArr.add(apd);

			if (rmv == apd) {
				// 최소값의 변화가 없으므로 
				continue;
			}
			if (rmv != subResult) {
				// 없어진게 최소 값이 아니면
				if (apd >= subResult)
					continue;
				else
					subResult = apd;
			} 
			else {
				// 없어진게 최소 값이면
				if (apd < subResult) {
					subResult = apd;
				} else if (apd == subResult) {
					continue;
				} else {
					// apd > subResult 인 경우이니까
					
					subResult = Collections.min(subArr);
					// 답은 위 같이 썼지만
					//continue 해도 될 듯
				}
			}

			if (subResult > result) {
				// 부분 최소 값들 중에서 최댓값을 구하는 부분
				result = subResult;
			}
			if (result == max) {
				// 결국 전체 중에 최대값이 부분의 최소로 나오면 종료
				return result;
			}
		}

		return result;
	}

}

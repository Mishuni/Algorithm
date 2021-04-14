import java.util.*;

public class QuickSort {

	// partition
	public static int partition(int[] tmp, int left, int right) {
		int point = (left + right) / 2;
		int pivot = tmp[point];
		while (left < right) {
			// System.out.println("e");
			while (left <= right && tmp[left] <= pivot)
				++left;
			while (left <= right && tmp[right] > pivot)
				--right;

			if (left <= right) {
				int temp = tmp[left];
				tmp[left] = tmp[right];
				tmp[right] = temp;

				// right는 다 통과해서 point 까지 왔는데 left는 아직 남은 경우 기준점을 변경
				if (right == point) {
					// right는 point 까지 접근 가능 (같은거 포함안하니까)
					// point index랑 left랑 바꾸니까 left가 새로운 pivot됨
					return left;
				}
			}
		}
		// 마지막에 left는 다 통과되고 right에는 바꿀 게 남은 경우 기준점을 변경
		if (right != point) {
			int temp = tmp[point];
			tmp[point] = tmp[right];
			tmp[right] = temp;
		}
		return right;

	}

	public static void quickSort(int[] ori, int left, int right) {
		if (left < right) {
			int newPivot = partition(ori, left, right);
			quickSort(ori, left, newPivot - 1);
			//System.out.println("left:" + Arrays.toString(ori));
			quickSort(ori, newPivot + 1, right);
			//System.out.println("right:" + Arrays.toString(ori));
		}
	}

	public static void main(String[] args) {

		int[] a = { 1, 3, 56, 8, 9, 3, 2, 1, 23, 11, 10 };

		quickSort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));

	}
}
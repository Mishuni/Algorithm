import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Bj1931 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		
		for(int i=0; i<N; i++) {
			list.add(new Meeting(sc.nextInt(),sc.nextInt()));
		}
		
		sc.close(); sc= null;
		Collections.sort(list);
		// time 이 제일 적은 순대로 회의를 배치한다.
		
		int pre = list.get(0).end;// 겹치는 것 중에서 제일 작은거
		int total = 1;
		
		for(int i=1; i<list.size(); i++) {
			Meeting a = list.get(i);
			if(a.start>=pre) {
				// 끝났던 시간 부터 시작할 수 있으니까
				pre = a.end;
				total++;
			}
		}
		System.out.println(total);	
		return;
	}
}

class Meeting implements Comparable<Meeting>{
	int start;
	int end;
	int time;
	
	public Meeting(int start, int end) {
		this.start = start;
		this.end = end;
		this.time = end - start;
	}
	@Override
	public int compareTo(Meeting o) {
		// 죵료 시간이 같으면 시작 시간을 비교함
		if(this.end == o.end) {
            return Integer.compare(this.start, o.start);
        } else {
            return Integer.compare(this.end, o.end);
        }
	}
}

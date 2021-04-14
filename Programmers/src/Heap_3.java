import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Heap_3 {

	public static void main(String[] args) {
		int[][] jobs = { { 0, 3 }, { 2, 6 } , { 1, 9 }};
		
		PriorityQueue<Job> tasks = new PriorityQueue<Job>();
		List<Job> list = new ArrayList<Job>();
		
		for(int i=0; i<jobs.length; ++i) {
			tasks.add(new Job(jobs[i][0],jobs[i][1]));
		}
		
		for(int i=0; i<jobs.length; ++i) {
			list.add(tasks.poll());
		}
		
		int lastEnd=0, sum=0;
		while(!list.isEmpty()) {
			for(int i=0; i<list.size(); ++i) {
				if(lastEnd>=list.get(i).request) {
					lastEnd+=list.get(i).taskTime;
					sum+=lastEnd-list.get(i).request;
					list.remove(i);
					break;
				}
				if(i==list.size()-1) ++lastEnd;
			}
		}
		
		System.out.println(sum/jobs.length);
	}

}

class Job implements Comparable<Job>{
	
	int request;
	int taskTime;
	
	public Job(int request, int taskTime) {
		this.request=request;
		this.taskTime=taskTime;
	}

	@Override
	// 수행 시간이 짧을 수록, 
	// 수행시간이 같다면, 더 빨리 요청된 순으로 우선순위 배정
	public int compareTo(Job o) {
		if(this.taskTime!=o.taskTime) 
			return (this.taskTime-o.taskTime<0)? -1:1;
		else
			return (this.request-o.request <0)?-1:1;
	}
	
}

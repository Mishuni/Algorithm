import java.util.LinkedList;
import java.util.Scanner;

public class Bj2579 {

	public static void main(String[] args) {
	
		// 1. 입력
		Scanner sc = new Scanner(System.in);
		LinkedList<Stair> steps = new LinkedList<Stair>();
		int N = sc.nextInt();
		int[] score = new int[N];
		for(int i=0; i<N; i++) {
			score[i] = sc.nextInt();
		}
		steps.add(new Stair());
		steps.add(new Stair(0,score[0],score[0]));
		
		// 2. 동적 계획법
		for(int i=1; i<N-1; i++) {
			
			steps.add(new Stair(steps.get(1).getMax2(),
					steps.get(1).double_step+score[i],
					steps.get(0).getMax2()+score[i]));
			steps.remove();
		}
		// 3. 출력
		if(steps.get(1).double_step+score[N-1]>
		steps.get(0).getMax2()+score[N-1]) {
			System.out.println(steps.get(1).double_step+score[N-1]);
		}else {
			System.out.println(steps.get(0).getMax2()+score[N-1]);
		}
	}

}

class Stair {
	int jump;   	   // 현재 계단 안 올라가도 되어서 안 올라갔을 때
	int single_step;   // 1칸 뒤로 뛰어올라왔을 때
	int double_step;   // 2칸 뒤로 뛰어올라왔을 때

	Stair() {
		this.jump = 0;
		this.single_step = 0;
		this.double_step = 0;
	}

	Stair(int a, int b, int c) {
		this.jump = a;
		this.single_step = b;
		this.double_step = c;
	}

	public int getMax2() {
		// 두 값(single_step, double_step) 중 max 값을 반환
		return (single_step>double_step)? single_step : double_step;
	}

}
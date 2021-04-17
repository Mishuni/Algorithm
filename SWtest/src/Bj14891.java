import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.LinkedList;


public class Bj14891 {

	public static LinkedList<Integer>[] wheels;
	public static boolean[] between;
	
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		between = new boolean[3];
		wheels = new LinkedList[4];
		StringTokenizer st;
		for(int s=0; s<4; ++s) {
			st = new StringTokenizer(br.readLine());
			String[] line = st.nextToken().split("");
			LinkedList<Integer> tmp = new LinkedList();
			for(int i=0; i<8; ++i) {
				tmp.add(stoi(line[i]));
			}
			wheels[s] = tmp;
		}
		
		st = new StringTokenizer(br.readLine());
		int K = stoi(st.nextToken());
		for(int i=0; i<K; ++i) {
			st = new StringTokenizer(br.readLine());
			int start = stoi(st.nextToken())-1;
			int direc = stoi(st.nextToken());
			checkBetween();
			rotate(wheels[start], direc);
			// 0 1 2 3
			switch(start) {
			case 0:
				if(!between[0]) {
					rotate(wheels[1],direc*-1);
					if(!between[1]) {
						rotate(wheels[2],direc);
						if(!between[2]) {
							rotate(wheels[3],direc*-1);
						}
					}
				}
				break;
			case 1:
				if(!between[0]) {
					rotate(wheels[0],direc*-1);
				}
				if(!between[1]) {
					rotate(wheels[2],direc*-1);
					if(!between[2]) {
						rotate(wheels[3],direc);
					}
				}
				break;
			
			case 2:
				if(!between[1]) {
					rotate(wheels[1],direc*-1);
					if(!between[0]) {
						rotate(wheels[0],direc);
					}
				}
				if(!between[2]) {
					rotate(wheels[3],direc*-1);
				}
				break;
			case 3:
				if(!between[2]) {
					rotate(wheels[2],direc*-1);
					if(!between[1]) {
						rotate(wheels[1],direc);
						if(!between[0]) {
							rotate(wheels[0],direc*-1);
						}
					}
				}
				break;
			}
		}
		
		int[] grade = {1,2,4,8};
		int sum = 0;
		for(int i=0; i<4; ++i) {
			if(wheels[i].get(0)==1) {
				sum+=grade[i];
			}
		}
		System.out.println(sum);
			
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void checkBetween() {
		for(int i=0; i<3; ++i) {
			// 같으면 true
			if(wheels[i].get(2)==wheels[i+1].get(6)) {
				between[i] = true;
			}
			else {
				between[i] = false;
			}
		}
	}
	
	public static void rotate(LinkedList<Integer> wheel, int direc) {
		// clock wise
		if(direc==1) {
			// last to head
			int last = wheel.remove(7);
			wheel.add(0, last);
		}
		else {
			// head to last
			int head = wheel.remove(0);
			wheel.add(7,head);
		}
	}

}

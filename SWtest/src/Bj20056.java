import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

class Fireball{
	
	int y,x,direc,speed,mass;
	
	Fireball(int y, int x, int m, int s, int d){
		this.y = y;
		this.x = x;
		this.mass =m;
		this.speed = s;
		this.direc = d;
	}
}

public class Bj20056 {
	
	public static int N,M,K;
	public static int[][] map;
	public static ArrayList<Fireball> fbs;
	
	public static final int[][] DIRECTIONS 
	= {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	
	public static final int[] SAME = {0,2,4,6};
	public static final int[] DIFF = {1,3,5,7};
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		map = new int[N][N];
		
		fbs = new ArrayList();
		
		for(int i=0; i<M; ++i) {
			
			st = new StringTokenizer(br.readLine());
			
			fbs.add(new Fireball(stoi(st.nextToken())-1,
					stoi(st.nextToken())-1,stoi(st.nextToken()),
					stoi(st.nextToken()),stoi(st.nextToken())));
		}
		
		while(K!=0 && fbs.size()>0) {
			--K;
			int i = 0;
			ArrayList<ArrayList<Integer>> queue = new ArrayList();
			LinkedList<Integer> remove = new LinkedList();
			
			map = new int[N][N];
			// move
			for(Fireball fb : fbs) {
				
				int[] dir = DIRECTIONS[fb.direc];
				
				int nexty = (fb.y+dir[0]*(fb.speed%N))%N;
				int nextx = (fb.x+dir[1]*(fb.speed%N))%N;
				nexty = (nexty<0)? nexty+N : (nexty>N-1)? nexty-N : nexty; 
				nextx = (nextx<0)? nextx+N : (nextx>N-1)? nextx-N : nextx;
				int status = map[nexty][nextx];
				
				if(status<0) {
					// add duplicate
					int index = (status * -1 )-1;
					
					ArrayList<Integer> lo = queue.get(index);
					
					lo.set(0, lo.get(0)+fb.mass);
					lo.set(1, lo.get(1)+fb.speed);
					lo.set(2, lo.get(2)+1);
					
					int cmp = (fb.direc%2==0)? 1 : -1;
					int ori = lo.get(3);
					
					if(ori!=0) {
						if(ori*cmp==-1) {
							lo.set(3, 0);
						}
					}
					remove.add(i);
				}
				
				else if(status>0 ) {
					// init duplicate
					int init = status-1;
					ArrayList<Integer> q = new ArrayList();
					
					Fireball first = fbs.get(init);
					
					q.add(first.mass+fb.mass);
					q.add(first.speed+fb.speed);
					q.add(2);
					if(first.direc%2==0) {
						if(fb.direc%2==0) {
							q.add(1);
						}
						else {
							q.add(0);
						}
					}
					else{
						if(fb.direc%2==0) {
							q.add(0);
						}
						else {
							q.add(-1);
						}
					}
					q.add(nexty);
					q.add(nextx);
					queue.add(q);
					map[nexty][nextx] = -1 * (queue.size());
					
					remove.add(init);
					remove.add(i);
					
				}
				
				else {
					// init
					map[nexty][nextx] = i+1;
					
				}
				
				fb.y = nexty;
				fb.x = nextx;
				++i;
			}
			
			// process duplicate
			for(ArrayList<Integer> current : queue ) {
				
				int mass = current.get(0)/5;
				if(mass==0) continue;
				int speed = current.get(1)/current.get(2);
				int[] dir;
				if((current.get(3)!=0)) {
					dir = SAME;
				}
				else {
					dir = DIFF;
				}
				
				for(int d=0; d<4; ++d) {
					fbs.add(new Fireball(current.get(4),current.get(5),mass,speed,dir[d]));
				}
			}
			 
			Collections.sort(remove);
			for(int re = remove.size()-1; re>=0; --re) {
				int r = remove.get(re);
				Fireball f = fbs.remove(r);
				map[f.y][f.x] = 0;
			}
			
		}
		
		int result = 0;
		for(Fireball fb : fbs) {
			result += fb.mass;
		}
		
		System.out.println(result);
		
	}

}

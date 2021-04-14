import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bj7569 {

	public static int M, N, H;
	public static int[][][] box;
	public static Queue<Integer> x = new LinkedList<Integer>();
	public static Queue<Integer> y = new LinkedList<Integer>();
	public static Queue<Integer> z = new LinkedList<Integer>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		box = new int[N][M][H];

		for (int l = 0; l < H; l++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					box[i][j][l] = sc.nextInt();
					if (box[i][j][l] == 1) {
						// 처음에 익은 토마도가 있는 곳(1인 곳)을
						// queue에 집어넣어 관찰 대상에 입력
						x.add(i);
						y.add(j);
						z.add(l);
						
					}
				}
			}
		}
		sc.close();
		sc = null;
		bfs();
	}

	public static void bfs() {
		// dx,dy,dz : 상, 하, 좌, 우, 위, 아래 좌표 움직임
		// (-1,0,0), (1,0,0), (0,-1,0), (0,1,0) ,(0,0,-1) ,(0,0,1)
		int[] dx = { -1, 1, 0, 0, 0, 0 };
		int[] dy = { 0, 0, -1, 1, 0, 0 };
		int[] dz = { 0, 0, 0, 0, -1, 1 };
		
		int day = 0;
		int cnt = 0;
		int pre_size = x.size();
		boolean influ = false;

		while (!x.isEmpty() && !y.isEmpty()&& !z.isEmpty()) {
			cnt++;
			if (cnt > pre_size) {
				// while loop가 이 전 day에 저장된 좌표들의
				// 갯수만큼 돌았다면, 다른 day로 넘어가기
				cnt = 1;
				pre_size = x.size();
				if (influ) {
					day++;
					influ = false;
				}
			}
			int n = x.poll();
			int m = y.poll();
			int h = z.poll();

			for (int i = 0; i < 6; i++) {
				int tmp_x = n + dx[i];
				int tmp_y = m + dy[i];
				int tmp_z = h + dz[i];
				
				if (tmp_x >= 0 && tmp_y >= 0 && tmp_z>=0 &&
						tmp_x < N && tmp_y < M && tmp_z <H) {
					// 변화된 좌표가 box 안에 유효한 좌표이면
					if ( box[tmp_x][tmp_y][tmp_z] == 0) {
						// 좌표의 값이 0 이면
						box[tmp_x][tmp_y][tmp_z] = 1;
						x.add(tmp_x);
						y.add(tmp_y);
						z.add(tmp_z);
						influ = true;
						// 이번 day에서 한번이라도 다른 좌표를 들렀으면 influ를 true로 표시
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for(int l=0; l<H; l++)
				if (box[i][j][l] == 0) {
					// box 좌표중 0 값이 하나라도 있으면 모두 익기 실패
					day = -1;
					break;
				}
			}
			if (day == -1)
				break;
		}
		System.out.println(day);

	}
}
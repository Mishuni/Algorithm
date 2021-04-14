import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hash_4 {

	public static void main(String[] args) {
		String[] genres= {"classic", "pop", "classic", 
				"classic", "pop","dance","k-pop","k-pop","k-pop"};
		int[] plays = {500, 600, 150, 800, 2500,1000,2000,3000,500};
		// 정답  : [7, 6, 4, 1, 3, 0, 5]
		
		Map<String,Genre> rank = new HashMap<String,Genre>();

		for(int i=0; i<genres.length; ++i) {
			try {
				// 없는 키 값이 오면 에러 처리
				rank.get(genres[i]).add(plays[i],i);
				
			}catch(Exception e) {
				// 키 값 추가
				rank.put(genres[i], new Genre(plays[i],i));
			}
		}
		
		List<Genre> tmp = new ArrayList<Genre>(rank.values());
		// 장르들을 총 재생 순 대로 정렬
		Collections.sort(tmp);
		
		// 장르별 재생 순 1,2위 고유 번호 합치기 
		List<Integer> ans_tmp = new ArrayList<Integer>();
		for(int i=0; i<tmp.size(); ++i) {
			Genre tt = tmp.get(i);
			ans_tmp.add( tt.index[0]);
			if(tt.index[1]!=-1) {
				// 그 장르에 수록된 곡이 2개 이상인 경우
				ans_tmp.add( tt.index[1]);
			}
		}
		
		// List 를 배열로 변환 하기 위한 작업
		int[] answer = new int[ans_tmp.size()];
		for(int i = 0; i<answer.length; ++i) {
			answer[i] = ans_tmp.get(i);
		}
		
		// 결과 확인
		System.out.println(Arrays.toString(answer));
	}

}

class Genre implements Comparable<Genre>{
	// 장르별 정보를 담는 클래스
	int[] play;
	int[] index;
	int sum;
	
	Genre(int first, int ind_f){
		play = new int[2];
		index = new int[2];
		play[0] = first;
		index[0] = ind_f;
		index[1] = -1;
		sum = first;
	}
	
	public void add(int value, int ind) {
		sum+=value;
		if(play[0]<value) {
			// 입력된 재생 수가 현재 1위 재생 수 보다 클 때
			// 1위에 입력된 값 넣고, 1위는 2위로 변경
			int tmp = play[0];
			play[0] = value;
			play[1] = tmp;
			tmp = index[0];
			index[0] = ind;
			index[1] = tmp;
		}
		else if(play[0]==value && value>play[1]) {
			// 1위 값과 같은데 2위 값보다 클 때 2위에 저장
			play[1] = value;
			index[1] = ind;
		}
		else {
			// 입력된 재생 수가 현재 1위 재생 수 보다 작을 때
			if(play[1]<value) {
				// 입력된 재생 수가 현재 2위 재생 수 보다 큰 경우
				// 2위에 저장
				play[1] = value;
				index[1] = ind;
			}
		}
	}

	@Override
	public int compareTo(Genre a) {
		// 장르별 비교를 위한 함수, sum 값이 크면 더 앞에 정렬
		if(this.sum>a.sum) {
			return -1;
		}
		return 1;
	}
	
}
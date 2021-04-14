import java.util.*;
public class KakaoProb3 {

	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
	
		TrieInfo[] tries = new TrieInfo[3];  // c j p
		int[] answers ;
		ArrayList<Integer> resTmp = new ArrayList();
		
		for(int i=0; i<3; ++i)
			tries[i] = new TrieInfo();
		
		for(String p : info) {
			String[] ps = p.split(" ");
			int[] word = new int[3];
			word[0] = (ps[1].equals("backend"))? 0:1;
			word[1] = (ps[2].equals("junior"))? 0:1;
			word[2] = (ps[3].equals("chicken"))? 0:1;
			
			int root = (ps[0].equals("cpp"))? 0 : (ps[0].equals("java"))? 1:2;
			tries[root].insert(word, Integer.parseInt(ps[4]));
			
		}
		
		for(int q=0; q<query.length; ++q) {
			String queryone = query[q] ;
			queryone = queryone.replaceAll("and ", "");
			String[] qq  = queryone.split(" ");
			int[] word =new int[3];
			
			word[0] = ( (qq[1] .equals("-"))? -1 : (qq[1].equals("backend"))? 0:1);
			word[1] = ((qq[2] .equals("-"))? -1 : (qq[2].equals("junior"))? 0:1);
			word[2] = ( (qq[3] .equals("-"))? -1 : (qq[3].equals("chicken"))? 0:1);
			int root = (qq[0].equals("cpp"))? 0 : (qq[0].equals("java"))? 1: (qq[0] .equals("python"))? 2 : -1;

			if(root!=-1) {
				int answer = tries[root].search(word, Integer.parseInt(qq[4]),0);
				resTmp.add(answer);
			}
			else {
				int answer = 0;
				for(root=0 ; root<3; ++root) {
					answer += tries[root].search(word, Integer.parseInt(qq[4]),0);
				}
				resTmp.add(answer);
			}
			
			
			
		}
		
		System.out.println(resTmp);
		answers = new int[resTmp.size()];
		for(int i =0; i<resTmp.size(); ++i) {
			answers[i] = resTmp.get(i);
		}
		
		
		
				
	}

}


//Trie Node
class TrieInfo {

	int count;
	TrieInfo[] childList;
	ArrayList<Integer> exam;

	TrieInfo() {
		childList = new TrieInfo[2];
		count = 0;
		exam = new ArrayList();
	}

	void insert(int[] word, int grade) {
		TrieInfo current = this;
		for (int i : word) {
			++current.count;
			current.exam.add(grade);
			
			if (current.childList[i] != null) {
				current = current.childList[i];
			} else {
				current.childList[i] = new TrieInfo();
				current = current.childList[i];
			}
		}
		
		++current.count;
		current.exam.add(grade);
		
		
	}

	int search(int [] query, int grade, int depth) {
		TrieInfo current = this;
		int result = 0;
		if(depth == 3) {
			result = 0;
			if(grade==0) {
				return current.count;
			}
			for(int g : current.exam) {
				if(g>=grade) {
					result++;
				}
			}
			return result;
		}
			
			int no = query[depth];

			if(no==-1) {
				int a = 0;
				if (current.childList[0] != null) {
				 a =  current.childList[0].search(query, grade,depth+1); }
				
				if (current.childList[1 ] != null) {
				a += current.childList[1].search(query, grade,depth+1);
				}
				return a;
			}
			
			if (current.childList[no ] != null) {
				return current.childList[no].search(query, grade,depth+1);
			} else {
				return 0;
			}
	}
	

}

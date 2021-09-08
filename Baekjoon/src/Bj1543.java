import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj1543 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String paper = br.readLine();
		String target = br.readLine();
		int res = 0;
		
		for(;paper.indexOf(target)!=-1; ++res) {
			paper = paper.substring(paper.indexOf(target)+target.length(),paper.length());
		}
		System.out.println(res);
		
	}

}

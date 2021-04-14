
public class Dfs_3 {
	
	public static void main(String[] args) {
		int[][] baseball = {{123,1,1},
							{356,1,0},
							{327,2,0},
							{489,0,1}};
		String a, b;
		int answer = 0 ;
		for(int i=123; i<987; ++i) {
			boolean check = false;
			a = Integer.toString(i);
			if(i/100==0||a.charAt(1)=='0'||a.charAt(2)=='0') {
				continue;
			}
			if(a.charAt(0)==a.charAt(1) || a.charAt(0)==a.charAt(2) || a.charAt(1)==a.charAt(2)) {
				continue;
			}
			
			for(int j=0; j<baseball.length; ++j) {
				int strike =0 ;
				int ball = 0;
				if(baseball[j][1]==3) {
					System.out.println(1);
					return;
				}
				b= Integer.toString(baseball[j][0]);
				
				for(int f =0; f<3; ++f) {
					for(int s =0; s<3; ++s) {
						char tmp1 = a.charAt(f);
						char tmp2 = b.charAt(s);
						if(f==s) {
							if(tmp1==tmp2) {
								++strike;
							}
						}else {
							if(tmp1==tmp2) {
								++ball;
							}
						}
					}
				}
				if(strike!=baseball[j][1] || ball!=baseball[j][2]) {
					check = true;
					break;
				}
				
			}
			if(!check) {
				++answer;
			}
			
		}
		System.out.println(answer);
		
	}

}

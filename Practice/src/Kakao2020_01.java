
public class Kakao2020_01 {

	public static void main(String[] args) {

		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String hand = "right";
		
		System.out.println(solution(numbers,hand));
	}
	
	 public static String solution(int[] numbers, String hand) {
		 
		 StringBuilder answer = new StringBuilder();
		 // left , right
		 int[][] hands = new int[][] {{3,0},{3,2}};
		 int prior = (hand.equals("left"))? 0 : 1;
		 String pri_c = (hand.equals("left"))? "L" : "R";
		 
		 for(int num : numbers) {
			 if(num==1||num==4||num==7) {
				 hands[0]=getPos(num);
				 answer.append("L");
			 }
			 else if(num==3||num==6||num==9) {
				 hands[1] = getPos(num);
				 answer.append("R");
			 }
			 else {
				 int left_dis = countMinDis(hands[0],num);
				 int right_dis = countMinDis(hands[1],num);
				 if(left_dis<right_dis) {
					 hands[0]=getPos(num);
					 answer.append("L");
				 }
				 else if(left_dis>right_dis) {
					 hands[1]=getPos(num);
					 answer.append("R");
				 }
				 else {
					 hands[prior]=getPos(num);
					 answer.append(pri_c);
				 }
			 }
		 }
		 return answer.toString();
		 
	 }
	 public static int[] getPos(int target) {
		 int y = (target==0)?3:(int)Math.ceil((float)target/(float)3) - 1;
		 int x = (target==0)?1:(target % 3==0)?2: target % 3-1;
		 return new int[] {y,x};
	 }
	 public static int countMinDis(int[] pos, int target) {
		// ceil(i/3) - 1 , i%3 - 1 = y , x
		// 0 => 3,1
		int[] next = getPos(target);
		return Math.abs(next[0]-pos[0]) + Math.abs(next[1]-pos[1]);
	 }
}

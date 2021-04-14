import java.util.*;

public class Real2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 6;
		int[] answer ;
        int fac = 0;
        for(int i=n; i>0; --i){
            fac += i;
        }
        int[][] space = new int[n][n];
        answer = new int[fac+1];
        
        
        space[0][n-1] = 1;
        int startx = 0;
        int starty = n-1;
        int[][] direction = {{+1,-1},{0,+1},{-1,0}}; 
        int idx_dir = 0;
        
        for(int i=2; i<=fac; ++i) {
        	
        	startx  += direction[idx_dir][0];
        	starty  += direction[idx_dir][1];

        	if(space[startx][starty]!=0) {

        		--i;
        		startx  -= direction[idx_dir][0];
            	starty  -= direction[idx_dir][1];
            	
        		if(++idx_dir>2) {
        			idx_dir=0;
        		}
        		continue;
        	}
        	else {
        		space[startx][starty] = i;
        		if((starty==n-1 && startx==n-1)||( startx == n-1&&starty==0)) {
        			++idx_dir;
        		}
        	}
        	
        }
        
        int idx = 0;
        for(int i=0; i<n; ++i) {
        	 for(int j=0; j<n; ++j) {
        		 if(space[i][j]!=0) {
        			 answer[idx++]=space[i][j];
        		 }
        	 }
        }
        
        System.out.println(Arrays.toString(answer));
       
	}

}
// [0,0,0,0,0  ,1,0,0,0,0,0,0]
// [0,0,0,0,2  ,15,0,0,0,0,0,0]
// [0,0,0,3,0  ,14,0,0,0,0,0,0]
// [0,0,4,0,0  ,13,0,0,0,0,0,0]
// [0,5,0,0,0  ,12 ,0,0,0,0,0,0]
// [6,7,8,9,10,11,0,0,0,0,0,0]
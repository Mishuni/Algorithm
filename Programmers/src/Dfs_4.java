
public class Dfs_4 {

	public static void main(String[] args) {
		int brown = 24 , red = 24 ;
		int[] result = new int[2];
		// width >= height
		// red = (width - 2 ) * (height - 2 )
		// brown + red = area
		int area = brown + red ;
		int root = (int)Math.sqrt(area);
		
		for ( int i = 3 ; i <= root ; ++ i) {
			
			if(area % i == 0) {
				
				if ( red == (i-2) * ((area/i)-2) ) {
					result[0] = area / i;
					result[1] = i ;
					break ;
				}
			}
			
		}
		System.out.println(result[0]);
		System.out.println(result[1]);

	}

}

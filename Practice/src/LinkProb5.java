import java.util.*;
public class LinkProb5 {

	public static void main(String[] args) {
		int[] cards = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
		
		int answer = 0;
		List<Integer> player = new ArrayList<Integer>();
		List<Integer> dealer = new ArrayList<>();
		
		
		int next = 0;
		
		while(next<cards.length-3) {
		
		System.out.println(next);
		
		player.clear();
		dealer.clear();
		int psum = 0;
		int dsum = 0;
		for ( int i=0; i<3; i=i+2) {
			
			int pc = ( cards[next+i] >=10)? 10 : cards[next+i] ;
			int dc = ( cards[next+i+1] >=10)? 10 : cards[next+i+1] ;
			player.add(pc);
			dealer.add(dc);
			
		}
		int show = ( cards[next+3] >=10)? 10 : cards[next+3];
		psum = sum(player);
		dsum = sum(dealer);

		System.out.println(psum+":"+dsum);
		next += 4;
		if(psum == 21) {
			
			if ( dsum != 21) {
				answer += 3;
			}
			
		}
		else {
			try {
			if ( show ==1 || show >=7) {
				// 플레이어는 카드 합이 17 이상이 될 때까지 받는다
				while(  sum(player) < 17   ) {
					
					player.add(cards[next++]);
					
				}
				psum = sum(player);
				if( psum == 21 ) {
					if ( dsum != 21) {
						answer += 3;
					}
				}
				else if(psum > 21) {
					answer -=2;
				}
				else {
					// dealer
					while( sum(dealer) < 17) {
						dealer.add(cards[next++]);
					}
					dsum = sum(dealer);
					if( dsum == 21 ) {
						if ( psum != 21) {
							answer -= 2;
						}
					}
					else if(dsum > 21) {
						answer +=2;
					}
					else {
						answer+=finalSum( psum, dsum);
					}
				}
				
				
			}
			
			else if ( show <=6 && show >=4) {
				
				// player no card -> dealer
				while( sum(dealer) < 17) {
					dealer.add(cards[next++]);
				}
				dsum = sum(dealer);
				if( dsum == 21 ) {
					if ( psum != 21) {
						answer -= 2;
					}
				}
				else if(dsum > 21) {
					answer +=2;
				}
				else {
					answer+=finalSum( psum, dsum);
				}
				
			}
			
			else {
				
				// 플레이어는 카드 합이 12 이상이 될 때까지 받는다
				while(  sum(player) < 12   ) {
					
					player.add(cards[next++]);
					
				}
				psum = sum(player);
				if( psum == 21 ) {
					if ( dsum != 21) {
						answer += 3;
					}
				}
				else if(psum > 21) {
					answer -=2;
				}
				else {
					// dealer
					
					while( sum(dealer) < 17) {
						dealer.add(cards[next++]);
					}
					dsum = sum(dealer);
					if( dsum == 21 ) {
						if ( psum != 21) {
							answer -= 2;
						}
					}
					else if(dsum > 21) {
						answer +=2;
					}
					else {
						answer+=finalSum( psum, dsum);
					}
					
				}
				
				
			}}
			catch(ArrayIndexOutOfBoundsException e ) {
				break;
			}
		}
		}
		
		System.out.println(answer);
		
	}
	
	public static int finalSum ( int p, int d) {
		
		int a = 21 - p;
		int b = 21 - d;
		
		if(a > b )  return -2 ;
		if (b> a)  return 2;
		else  return 0;
		
	}
	
	public static int sum( List<Integer> test ) {
		
		int s = 0;
		
		int anum = 0;
		
		for ( int t : test) {
			if( t!=1) {
				s+=t;
			}
			else {
				++anum;
			}
			
		}
		
	  while(anum>0 && 21 - s-anum +1 >= 11) {
			s += 11;
			--anum;
		}
		
		
		return s ;
		
	}

}

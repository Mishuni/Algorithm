import java.util.*;
public class LinkProb6 {

	public static void main(String[] args) {
	
		String[] companies = {  "A fabdec 2", "B cebdfa 2", "C ecfadb 2"} ;
		String[] applicants = { "a BAC 1", "b BAC 3", "c BCA 2", "d ABC 3", "e BCA 3", "f ABC 2" } ;
		
		int comNum = companies.length;
		int round = (companies.length<4)?companies.length:4;
		
		ArrayList<Info> com = new ArrayList();
		for(String c: companies) {
			com.add(new Info(c.split(" ")));
		}
		
	}

}

class Info {
	
	String name ;
	String[] list;
	int rank;
	LinkedList<String> appList;
	
	Info(String[] group){
		
		this.name = group[0];
		this.list = group[1].split("");
		this.rank = Integer.parseInt(group[2]);
		this.appList = new LinkedList();
		
	}
	
	public void choice(String an) {
		
		if(this.appList.size() <= rank) {
			
		}
		else {
			// 우선순위판별
		}
		
	}
	
}

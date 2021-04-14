
public class Leet1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {2,7,1,2};
		int target = 9;
		
		int[] answer = twoSum(nums,target);

	}
	
	public static int[] twoSum(int[] nums, int target) {
        int[] temp=new int[2];
        for(int i=0; i<nums.length-1; ++i){
            for(int j=i+1; j<nums.length; ++j){
                if(nums[i]+nums[j]==target){
                    temp[0]=i;
                    temp[1]=j;
                    return temp;    
                }
                 
            }
        }
        
        return temp;
    }

}

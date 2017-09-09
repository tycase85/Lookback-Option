import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EuropeanPutCalculation {

	private static double startingValue; //s value = 4
	private static double upValue; //up value = 2
	private static double downValue; //down value = 0.5
	private static double rate; //rate = 0.25
	private static double p; //p = 0.5
	private static double q; //q = 0.5
	
	static List<Map<String,String>> vValuesMaps;
	
	
	public static void main(String [] args) throws NumberFormatException, IOException{
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		
		startingValue = 4;
		upValue = 2;
		downValue = 0.5;
		rate = 1.5;
		//p = 0.5;
		//q = 0.5;
		
		int length = 0; // initializing, will get updated later
		
		System.out.println("Enter a value for V: ");
		length = Integer.parseInt(read.readLine());//length being set to whatever number the user inputs.
		initiateMapsArrays(length);
		
		for(int i=length; i>0; i--){
			//System.out.println("\n\n");
			List<Integer> masks = grayCode(i);
			
			//sets integer as each value in the array of masks
			for(Integer integer:masks){
				//setting integer as a string representation
				//Append 0 to make strings of equal length
				String binaryString = Integer.toBinaryString(integer);
				
				
				//when v3(THH) binary string = 11. binaryString.length() is 2 and i is 3. continues until the first number is an H
				if(binaryString.length()<i){
					//difference is the diff between i and binarystring.length(). while doing Ts there is a diff.
					int difference=i-binaryString.length();
					
					//adding 0's after there are no more 1's(H) in the binary string. Only used for flips with T first.
					for(int d=0; d<difference;d++){
						binaryString="0"+binaryString;
					}
					
				}
				
				//doing the first part equals case only. if set n as 3, then i=3, length=3. only i changes. if n=3 then doing all equations with flips of 3
				if(i==length){

					//This section is for the remaining cases of the n's. (Not the first or second cases. n=3, Not (TTT) or (TTH))
					String firstPart=binaryString.substring(0,binaryString.lastIndexOf("1")+1);
					double tmpValue = 5-convertBinStrToDouble(binaryString);
					if(tmpValue < 0){
						tmpValue = 0;
					}
					tmpValue = (double)Math.round(tmpValue * 100d) / 100d;
					
					vValuesMaps.get(i-1).put(binaryString, tmpValue+"");
					//System.out.println("V" + i + "(" + convertBinStrToDouble(binaryString) + ")" + " =  " + tmpValue);
				}
				
				//For the second half equations. if n=3, then the 2's, 1's
				else{
					//if finding v2(HH) it sets first part as v3(HHH) and second part as v3(HHT)
					String firstPart = binaryString+"1";
					String secondPart = binaryString+"0";
					double tmpValue = (1/(1+rate))*(Double.parseDouble(vValuesMaps.get(i).get(firstPart))+Double.parseDouble(vValuesMaps.get(i).get(secondPart)));
					tmpValue = (double)Math.round(tmpValue * 100d) / 100d;

					vValuesMaps.get(i-1).put(binaryString, tmpValue+"");
					//System.out.println("V" + i + "(" + convertBinStrToDouble(binaryString) + ")" + " =  " + tmpValue);					
				}
			}
		}
		
		//calculating the 0 manually
		
		double vT = Double.parseDouble(vValuesMaps.get(0).get("0"));
		double vH = Double.parseDouble(vValuesMaps.get(0).get("1"));

		double v0 = (1/(1+rate))*((vH)+(vT));
		System.out.println();
		System.out.println("V0 = " + v0);
	}
	
	//This method is only for the first part equations, all the n's except for the first two (n=3, TTT and TTH)
	private static double convertBinStrToDoubleFirstPart(String encodedString) {
		double rtVal=startingValue;
		int hold = 0;
		int topHolder = 0;
		int holdValue = 4;
		for(int i=0; i<encodedString.length(); i++){
			if((encodedString.charAt(i)+"").equals("0")){
				rtVal=rtVal*downValue;
				hold--;
			}
			else{
				rtVal=rtVal*upValue;
				hold++;
				if(hold > topHolder){
					topHolder = hold;
				}
			}
		}
		for(int m = topHolder; m>0; m--){
			holdValue = holdValue*2;
		}
		rtVal = holdValue;
		return rtVal;
		
	}
	
	
	//Takes the binaryString of 0s and 1s and converts it to Ts and Hs
	private static String convertBinStrToCoinFlips(String encodedString) {
		String rtVal="";
		for(int i=0; i<encodedString.length(); i++){
			if((encodedString.charAt(i)+"").equals("0")){
				rtVal+="T";
			}
			else{
				rtVal+="H";
			}
		}
		return rtVal;
	}

	//Takes the binaryString of 0s and 1s and converts it to a value
	private static double convertBinStrToDouble(String encodedString) {
		double rtVal=startingValue;
		for(int i=0; i<encodedString.length(); i++){
			if((encodedString.charAt(i)+"").equals("0")){
				rtVal=rtVal*downValue;
			}
			else{
				rtVal=rtVal*upValue;
			}
		}
		return rtVal;
		
	}

	//putting each series into a list. ex: n=2, result will be [0,1,3,2]
	public static List<Integer> grayCode(int n) {
		if(n==0){
			List<Integer> result = new ArrayList<Integer>();
			result.add(0);
			return result;
		}
		
		List<Integer> result = grayCode(n-1);
		int numToAdd = 1<<(n-1);
		
		for(int i = result.size()-1; i>=0; i--){
			result.add(numToAdd+result.get(i));
		}
		return result;
	}

	//gives the values in in the arraylist hashmap values
	private static void initiateMapsArrays(int length) {
		vValuesMaps = new ArrayList<>();// created at beginning, starting a new arraylist.
		
		for(int i=0; i<length; i++){
			vValuesMaps.add(new HashMap<String, String>());
		}
	}
	
	
}

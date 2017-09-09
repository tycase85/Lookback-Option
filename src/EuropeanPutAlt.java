import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EuropeanPutAlt{
	
	public static void main(String[]args) throws NumberFormatException, IOException{
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter a value for V: ");
		int val = Integer.parseInt(read.readLine());//length being set to whatever number the user inputs.
		double[][] arrayVal = firstArrayCalc(val);
		//System.out.println(Arrays.deepToString(arrayVal));
		double finalVal = secondArrayCalc(arrayVal, val);
		System.out.println("v0: " + finalVal);
		//test();
	}
	
	public static double[][] firstArrayCalc(int val) throws NumberFormatException, IOException{
		//n and m are the largest they can be, set by the user input
		//n2 and m2 are the current places in the array for it to be set later. ex arrayVal[n2][m2] = 16 where n2 = 2 and m2 = 0
		
		double value = 0;
		int n = 0; 
		int m = 0;
		double rate = 0.4;
		

		int x = (int) Math.pow(2, val);
		double [][]arrayVal = new double[val+1][x];
		
		double [][]uniqueArray = new double[val+1][x];

		int firstVal = 4;
		arrayVal[n][m] = firstVal;
		
		int holdArray = m;
		
		
		for(int i=0; i<val; i++){
			n = i+1;
			x = (int) Math.pow(2, n);
			m = 0;
			holdArray = m;
			holdArray = holdArray/2;
			

			
			for(int j=0; j<x; j++){
				m = j;
				if((j&1) == 0){
					value = (double) arrayVal[n-1][holdArray];

					value = value*2;
					arrayVal[n][m] = value;

				}
				else{
					value = (double) arrayVal[n-1][holdArray];
					value = value/2;
					arrayVal[n][m] = value;
					holdArray++;
					
				}
			}
		}
		return arrayVal;
	}
	
	public static double secondArrayCalc(double [][]arrayVal, int val){
		
		int v = 0;
		int b = 0;

		double rate = 0.4;
		int x = (int) Math.pow(2, val);
		
		double [][]uniqueArray = new double[val+1][x];

		
		for(int i = 0; i < x; i++ ){
			double value = (double)arrayVal[val][i];
			double currVal = value;
			double currValUp = 5-(currVal*2);
			double currValDown = 5-(currVal/2);
			if(currValUp<=0){
				currValUp = 0;
			}
			if(currValDown<=0){
				currValDown = 0;
			}
			
			double firstPartEqn = rate*(currValUp+currValDown);

			uniqueArray[val][i] = firstPartEqn;

		}

		for(int k = val-1; k>=0; k--){
			v = k;
			b=0;
			
			
			double upVal = 0;
			double downVal = 0;
			
			int x2 = (int) Math.pow(2, val);

			for(int g = 0; g<x2; g++){
				double findVal = (double) arrayVal[k][b];
				double upHold = findVal * 2;
				double downHold = findVal /2;
				double testVal = (double) arrayVal[k+1][g];

				if(testVal == upHold){
					upVal = uniqueArray[k+1][g];
					
	
				}
				else if(testVal == downHold){
					downVal = uniqueArray[k+1][g];
					double secondPartEqn = rate*(upVal+downVal);

					v = k;
					uniqueArray[v][b] = secondPartEqn;
					b++;
				}
			
			
			}
			
			
		}
		

		double finalValue = (double) uniqueArray[0][0];
	
		return finalValue;
	}
	
	
	private static void test() throws NumberFormatException, IOException{
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter a value for V: ");
		int val = Integer.parseInt(read.readLine());//length being set to whatever number the user inputs.
		
		//n and m are the largest they can be, set by the user input
				//n2 and m2 are the current places in the array for it to be set later. ex arrayVal[n2][m2] = 16 where n2 = 2 and m2 = 0
				
				double value = 0;
				int n = 0; 
				int m = 0;
				double rate = 0.4;
				
				//System.out.println("N: " + n);
				//System.out.println("M: " + m);

				int x = (int) Math.pow(2, val);
				double [][]arrayVal = new double[val+1][x];
				
				double [][]uniqueArray = new double[val+1][x];
				//System.arraycopy(arrayVal, 0, uniqueArray, 0, arrayVal.length);
				
				//System.out.println("x: " + x);

				int firstVal = 4;
				arrayVal[n][m] = firstVal;
				
				int holdArray = m;
				
				
				for(int i=0; i<val; i++){
					n = i+1;
					x = (int) Math.pow(2, n);
					m = 0;
					holdArray = m;
					holdArray = holdArray/2;
					//double [][]holdVal = new double[n2-1][holdArray];
					

					
					for(int j=0; j<x; j++){
						//holdVal = new double[n2-1][holdArray]; 
						m = j;
						if((j&1) == 0){
							value = (double) arrayVal[n-1][holdArray];
							//System.out.println(value);

							value = value*2;
							arrayVal[n][m] = value;
							//System.out.println(arrayVal[n][m]);

						}
						else{
							value = (double) arrayVal[n-1][holdArray];
							value = value/2;
							arrayVal[n][m] = value;
							//System.out.println(arrayVal[n][m]);
							holdArray++;
							
						}
						
						if(n == val){
							//System.out.println("N: " + n);
							//System.out.println("M: " + m);

							double currVal = value;
							double currValUp = 5-(currVal*2);
							double currValDown = 5-(currVal/2);
							if(currValUp<=0){
								currValUp = 0;
							}
							if(currValDown<=0){
								currValDown = 0;
							}
							
							double firstPartEqn = rate*(currValUp+currValDown);

							uniqueArray[n][m] = firstPartEqn;

						}
						
						
						
						
						
					}
					
					
					
				
				}
				int v = 0;
				int b = 0;
				
				for(int k = val-1; k>=0; k--){
					v = k;
					b=0;
					
					
					double upVal = 0;
					double downVal = 0;
					
					int x2 = (int) Math.pow(2, val);

					for(int g = 0; g<x2; g++){
						double findVal = (double) arrayVal[k][b];
						double upHold = findVal * 2;
						double downHold = findVal /2;
						double testVal = (double) arrayVal[k+1][g];
						//System.out.println("[" + (k+1)+ "][" + g + "] " +"Test Val: " + testVal);
						//System.out.println("[" + (k+1)+ "][" + g + "] " +"upHold: " + upHold);
						//System.out.println("[" + (k+1)+ "][" + g + "] " +"downHold: " + downHold);

						if(testVal == upHold){
							upVal = uniqueArray[k+1][g];
							
			
						}
						else if(testVal == downHold){
							downVal = uniqueArray[k+1][g];
							double secondPartEqn = rate*(upVal+downVal);

							v = k;
							uniqueArray[v][b] = secondPartEqn;
							b++;
						}
					
					
					}
					
					
				
				
	}
				
				//value = (int) arrayVal[3][0];
				//System.out.println(value);
				//System.out.println(arrayVal[val][m]);

				double finalValue = (double) uniqueArray[0][0];
				//System.out.println("v0(4): " + finalValue);

				//System.out.println(Arrays.deepToString(arrayVal));
				//System.out.println("OLD METHOD: " + Arrays.deepToString(uniqueArray));
		
	}
	
	
	
}
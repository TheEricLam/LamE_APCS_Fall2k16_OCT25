/*
 * @author Eric Lam
@period 2
@date  9/30/16
 *
 */

import java.util.*;
public class Processor {

	public static void main(String[] args) {
		Scanner total = new Scanner(System.in);  //asks for user input; gets an integer
		System.out.println("How many integers do you have?");
		
		Scanner addNum = new Scanner(System.in); 
		System.out.println("Enter an integer."); 
		
		int evenMax = 0;
		int evenTotal = 0;
		int min = addNum.nextInt();
		int max = min;
		int inputNum = min;

		if (inputNum % 2 == 0){
			evenTotal += inputNum;
			inputNum = evenMax;
		}

		for (int i = 1; i <= total.nextInt(); i++){ // The loop asks user for more integers until number of integers is reached
			System.out.println("Enter an integer.");
			inputNum = addNum.nextInt();

			if (inputNum < min){ //A test to see which values should be saved as min
				min = inputNum;
			}else{
				max = inputNum;  //A test to see which values should be saved as max
			}

			if (inputNum % 2 == 0){  //A tests to see if integer is even, if true ten adds them together
				evenTotal += inputNum;
				evenMax = inputNum;
			}
			
		}
		//prints results
	System.out.println("The minimum is: " + min);
	System.out.println("The maximum is: " + max);
	System.out.println("The sum of all even numbers is: " + evenTotal);
	addNum.close();
	total.close();
	}
}
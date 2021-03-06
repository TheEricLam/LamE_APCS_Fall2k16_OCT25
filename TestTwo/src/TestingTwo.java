

import java.util.Scanner;

public class TestingTwo{

    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
   
    	System.out.println("Enter your operands, only two operands and a operator to compile.");
    	Scanner userInput = new Scanner(System.in);
    	String inputNum = userInput.nextLine();
    	while(inputNum != "quit"){
    		String[] answers = produceAnswer(inputNum);
    		System.out.println(answers[0]);
    		System.out.println(answers[1]);
    		
  
    		System.out.println(" ");
    		inputNum = userInput.nextLine();
    	}
  
    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    
    public static String[] produceAnswer(String input){
    	String[] finalAnswer = {" "," "};
    	int[] answers = new int[7];
    	int answersLength = answers.length;
    	
    	String[] inputParse = parseInput(input); // Splits the expression from space; gets 1st Operand, 2nd Operand, & operator
    	
    	//Takes the output of parseInput, and splits to find the whole, numerator, and denominator
    	int[] firstOperand = parseOperand(inputParse[0]);
    	int[] secondOperand = parseOperand(inputParse[2]);// The second operand of the expression
    	String operator = inputParse[1];
    	
    	for(int i = 0; i < 3; i++){
    		answers[i] = secondOperand[i];
    	}
    	
    	
    	finalAnswer[0] = "whole:" + answers[0] + " numerator:" + answers[1] + " denominator:" + answers[2];
    	
    	int[] improperFracOne = toImproperFrac(firstOperand);
    	int[] improperFracTwo = toImproperFrac(secondOperand);
    	
    	int[] operatorCalc = new int[3];
    	int lengthOfOpCalc = 0;
    	
    	if(operator.equals("+")||operator.equals("-")){
    		operatorCalc  = addSubtractFrac(improperFracOne, improperFracTwo, operator);
    		lengthOfOpCalc = operatorCalc.length;
    	}else{
    		operatorCalc = multiplyDivideFrac(improperFracOne, improperFracTwo, operator);
    		lengthOfOpCalc = operatorCalc.length;
    	}
    	
    	int j = 0;
    	int k = 3;
    	if(lengthOfOpCalc > 1){
    		
	    	while(j < lengthOfOpCalc){
	    		answers[k] = operatorCalc[j];
	    		j++;
	    		k++;
	    	}
	    	finalAnswer[1] = answers[3] + "_" + answers[4] + "/" + answers[5];
	    	return finalAnswer;
	    	
    	}else{
    		
    		answers[3] = operatorCalc[0];
    		finalAnswer[1] = "" + answers[3];
    		return finalAnswer;
    		
    	}


    }
    public static String[] parseInput(String inputExpression){
       String[] inputArray = inputExpression.split("\\s+"); //must split from the space since it will not always be a "+" operator
       return inputArray;							        
       
    }
    
   public static int[] parseOperand(String num){
	  int[] individualNum = {0, 0, 0};// collects the integers for whole,numerator, and denominator
	  					  // index 0 = whole; index 1 = numerator; index 2 = denominator
	  
	//checks if the expression has a "_"
	  if(num.indexOf("_" ) > 0){  
		  
	      String[] wholeNumSplit = num.split("_"); //splits to get get the whole number
	      individualNum[0] = Integer.parseInt(wholeNumSplit[0]); //changes the string into the return type, integer
	        
	      String[] numeratorSplit = wholeNumSplit[1].split("/"); // splits to get the numerator and denominator
	      individualNum[1] = Integer.parseInt(numeratorSplit[0]);// the numerator
	      individualNum[2] = Integer.parseInt(numeratorSplit[1]);// the denominator 
	       
	      return individualNum;
	 
	  //checks if the expression has a "/"
      }else if(num.indexOf("/") > 0){ 
    	  String[] numeratorSplit = num.split("/"); // splits to get the numerator and denominator
    	  individualNum[1] = Integer.parseInt(numeratorSplit[0]);// the numerator
	      individualNum[2] = Integer.parseInt(numeratorSplit[1]);// the denominator 
	      
	      return individualNum;
	      
	  //If the expression does not have a "_" or "/" then it is a whole number   
      }else{ 
    	  individualNum[0] = Integer.parseInt(num); 
    	  return individualNum;
      }
	
   }
   
   public static int[] toImproperFrac(int[] arrayNum){
	   int[] newFraction = {0,0};
		
	   if(arrayNum[1] == 0 || arrayNum[2] == 0){
		   newFraction[0] = arrayNum[0];
		   newFraction[1] = 1;
			
		   return newFraction; 
	   }else if(arrayNum[0] < 0){
		   newFraction[0] = (arrayNum[2] * arrayNum[0]) - arrayNum[1]; //if the whole number is negative; subtract 
		   newFraction[1] = arrayNum[2];//inputed denominator
			
		   return newFraction; 
	   }else{
		   int numerator = (arrayNum[2] * arrayNum[0]) + arrayNum[1]; //multiplies the denominator by the whole number, adds numerato
		   newFraction[0] = numerator; //new improper numerator value	
		   newFraction[1] = arrayNum[2];//inputed denominator
			
		   return newFraction;      
		}
   }
   public static int[] addSubtractFrac(int[] opOne, int[] opTwo, String operator){
	   int[] answer;
	   int numeratorOne = opOne[0] * opTwo[1];//cross multiply 1st operand's numerator w/ 2nd operand's denominator
	   int numeratorTwo = opOne[1] * opTwo[0];//cross multiply 1st operand's denominator w/ 2nd operand's numerator
	   int denominator = opOne[1] * opTwo[1];
	   
	   
	   int newNumerator;
	   if(operator.equals("+")){
		  newNumerator = numeratorOne + numeratorTwo;   
	   }else{
		    newNumerator = numeratorOne - numeratorTwo;
	   }
	   int wholeNum = newNumerator / denominator; //Calculation for the whole number
	  
	   
	   int remainder = newNumerator % denominator; //Calculation for the numerator
	   if(remainder == 0){
		   answer = new int[1];
		   answer[0] = wholeNum;
		   return answer;
	   }else{
		   answer = new int[3];
		   answer[0] = wholeNum;
		   answer[1] = remainder;
		   answer[2] = denominator;
		   return answer;
		   
	   }
   }
   public static int[] multiplyDivideFrac(int[] opOne, int[] opTwo, String operator){
	   int[] answer = {0,0,0}; //This method will always return 3 values
	   
	   /*both the numerator and denominator are set outside the if statement
	    * to stop repetition and is more efficient
	    */
	   int numerator; 
	   int denominator;
	   
	   if(operator.equals("/")){ //if divide, flips the operand Two's numerator and denominator, then multiplies
		   numerator = opOne[0] * opTwo[1];  // multiples after being "flipped"- gets the numerator
		   denominator = opOne[1] * opTwo[0]; // multiples after being "flipped"- gets the denominator
	   }else{
	    numerator = opOne[0] * opTwo[0]; 
	    denominator = opOne[1] * opTwo[1];
	   }
	   
	   int wholeNum = numerator / denominator; //Calculation for the whole number
	  
	   
	   int remainder = numerator % denominator; //Calculation for the numerator
	   if(remainder == 0){
		   answer = new int[1];
		   answer[0] = wholeNum;
		   return answer;
	   }else{
		   answer = new int[3];
		   answer[0] = wholeNum;
		   answer[1] = remainder;
		   answer[2] = denominator;
		   return answer;
		   
	   }

   }
   //This methods calculates the whole number and numerator
   public static int[] toMixedNum(int numer, int denom){
	   int[] mixedAnswer = {0,0};
	   mixedAnswer[0] = numer / denom; //Calculation for the whole number
	   mixedAnswer[1] = numer % denom; //Calculation for the numerator
	   return mixedAnswer;
   }
   
}



















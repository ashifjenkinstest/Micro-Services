package com.coronavirus.garbage;

public class TestRecursion {

	//Definition of the method
	static void printReverse(int n) { // 10 9 8 7  . . . . . 1 0
		if(n < 0) // 0 ,check if the number is less than  0 , since we are printing till 0, if we dont check this condition then the program will end p wit stack overflow
			return ; // return back to printReverse(10); ,This condition is to break out of the recursion calling
		printReverse(n - 1);// 9 8 7 6 . . . . . 0 , calls the printReverse( n - 1 ) to print the previous number
		//If Recursion calling is not the last statement of a method then all the statements after
		//Recursion call are executed and stored in a stack.
		//Once the recursion calling end they are popped out and the stack is emptied
		System.out.println(n + " ");// 1 2 3 4 . . . . . 10 
		 
	}
	
	public static void main(String[] args) {
		//Calling of the method
		TestRecursion.printReverse(10);//Passing 10 as an input to printReverse
		
	}

}

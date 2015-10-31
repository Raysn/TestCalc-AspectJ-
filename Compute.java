import java.io.*;

public class Compute {

	private double result;

	public Compute() {
		result = 0;
	}

	public double sum( double operand, int n ) {
		result = operand + n;
		return result;
	}

	public double subtract( double operand, int n ) {
		if( operand == 0 ) {
			result = n;
		} else {
			result = operand - n;
		}
		return result;
	}

	public double multiple( double operand, int n ) {
		if( operand == 0 ) {
			operand = 1;
		}
		result = operand * n;
		return result;
	}

	public double divide( double operand, int n ) {
		if( operand == 0 ) {
			result = n;
		} else {
			result = operand / n;
		}
		return result;
	}

	//ACする時とかに使う
	public void setZero() {
		result = 0;
	}

}

package example;

public class CramerEquation {

	double[] xMatrix;
	double[] yMatrix;

	/**
	 * CramerEquation constructor method for defining the x and y matrices
	 * 
	 * @param xMatrix
	 * @param yMatrix
	 */
	public CramerEquation(double[] xMatrix, double[] yMatrix) {
		this.xMatrix = xMatrix;
		this.yMatrix = yMatrix;
	}

	/**
	 * formEquation will construct the matrices for equation y = ax^2+bx+c
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static CramerEquation formEquation(double x, double y) {
		double[] equationXArray = {x * x, x, 1};
		double[] equationYArray = {y};
		return new CramerEquation(equationXArray, equationYArray);
	}

	/**
	 * Common method for solving the determinant of the matrix
	 * 
	 * @param determinantMatrix
	 * @return
	 */
	public static double determinant(double[][] determinantMatrix){
		return determinantMatrix[0][0]*((determinantMatrix[1][1]*determinantMatrix[2][2])-(determinantMatrix[1][2]*determinantMatrix[2][1])) -
				(determinantMatrix[0][1]*((determinantMatrix[1][0]*determinantMatrix[2][2])-(determinantMatrix[1][2]*determinantMatrix[2][0]))) +
				(determinantMatrix[0][2]*((determinantMatrix[1][0]*determinantMatrix[2][1])-(determinantMatrix[1][1]*determinantMatrix[2][0])));
	}

	/**
	 * replace column will change the 3x3 matrix with y matrix for calling the common determinant method
	 * 
	 * @param xMatrix
	 * @param yMatrix
	 * @param replaceRow
	 * @return
	 */
	public static double[][] replaceColumn(double[][] xMatrix, double[][] yMatrix, int replaceRow){
		int rowLength = xMatrix.length;
		int columnLength = xMatrix[0].length;
		double bufferMatrix[][] = new double [3][3]; 
		for(int rowIterator = 0; rowIterator < rowLength;rowIterator++){
			for(int columnIterator = 0; columnIterator < columnLength; columnIterator++){
				if(columnIterator != replaceRow)
					bufferMatrix[rowIterator][columnIterator] = xMatrix[rowIterator][columnIterator];
				else
					bufferMatrix[rowIterator][columnIterator] = yMatrix[rowIterator][0];
			}
		}
		return bufferMatrix;
	}

	/**
	 * cramer's equation can be solved which returns the result to the main method
	 * 
	 * @param xMatrix
	 * @param yMatrix
	 * @return
	 */
	public static double[] crame(double[][] xMatrix, double[][] yMatrix) {
		double[] result = new double[3];
		double determinantMatrix = determinant(xMatrix);
		result[0] = determinant(replaceColumn(xMatrix, yMatrix, 0)) / determinantMatrix;
		result[1] = determinant(replaceColumn(xMatrix, yMatrix, 1)) / determinantMatrix;
		result[2] = determinant(replaceColumn(xMatrix,yMatrix, 2)) / determinantMatrix;
		return result;
	}

	/**
	 * main method where inputs are given for solving a, b and c
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		double[][] xEquation = new double[3][3];
		double[][] yEquation = new double[3][0];

		CramerEquation minValue = formEquation(0, 1);
		xEquation[0] = minValue.xMatrix;
		yEquation[0] = minValue.yMatrix;

		CramerEquation midValue = formEquation(0.25, 1.27);
		xEquation[1] = midValue.xMatrix;
		yEquation[1] = midValue.yMatrix;

		CramerEquation maxValue = formEquation(0.5, 1.55);
		xEquation[2] = maxValue.xMatrix;
		yEquation[2] = maxValue.yMatrix;

		double[] result = crame(xEquation,yEquation);
		System.out.println("a: " + result[0] + ", b: " + result[1] + ", c: " + result[2]);
	}
}

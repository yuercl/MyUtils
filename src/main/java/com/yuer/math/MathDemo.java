package com.yuer.math;

import java.io.IOException;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class MathDemo {

	public static void main(String[] args) throws IOException {

		RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] { { 1, 1, 1 }, { 1, 2, 3 }, { 1, 1, 2 } }, false);
		DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
		RealVector constants = new ArrayRealVector(new double[] { 1, -2, 1 }, false);
		RealVector solution = solver.solve(constants);

		System.out.println(solution);

		// x + y + z = 1
		// x + 2y + 3z = -2
		// x + y + 2z = 1
		// x = 4
		// y = -3
		// z = 0

		System.out.println(coefficients.getTrace());

		// Create a real matrix with two rows and three columns, using a factory
		// method that selects the implementation class for us.
		double[][] matrixData = { { 1d, 2d, 3d }, { 2d, 5d, 3d } };
		RealMatrix m = MatrixUtils.createRealMatrix(matrixData);

		// One more with three rows, two columns, this time instantiating the
		// RealMatrix implementation class directly.
		double[][] matrixData2 = { { 1d, 2d }, { 2d, 5d }, { 1d, 7d } };
		RealMatrix n = new Array2DRowRealMatrix(matrixData2);

		// Note: The constructor copies the input double[][] array in both
		// cases.

		// Now multiply m by n
		RealMatrix p = m.multiply(n);
		System.out.println(p.getRowDimension()); // 2
		System.out.println(p.getColumnDimension()); // 2

		// Invert p, using LU decomposition
		RealMatrix pInverse = new LUDecomposition(p).getSolver().getInverse();
	}
}
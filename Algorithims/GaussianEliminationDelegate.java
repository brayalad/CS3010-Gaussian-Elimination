package Algorithims;

import Models.Coefficients;
import Models.Constants;
import Models.GaussianMatrix;

import java.util.List;
import java.util.Map;
import java.util.Vector;

public class GaussianEliminationDelegate {
    private final Map<String, GaussianElimination> algorithms;

    public GaussianEliminationDelegate(final Map<String, GaussianElimination> algorithms){
        this.algorithms = algorithms;
    }


    public List<Double> gaussianElimination(final GaussianMatrix<Double> gaussianMatrix, final String method){
        final Coefficients<Double> coefficients = Coefficients.copyOf(gaussianMatrix.getCoefficients());
        final Constants<Double> constants = Constants.copyOf(gaussianMatrix.getConstants());
        final List<Integer> order = new Vector<>(gaussianMatrix.getInd());

        final GaussianElimination algorithm = algorithms.get(method);


        return algorithm.gaussianElimination(coefficients,constants, order);
    }

}

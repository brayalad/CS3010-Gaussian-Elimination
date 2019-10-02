package Algorithims;

import Models.Coefficients;
import Models.Constants;
import Models.GaussianMatrix;

import java.util.List;

public interface GaussianElimination {

    List<Double> gaussianElimination(Coefficients<Double> coefficients, Constants<Double> constants, final List<Integer> ind);

    GaussianMatrix<Double> forwardElimination(Coefficients<Double> coefficients, Constants<Double> constants, List<Integer> ind);

    List<Double> backSubstitution(Coefficients<Double> coefficients, Constants<Double> constants, List<Integer> ind);

}

package Algorithims;

import Models.Coefficients;
import Models.Constants;
import Models.GaussianMatrix;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class NaiveGaussianElimination implements GaussianElimination {

    @Override
    public List<Double> gaussianElimination(final Coefficients<Double> coeffs, final Constants<Double> consts, final List<Integer> ind){
        final Coefficients<Double> coefficients = Coefficients.copyOf(coeffs);
        final Constants<Double> constants = Constants.copyOf(consts);
        final GaussianMatrix<Double> gaussianMatrix = forwardElimination(coefficients, constants, null);

        return backSubstitution(gaussianMatrix.getCoefficients(), gaussianMatrix.getConstants(), null);
    }

    @Override
    public GaussianMatrix<Double> forwardElimination(final Coefficients<Double> coeffs, final Constants<Double> consts, final List<Integer> ind){
        final Coefficients<Double> coefficients = Coefficients.copyOf(coeffs);
        final Constants<Double> constants = Constants.copyOf(consts);

        final int size = coefficients.size();

        for(int i = 0; i < size - 1; ++i){
            for(int j = i + 1; j < size; ++j){
                final Double mult = coefficients.get(j,i) / coefficients.get(i,i);
                for(int k = i; k < size; ++k){
                    final Double newValue = coefficients.get(j,k) - mult * coefficients.get(i,k);
                    coefficients.set(j,k,newValue);
                }
                constants.set(j, (constants.get(j) - mult * constants.get(i)));
            }
        }

        return new GaussianMatrix<>(size, coefficients, constants);
    }

    @Override
    public List<Double> backSubstitution(final Coefficients<Double> coefficients, final Constants<Double> constants, final List<Integer> ind){
        final int size = constants.size();

        final List<Double> solution = new Vector<>(Collections.nCopies(size, 0.0));
        solution.set(size - 1, (constants.get(size - 1) / coefficients.get(size - 1, size - 1)));
        for(int i = size - 2; i >= 0; --i){
            double sum = constants.get(i);
            for(int j = i + 1; j < size; ++j){
                sum = sum - coefficients.get(i,j) * solution.get(j);
            }
            solution.set(i, (sum / coefficients.get(i,i)));
        }

        return solution;
    }

}

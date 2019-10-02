package Algorithims;

import Models.Coefficients;
import Models.Constants;
import Models.GaussianMatrix;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class ScaledPartialPivoting implements GaussianElimination{

    @Override
    public List<Double> gaussianElimination(final Coefficients<Double> coeffs, final Constants<Double> consts, final List<Integer> ind){
        final Coefficients<Double> coefficients = Coefficients.copyOf(coeffs);
        final Constants<Double> constants = Constants.copyOf(consts);
        final List<Integer> order = new Vector<>(ind);
        final GaussianMatrix<Double> gaussianMatrix = forwardElimination(coefficients, constants, order);


        return backSubstitution(gaussianMatrix.getCoefficients(), gaussianMatrix.getConstants(), order);
    }

    @Override
    public GaussianMatrix<Double> forwardElimination(final Coefficients<Double> coeffs, final Constants<Double> consts, final List<Integer> ind){
        final Coefficients<Double> coefficients = Coefficients.copyOf(coeffs);
        final Constants<Double> constants = Constants.copyOf(consts);

        final int size = coefficients.size();

        final List<Double> scaling = new Vector<>(Collections.nCopies(size, 0.0));
        for(int i = 0; i < size; ++i){
            double smax = 0.0;
            for(int j = 0; j < size; ++j){
                smax = Math.max(smax, Math.abs(coefficients.get(i,j)));
            }
            scaling.set(i,smax);
        }

        for(int k = 0; k < size - 1; ++k){
            Double rmax = 0.0;
            int maxIndex = k;

            for(int i = k; i < size; ++i){
                Double r = Math.abs(coefficients.get(ind.get(i),k) / scaling.get(ind.get(i)));
                if(r > rmax){
                    rmax = r;
                    maxIndex = i;
                }
            }

            Collections.swap(ind, maxIndex, k);


            for(int i = k + 1; i < size; ++i){
                Double mult = coefficients.get(ind.get(i),k) / coefficients.get(ind.get(k), k);
                for(int j = k + 1; j < size; ++j){
                    coefficients.set(ind.get(i),j, (coefficients.get(ind.get(i),j) - mult * coefficients.get(ind.get(k), j)));
                }

                constants.set(ind.get(i), (constants.get(ind.get(i)) - mult * constants.get(ind.get(k))));
            }
        }

        return new GaussianMatrix<>(size, coefficients, constants);
    }

    @Override
    public List<Double> backSubstitution(final Coefficients<Double> coefficients, final Constants<Double> constants, final List<Integer> ind){
        final int size = constants.size();

        final List<Double> solution = new Vector<>(Collections.nCopies(size, 0.0));
        solution.set(size - 1, (constants.get(ind.get(size - 1)) / coefficients.get(ind.get(size - 1), size - 1)));
        for(int i = size - 1; i >= 0; --i){
            Double sum = constants.get(ind.get(i));
            for(int j = i + 1; j < size; ++j){
                sum -= coefficients.get(ind.get(i),j) * solution.get(j);
            }
            solution.set(i, (sum / coefficients.get(ind.get(i),i)));
        }

        return solution;
    }
}

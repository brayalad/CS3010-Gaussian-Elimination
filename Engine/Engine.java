package Engine;

import Algorithims.GaussianEliminationDelegate;
import Models.GaussianMatrix;
import UI.UserInterface;
import Utils.FileParser;

import java.io.IOException;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Engine {
    private static final String SCALED_PARTIAL_PIVOTING_FLAG = "--spp";
    private static final String NAIVE_GAUSSIAN_ELIMINATION_FLAG = "--nge";
    private static final String INVALID_INPUT = "Input is Invalid.";
    private static final String FILE_NAME_ERROR = "Only 1 file can be used. Must end with extension " + FileParser.MATRIX_FILE_EXTENSION;
    private static final String INVALID_FLAG_FORMAT = "%s is not a valid option.";
    private final GaussianEliminationDelegate gaussianEliminationDelegate;
    private final UserInterface userInterface;

    public Engine(final GaussianEliminationDelegate gaussianEliminationDelegate, final UserInterface userInterface){
        this.gaussianEliminationDelegate = gaussianEliminationDelegate;
        this.userInterface = userInterface;
    }

    public void start(final List<String> arguments){
        try {
            final Set<String> matrixFileNames = arguments.stream()
                    .filter(s -> s.endsWith(FileParser.MATRIX_FILE_EXTENSION))
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            if(matrixFileNames.size() != 1){
                throw new RuntimeException(FILE_NAME_ERROR);
            }

            final String matrixFileName = matrixFileNames.iterator().next().replaceAll(FileParser.MATRIX_FILE_EXTENSION, "");

            if (arguments.contains(SCALED_PARTIAL_PIVOTING_FLAG)) {
                execute(matrixFileName, SCALED_PARTIAL_PIVOTING_FLAG);
            } else {
                execute(matrixFileName, NAIVE_GAUSSIAN_ELIMINATION_FLAG);
            }

            //run();
        } catch (RuntimeException | IOException e){
            userInterface.printError(e.getLocalizedMessage());
        }
    }

    private void run() throws IOException {
        String input;
        do {
            userInterface.menu();
            input = userInterface.getInput();
            if(input.equals("1")){
                final String filePath = userInterface.getInput();
                userInterface.algorithms();
                final String algo = userInterface.getInput();
                if(algo.equals("1")){
                    execute(filePath, NAIVE_GAUSSIAN_ELIMINATION_FLAG);
                } else if(algo.equals("2")){
                    execute(filePath, SCALED_PARTIAL_PIVOTING_FLAG);
                } else {
                    userInterface.invalidInput();
                }
            }
        } while(!input.equals("2"));
    }

    private void execute(final String matrixFileName, final String method) throws IOException {
        final GaussianMatrix<Double> gaussianMatrix = FileParser.readFromMatrixFile(String.format(FileParser.MATRIX_FILE_FORMAT, matrixFileName));

        List<Double> solution;

        if(method.equals(SCALED_PARTIAL_PIVOTING_FLAG)){
            solution = gaussianEliminationDelegate.gaussianElimination(gaussianMatrix, SCALED_PARTIAL_PIVOTING_FLAG);
        } else {
            solution = gaussianEliminationDelegate.gaussianElimination(gaussianMatrix, NAIVE_GAUSSIAN_ELIMINATION_FLAG);
        }


        FileParser.writeToSolutionFile(solution, String.format(FileParser.SOLUTION_FILE_FORMAT, matrixFileName));

        userInterface.operation(solution, matrixFileName);

    }



}

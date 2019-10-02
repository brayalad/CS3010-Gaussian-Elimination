package Utils;

import Models.Coefficients;
import Models.Constants;
import Models.GaussianMatrix;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;


public class FileParser {
    public static final String MATRIX_FILE_EXTENSION = ".lin";
    public static final String SOLUTION_FILE_EXTENSION = ".sol";
    public static final String MATRIX_FILE_FORMAT = "%s.lin";
    public static final String SOLUTION_FILE_FORMAT = "%s.sol";
    private static final String DELIMITER = " ";
    private static final String PW_WRITE_FORMAT = "%s ";
    private static final String FILE_EXTENSION_ERROR_FORMAT = "%s is not a supported file type";

    private FileParser(){}

    public static GaussianMatrix<Double> readFromMatrixFile(final String matrixFileName) throws IOException{
        if(!matrixFileName.endsWith(MATRIX_FILE_EXTENSION)){
            throw new RuntimeException(String.format(FILE_EXTENSION_ERROR_FORMAT, matrixFileName));
        }

        final Path filePath = Paths.get(matrixFileName);
        final List<String> linesInFile = Files.readAllLines(filePath);

        final int size = Integer.parseInt(linesInFile.get(0));
        final List<List<Double>> matrix = parseMatrix(linesInFile.subList(1, linesInFile.size() - 1), size);
        final List<Double> constants = parseRow(linesInFile.get(linesInFile.size() - 1), size);


        return new GaussianMatrix<>(size, new Coefficients<>(matrix), new Constants<>(constants));
    }

    public static GaussianMatrix<Double> readFromMatrixFileWithScanner(final String fileName) throws IOException {
        final Scanner sc = new Scanner(new File(fileName));
        final List<String> lines = new Vector<>();
        while(sc.hasNext()){
            lines.add(sc.nextLine());
        }
        sc.close();

        final int size = Integer.parseInt(lines.get(0));
        final List<List<Double>> matrix = parseMatrix(lines.subList(1, lines.size() - 1), size);
        final List<Double> constants = parseRow(lines.get(lines.size() - 1), size);


        return new GaussianMatrix<>(size, new Coefficients<>(matrix), new Constants<>(constants));
    }

    public static void writeToSolutionFile(final List<Double> solution, final String fileName) throws IOException {
        if(!fileName.endsWith(SOLUTION_FILE_EXTENSION)){
            throw new RuntimeException(String.format(FILE_EXTENSION_ERROR_FORMAT, fileName));
        }

        final PrintWriter pw = new PrintWriter(new FileWriter(new File(fileName)));
        pw.println(rowToString(solution));
        pw.close();
    }

    public static void writeToSolutionFile(final GaussianMatrix<Double> gaussianMatrix, final String fileName) throws IOException {
        if(!fileName.endsWith(SOLUTION_FILE_EXTENSION)){
            throw new RuntimeException(String.format(FILE_EXTENSION_ERROR_FORMAT, fileName));
        }

        final PrintWriter pw = new PrintWriter(new FileWriter(new File(fileName)));
        pw.println(gaussianMatrix.size());
        for(final List<Double> row : gaussianMatrix.getCoefficients().getMatrix()){
            pw.println(rowToString(row));
        }
        pw.println(rowToString(gaussianMatrix.getConstants().getConstants()));
        pw.close();
    }

    private static List<Double> parseRow(final String rowLine, final int size) {
        final List<Double> row = new Vector<>(size);
        for(final String s : rowLine.split(DELIMITER)){
            if(s != null && !s.isEmpty()){
                row.add(Double.parseDouble(s));
            }
        }

        return row;
    }

    private static List<List<Double>> parseMatrix(final List<String> matrixRows, final int size){
        final List<List<Double>> matrix = new Vector<>(size);
        for(final String row : matrixRows){
            matrix.add(parseRow(row, size));
        }

        return matrix;
    }

    private static String rowToString(final List<Double> row){
        final StringBuilder sb = new StringBuilder();
        for(final Double n : row) {
            sb.append(String.format(PW_WRITE_FORMAT, String.valueOf(n)));
        }

        return sb.toString();
    }

}

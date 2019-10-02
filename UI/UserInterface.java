package UI;

import Models.GaussianMatrix;

import java.util.List;

public interface UserInterface {
    String PRINT_MATRIX_FORMAT = "\nGaussian Matrix:\n %s\n";
    String MENU = "\nMenu Options:\n 1) Run algorithm on another file.\n 2) Exit\n";
    String ENTER_FILE = "Please Enter File Path: ";
    String ALGORITHM_MENU = "\nAvailable Algorithms:\n 1) Naive Gaussian Elimination\n 2) Scaled Partial Pivoting\n";
    String ENTER_CHOICE = "Please Enter Choice: ";
    String ERROR_FORMAT = "\nProgram had an error with message: %s\n";
    String OPERATION_FORMAT = "Solution: \n%s\nwas written to file : %s.sol\n";
    String INVALID_INPUT = "\nInput is invalid. Please try again.";

    void menu();

    void algorithms();

    void printError(String error);

    void printMatrix(GaussianMatrix<?> gaussianMatrix);

    void operation(List<?> gaussianMatrix, String fileName);

    String getInput();

    void invalidInput();
}

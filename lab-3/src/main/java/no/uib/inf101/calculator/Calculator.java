package no.uib.inf101.calculator;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a calculator that can evaluate mathematical expressions.
 * The calculator supports various operations through the use of
 * <code>Operator</code>
 * implementations, which define the behavior for specific mathematical
 * operators.
 */
public class Calculator {

    public Calculator() {
    }

    /**
     * Retrieves a list of all operator symbols supported by the calculator.
     *
     * @return a list of operator symbols
     */
    public List<String> getOperatorSymbols() {
        return Arrays.asList("+", "-", "*");
    }

    /**
     * Retrieves the operators description.
     *
     * @return a string of the operator's description
     * @throws NullPointerException if the operator is not found in the calculator
     */
    public String getOperatorDescription(String operatorSymbol) {
        switch (operatorSymbol) {
            case "+":
                return "Addition: \"Combine two numbers to find their total or sum.\"";
            case "-":
                return "Subtraction: \"Find the difference between two numbers by taking one away from the other.\"";
            case "*":
                return "Multiplication: \"Calculate the result of multiplying one number by another.\"";
            default:
                throw new NullPointerException("This operator is not supported by the calculator: " + operatorSymbol);
        }
    }

    /**
     * Performs a calculation between two numbers using a specified operator.
     *
     * @param num1           the first operand
     * @param num2           the second operand
     * @param operatorSymbol the symbol of the operator to use
     * @return the result of the calculation
     * @throws NullPointerException if the operator is not found in the calculator
     */
    public double evaluate(double num1, double num2, String operatorSymbol) {
        switch (operatorSymbol) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            default:
                throw new NullPointerException("This operator is not supported by the calculator: " + operatorSymbol);
        }
    }










    
    // ################# NO NEED TO LOOK AT THIS METHOD #################
    /**
     * Evaluates a mathematical expression represented as an <code>Expression</code>
     * object.
     *
     * @param expression the expression to evaluate
     * @return the result of the evaluation as a double
     * @throws NullPointerException if the operator in the expression is not
     *                              supported
     */
    public double evaluate(Expression expression) {
        if (expression.isNumber())
            return expression.getNumberValue();

        Expression operand1 = expression.getOperand1();
        Expression operand2 = expression.getOperand2();
        String operatorSymbol = expression.getOperator();
        if (!getOperatorSymbols().contains(operatorSymbol))
            throw new IllegalArgumentException("The operator is not supported by the caluclator: " + operatorSymbol);

        return evaluate(evaluate(operand1), evaluate(operand2), operatorSymbol);
    }

}

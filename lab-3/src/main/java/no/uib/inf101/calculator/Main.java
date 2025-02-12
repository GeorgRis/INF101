package no.uib.inf101.calculator;

import no.uib.inf101.calculator.gui.CalculatorGUI;

public class Main {

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		new CalculatorGUI(calculator);
	}	
}

package jcalc;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame implements ActionListener {
	JButton calcButtonC, calcButtondelete;
	Container cont;
	String expression = "";
	TextArea t, tt;

	public UserInterface() {
		setTitle("Calculatrice");
		setResizable(false);

		setLayout(new GridLayout(5, 3));
		cont = getContentPane();

		t = new TextArea(expression, 5, 50, TextArea.SCROLLBARS_NONE);
		t.setEditable(false);
		t.setFont(new Font("Consolas", Font.BOLD, 20));
		add(t);

		tt = new TextArea(expression, 5, 50, TextArea.SCROLLBARS_NONE);
		tt.setEditable(false);
		tt.setFont(new Font("Consolas", Font.BOLD, 20));
		add(tt);

		String touches[] = { "C", "←", "1", "2", "3", "+", "4", "5", "6", "-",
				"7", "8", "9", "*", ".", "0", "=", "/" };
		for (int i = 0; i < touches.length; i++) {
			JButton b = new JButton(touches[i]);
			b.addActionListener(this);
			add(b);
			b.setFont(new Font("Consolas", Font.BOLD, 30));
		}

		setSize(900, 400);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "C")
			expression = "";
		else if (e.getActionCommand() == "←")
			expression = expression.substring(0, expression.length() - 1);
		else if (e.getActionCommand() == "=")
			tt.setText(String.valueOf(evaluateExpression(expression)));
		else
			expression += e.getActionCommand();

		t.setText(expression);
	}

	public double applyOperation(double a, double b, char operation) {
		System.out.println("J'applique l'opération " + a + operation + b);
		if (operation == '+')
			return a + b;
		else if (operation == '-')
			return a - b;
		else if (operation == '*')
			return a * b;
		else if (operation == '/')
			return a / b;
		else
			return 0;
	}

	public double evaluateExpression(String expression) {
		System.out.println("Evaluation de l'expression : " + expression);

		double a = 0, b = 0;
		int l = 0;
		char operation = ' ';

		boolean priorite = false;

		for (int i = 0; i < expression.length(); i++) {
			if ("+-*/".indexOf(expression.charAt(i)) != -1) {
				if (priorite == true) {
					if (operation == ' ')
						a = Double.parseDouble(expression.substring(l, i));
					else
						b = Double.parseDouble(expression.substring(l, i));

					l = i + 1;

					if (operation == '*' || operation == '/') {
						a = applyOperation(a, b, operation);
						b = 0;
						expression = expression.substring(0, l);
					}
					operation = expression.charAt(i);
				} else {
					if (operation == ' ')
						a = Double.parseDouble(expression.substring(l, i));
					else
						b = Double.parseDouble(expression.substring(l, i));

					l = i + 1;

					if (operation != ' ') {
						a = applyOperation(a, b, operation);
						b = 0;
					}
					operation = expression.charAt(i);
				}
			}
		}
		b = Double.parseDouble(expression.substring(l));
		return applyOperation(a, b, operation);
	}
}

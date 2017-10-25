package jcalc;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame implements ActionListener {
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
		t.setFont(new Font("Consolas", Font.BOLD, 15));
		add(t);

		tt = new TextArea(expression, 5, 50, TextArea.SCROLLBARS_NONE);
		tt.setEditable(false);
		tt.setFont(new Font("Consolas", Font.BOLD, 15));
		add(tt);

		String touches[] = { "C", "←", "1", "2", "3", "+", "4", "5", "6", "-",
				"7", "8", "9", "*", ".", "0", "=", "/" };
		for (int i = 0; i < touches.length; i++) {
			JButton b = new JButton(touches[i]);
			b.addActionListener(this);
			add(b);
			b.setFont(new Font("Consolas", Font.BOLD, 30));
		}

		setSize(900, 300);
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
		System.out.println("J'applique l'opération " + a + " " + operation
				+ " " + b);
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
		System.out.println("");
		double a = 0;
		int j = 0, k = 0, l = 0;

		while (expression.indexOf('*') != -1 || expression.indexOf('/') != -1) {
			k = Math.min(expression.indexOf('*'), expression.indexOf('/'));
			if (k < 0)
				k = Math.max(expression.indexOf('*'), expression.indexOf('/'));
			j = k - 1;
			l = k + 1;
			while (j > 0 && "+-/*".indexOf(expression.charAt(j - 1)) == -1)
				j--;
			while (l < expression.length() - 1
					&& "+-/*".indexOf(expression.charAt(l + 1)) == -1)
				l++;
			a = applyOperation(Double.parseDouble(expression.substring(j, k)),
					Double.parseDouble(expression.substring(k + 1, l + 1)),
					expression.charAt(k));
			expression = expression.substring(0, j) + a
					+ expression.substring(l + 1);
			System.out.println("L'expression evolue en : " + expression);
		}

		while (expression.indexOf('+') != -1 || expression.indexOf('-') != -1) {
			k = Math.min(expression.indexOf('+'), expression.indexOf('-'));
			if (k < 0)
				k = Math.max(expression.indexOf('+'), expression.indexOf('-'));
			if (k == 0)
				break;
			j = k - 1;
			l = k + 1;
			while (j > 0 && "+-/*".indexOf(expression.charAt(j - 1)) == -1)
				j--;
			while (l < expression.length() - 1
					&& "+-/*".indexOf(expression.charAt(l + 1)) == -1)
				l++;
			a = applyOperation(Double.parseDouble(expression.substring(j, k)),
					Double.parseDouble(expression.substring(k + 1, l + 1)),
					expression.charAt(k));
			expression = expression.substring(0, j) + a
					+ expression.substring(l + 1);
			System.out.println("L'expression evolue en : " + expression);
		}
		return Double.parseDouble(expression);
	}
}

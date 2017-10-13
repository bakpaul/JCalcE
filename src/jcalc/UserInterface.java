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

		setLayout(new GridLayout(6, 3));
		cont = getContentPane();

		calcButtonC = new JButton("C");
		calcButtonC.addActionListener(this);
		add(calcButtonC);

		t = new TextArea(expression, 5, 50, TextArea.SCROLLBARS_NONE);
		t.setEditable(false);
		add(t);

		tt = new TextArea(expression, 5, 50, TextArea.SCROLLBARS_NONE);
		tt.setEditable(false);
		add(tt);

		for (int i = 0; i < 10; i++) {
			JButton b = new JButton(i + "");
			b.addActionListener(this);
			add(b);
		}
		
		String operateurs[] = {"+", "-", "*", "/", "=", "."};
		for (int i = 0; i < operateurs.length; i++) {
			JButton b = new JButton(operateurs[i]);
			b.addActionListener(this);
			add(b);
		}
		
		String parentheses[] = {"(", ")"};
		for (int i = 0; i < parentheses.length; i++) {
			JButton b = new JButton(parentheses[i]);
			b.addActionListener(this);
			add(b);
		}


		setSize(600, 600);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == calcButtonC)
			expression = "";
		else if (e.getActionCommand() == "=")
			tt.setText(String.valueOf(evaluateExpression(expression)));
		else
			expression += e.getActionCommand();

		t.setText(expression);
	}

	public double applyOperation(double a, double b, char operation) {
		if (operation == '+')
			return a + b;
		else if (operation == '-')
			return a - b;
		else
			return 0;
	}

	public double evaluateExpression(String expression) {
		double a = 0, b = 0;
		int l = 0;
		char operation = ' ';
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) != '+' && expression.charAt(i) != '-'
					&& expression.charAt(i) != '('
					&& expression.charAt(i) != ')') {
			} else {
				System.out.println(l);
				if (operation == ' ')
					a = Double.parseDouble(expression.substring(l, i));
				else
					b = Double.parseDouble(expression.substring(l, i));
				
				System.out.print(a + " " + b);
				
				l = i + 1;
				
				if (operation != ' ') {
					a = applyOperation(a, b, operation);
					b = 0;
				}
				operation = expression.charAt(i);
			}
		}
		b = Double.parseDouble(expression.substring(l));
		System.out.println(applyOperation(a, b, operation));
		return applyOperation(a, b, operation);
	}
}

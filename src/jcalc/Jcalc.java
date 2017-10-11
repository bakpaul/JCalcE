package jcalc;

import javax.swing.JOptionPane;

public class Jcalc {
	public static void main(String[] args) {
		String s = JOptionPane.showInputDialog(null, "Saisir l'expression", "Quel est l'expression",
				JOptionPane.WARNING_MESSAGE);
		System.out.println(parenthesisCheck(s));
	}
	
	public static Boolean parenthesisCheck(String s) {
		int n = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(')
				n += 1;
			if(s.charAt(i) == ')')
				n -= 1;
			if(n < 0) {
				return false;
			}
		}
		if(n == 0)
			return true;
		else
			return false;
	}

}

package br.com.unicap.bd2.util;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public abstract class ShowMessage {

	public static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "ERRO!", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showScrollMessage(String message, String title) {
		JTextArea textArea = new JTextArea(message);
		textArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setPreferredSize(new Dimension(400, 400));
		JOptionPane.showMessageDialog(null, scroll, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void showInfomationMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

}

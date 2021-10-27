package br.com.unicap.bd2.view;

import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.com.unicap.bd2.controller.ControllerGeneric;
import br.com.unicap.bd2.util.ShowMessage;

public class MainWindowView {
	
	private ControllerGeneric controller = new ControllerGeneric();
	
	public void query() {
		try {
			JTextArea textArea = new JTextArea();
			JScrollPane scroll = new JScrollPane(textArea);
			scroll.setPreferredSize(new Dimension(400, 400));
			int opcao= JOptionPane.showConfirmDialog(null, scroll, "Informe sua Consulta", JOptionPane.OK_CANCEL_OPTION);
			if(opcao == JOptionPane.OK_OPTION) {
				String result= this.controller.query(textArea.getText());
				ShowMessage.showScrollMessage(result, "Resultado da consulta");
			}
			
		}catch(SQLException e) {
			ShowMessage.showErrorMessage(e.getMessage());
		}
	}

}

package br.com.unicap.bd2.view;

import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.unicap.bd2.controller.ControllerGeneric;
import br.com.unicap.bd2.util.ShowMessage;

public class MainWindowView {
	
	private ControllerGeneric controller = new ControllerGeneric();
	
	private JTextField country = new JTextField(15);
	private JTextField month= new JTextField(2);
	
	private Object[] input= {"Informe o país: ", this.country, "Informe o mês: ", this.month};
	
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
	
	public void executeProcedure() {
		try {
			int opcao = JOptionPane.showConfirmDialog(null, this.input, "Infome os dados", JOptionPane.OK_CANCEL_OPTION);
			if(opcao == JOptionPane.OK_OPTION) {
				String[] inputs = this.inputFields(this.input);
				String result= this.controller.executeProcedure(inputs);
				ShowMessage.showScrollMessage(result, "Sucesso!");
			}
			
		}catch(SQLException e) {
			ShowMessage.showErrorMessage(e.getMessage());
		}finally {
			this.cleanInputFields(this.input);
		}
	}
	
	public void setProcedure() {
		try {
			this.controller.setProcedure();
		}catch(SQLException e) {
			ShowMessage.showErrorMessage(e.getMessage());
		}
	}
	
	private String[] inputFields(Object[] input) {
		String[] fields = new String[input.length / 2];
		int cont = 0;

		for (int i = 0; i < input.length; i++) {
			if ((i % 2 != 0)) {
				JTextField aux = (JTextField) input[i];
				fields[cont] = aux.getText().trim();
				cont++;
			}
		}

		return fields;
	}

	private void cleanInputFields(Object[] input) {
		for (int i = 0; i < input.length; i++) {
			if (i % 2 != 0) {
				JTextField aux = (JTextField) input[i];
				aux.setText("");
			}
		}
	}

}

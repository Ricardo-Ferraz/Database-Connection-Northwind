package br.com.unicap.bd2.view;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.unicap.bd2.controller.ControllerCustomer;
import br.com.unicap.bd2.util.ShowMessage;

public class CustomerView {

	private ControllerCustomer controller = new ControllerCustomer();
	private JTextField customerId = new JTextField(5);
	private JTextField companyName = new JTextField(40);
	private JTextField contactName = new JTextField(30);
	private JTextField contactTitle = new JTextField(30);
	private JTextField adress = new JTextField(60);
	private JTextField city = new JTextField(15);
	private JTextField region = new JTextField(15);
	private JTextField postalCode = new JTextField(10);
	private JTextField country = new JTextField(15);
	private JTextField phone = new JTextField(24);
	private JTextField fax = new JTextField(24);

	private Object[] input = { "CustomerId: ", this.customerId, "CompanyName: ", this.companyName, "ContactName: ",
			this.contactName, "ContactTitle: ", this.contactTitle, "Adress: ", this.adress, "City: ", this.city,
			"Region: ", this.region, "PostalCode: ", this.postalCode, "Country: ", this.country, "Phone: ", this.phone,
			"Fax: ", this.fax };

	public void read() {
		try {
			String aux = controller.read();
			ShowMessage.showScrollMessage(aux, "Customers");
		} catch (SQLException e) {
			ShowMessage.showErrorMessage(e.getMessage());
		}
	}

	public void readOne() {
		try {
			int opcao = JOptionPane.showConfirmDialog(null, this.customerId, "Informe o CustomerId",
					JOptionPane.OK_CANCEL_OPTION);
			if (opcao == JOptionPane.OK_OPTION) {
				String aux = this.controller.readOne(this.customerId.getText());
				ShowMessage.showInfomationMessage(aux, "Customer encontrado");
			}
		} catch (SQLException e) {
			ShowMessage.showErrorMessage(e.getMessage());
		} catch (Exception e) {
			ShowMessage.showErrorMessage(e.getMessage());
		} finally {
			this.customerId.setText("");
		}
	}

	public void create() {
		try {
			int opcao = JOptionPane.showConfirmDialog(null, this.input, "Informe os dados",
					JOptionPane.OK_CANCEL_OPTION);
			if (opcao == JOptionPane.OK_OPTION) {
				String[] inputs = this.inputFields();
				this.cleanEmptyFields(inputs);
				int rows = this.controller.create(inputs);
				ShowMessage.showInfomationMessage("Linhas afetadas: " + rows, "Sucesso!");
			}
		} catch (SQLException e) {
			ShowMessage.showErrorMessage(e.getMessage());
		} finally {
			this.cleanInputFields();
		}
	}

	public void delete() {
		try {

			int opcao = JOptionPane.showConfirmDialog(null, this.customerId, "Informe o CustomerId",
					JOptionPane.OK_CANCEL_OPTION);
			if (opcao == JOptionPane.OK_OPTION) {
				int rows= this.controller.delete(this.customerId.getText());
				ShowMessage.showInfomationMessage("Linhas afetadas: " + rows, "Sucesso!");
			}

		} catch (SQLException e) {
			ShowMessage.showErrorMessage(e.getMessage());
		} finally {
			this.customerId.setText("");
		}
	}
	
	public void update() {
		try {
			
			int opcao = JOptionPane.showConfirmDialog(null, this.customerId, "Informe o CustomerId para atualização",
					JOptionPane.OK_CANCEL_OPTION);
			if (opcao == JOptionPane.OK_OPTION) {
				this.controller.readOne(this.customerId.getText());
				String aux= this.customerId.getText();
				this.customerId.setText("");
				opcao = JOptionPane.showConfirmDialog(null, this.input, "Informe os dados para Atualizar",
						JOptionPane.OK_CANCEL_OPTION);
				if (opcao == JOptionPane.OK_OPTION) {
					String[] inputs = this.inputFields();
					this.cleanEmptyFields(inputs);
					int rows = this.controller.update(inputs, aux);
					ShowMessage.showInfomationMessage("Linhas afetadas: " + rows, "Sucesso!");
				}
			}
			
		}catch(SQLException e) {
			ShowMessage.showErrorMessage(e.getMessage());
		}catch(Exception e) {
			ShowMessage.showErrorMessage(e.getMessage());
		}finally {
			this.cleanInputFields();
		}
	}

	private String[] inputFields() {
		String[] fields = new String[this.input.length / 2];
		int cont = 0;

		for (int i = 0; i < this.input.length; i++) {
			if ((i % 2 != 0)) {
				JTextField aux = (JTextField) this.input[i];
				fields[cont] = aux.getText().trim();
				cont++;
			}
		}

		return fields;
	}

	private void cleanInputFields() {
		for (int i = 0; i < this.input.length; i++) {
			if (i % 2 != 0) {
				JTextField aux = (JTextField) this.input[i];
				aux.setText("");
			}
		}
	}

	private void cleanEmptyFields(String[] inputs) {
		for (int i = 0; i < inputs.length; i++) {
			if (inputs[i].trim().isEmpty()) {
				inputs[i] = null;
			}
		}
	}

}

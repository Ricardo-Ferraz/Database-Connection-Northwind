package br.com.unicap.bd2.view;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.unicap.bd2.controller.ControllerOrder;
import br.com.unicap.bd2.util.ShowMessage;

public class OrderView {

	private ControllerOrder controller = new ControllerOrder();
	private JTextField orderId = new JTextField(11);
	private JTextField customerId = new JTextField(5);
	private JTextField employeeId = new JTextField(11);
	private JTextField orderDate = new JTextField(60);
	private JTextField requiredDate = new JTextField(60);
	private JTextField shippedDate = new JTextField(60);
	private JTextField shipVia = new JTextField(11);
	private JTextField freight = new JTextField(11);
	private JTextField shipName = new JTextField(40);
	private JTextField shipAddress = new JTextField(60);
	private JTextField shipCity = new JTextField(15);
	private JTextField shipRegion = new JTextField(15);
	private JTextField shipPostalCode = new JTextField(10);
	private JTextField shipCountry = new JTextField(15);

	private Object[] input = { "OrderID: ", this.orderId, "CustomerID: ", this.customerId, "EmployeeID: ",
			this.employeeId, "OrderDate: ", this.orderDate, "RequiredDate: ", this.requiredDate, "ShippedDate: ", this.shippedDate,
			"ShipVia: ", this.shipVia, "Freight: ", this.freight, "ShipName: ", this.shipName, "ShippedAddress: ", this.shipAddress,
			"ShipCity: ", this.shipCity, "ShipRegion: ", this.shipRegion, "ShipPostalCode: ", this.shipPostalCode, "ShipCountry: ", this.shipCountry };

	public void read() {
		try {
			String aux = controller.read();
			ShowMessage.showScrollMessage(aux, "Orders");
		} catch (SQLException e) {
			ShowMessage.showErrorMessage(e.getMessage());
		}
	}

	public void readOne() {
		try {
			int opcao = JOptionPane.showConfirmDialog(null, this.orderId, "Informe o OrderID",
					JOptionPane.OK_CANCEL_OPTION);
			if (opcao == JOptionPane.OK_OPTION) {
				String aux = this.controller.readOne(this.orderId.getText());
				ShowMessage.showInfomationMessage(aux, "Order encontrado");
			}
		} catch (SQLException e) {
			ShowMessage.showErrorMessage(e.getMessage());
		} catch (Exception e) {
			ShowMessage.showErrorMessage(e.getMessage());
		} finally {
			this.orderId.setText("");
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
/*
	public void delete() {
		try {

			int opcao = JOptionPane.showConfirmDialog(null, this.orderId, "Informe o CustomerId",
					JOptionPane.OK_CANCEL_OPTION);
			if (opcao == JOptionPane.OK_OPTION) {
				int rows= this.controller.delete(this.orderId.getText());
				ShowMessage.showInfomationMessage("Linhas afetadas: " + rows, "Sucesso!");
			}

		} catch (SQLException e) {
			ShowMessage.showErrorMessage(e.getMessage());
		} finally {
			this.orderId.setText("");
		}
	}
	
	public void update() {
		try {
			
			int opcao = JOptionPane.showConfirmDialog(null, this.orderId, "Informe o CustomerId para atualização",
					JOptionPane.OK_CANCEL_OPTION);
			if (opcao == JOptionPane.OK_OPTION) {
				this.controller.readOne(this.orderId.getText());
				String aux= this.orderId.getText();
				this.orderId.setText("");
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
*/
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

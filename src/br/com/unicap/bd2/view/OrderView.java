package br.com.unicap.bd2.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	private JTextField productId = new JTextField(15);
	private JTextField unitPrice = new JTextField(15);
	private JTextField quantity = new JTextField(15);
	private JTextField discount = new JTextField(15);

	private Object[] input = { "CustomerID: ", this.customerId, "EmployeeID: ", this.employeeId, "OrderDate: ",
			this.orderDate, "RequiredDate: ", this.requiredDate, "ShippedDate: ", this.shippedDate, "ShipVia: ",
			this.shipVia, "Freight: ", this.freight, "ShipName: ", this.shipName, "ShippedAddress: ", this.shipAddress,
			"ShipCity: ", this.shipCity, "ShipRegion: ", this.shipRegion, "ShipPostalCode: ", this.shipPostalCode,
			"ShipCountry: ", this.shipCountry };

	private Object[] inputDetails = { "ProductID: ", this.productId, "UnitPrice: ", this.unitPrice, "Quantity: ",
			this.quantity, "Discount: ", this.discount };

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
		String query = "";
		int rows = 0;
		try {
			int opcao = JOptionPane.showConfirmDialog(null, this.input, "Informe os dados",
					JOptionPane.OK_CANCEL_OPTION);
			if (opcao == JOptionPane.OK_OPTION) {
				String[] inputs = this.inputFields(this.input);
				this.cleanEmptyFields(inputs);
				String newOrderId = this.controller.create(inputs);
				ShowMessage.showInfomationMessage("OrderId gerado: " + newOrderId, "Sucesso!");
				int opcao2;
				query = "INSERT INTO [Order Details](OrderID, ProductID, UnitPrice, Quantity, Discount) VALUES ";
				do {
					this.cleanInputFields(this.inputDetails);
					opcao = JOptionPane.showConfirmDialog(null, this.inputDetails, "Informe os dados da Compra",
							JOptionPane.OK_CANCEL_OPTION);
					if (opcao == JOptionPane.OK_OPTION) {
						query += "(" + newOrderId + ",";
						String[] inputsDetails = this.inputFields(this.inputDetails);
						for (int i = 0; i < inputsDetails.length; i++) {
							if (i == inputsDetails.length - 1) {
								query += inputsDetails[i];
							} else {
								query += inputsDetails[i] + ",";
							}
						}
						query += ")";
					}

					opcao2 = JOptionPane.showConfirmDialog(null, "Deseja incluir mais compras?", "Escolha",
							JOptionPane.YES_NO_OPTION);
					if (opcao2 == JOptionPane.YES_OPTION) {
						query += ",";
					}

				} while (opcao2 == JOptionPane.YES_OPTION);
				rows = this.controller.createDetails(query);
				ShowMessage.showInfomationMessage("Quantidade de linhas afetadas: " + rows, "Sucesso!");
			}

		} catch (SQLException e) {
			ShowMessage.showErrorMessage(e.getMessage());
		} finally {
			this.cleanInputFields(this.inputDetails);
			this.cleanInputFields(this.input);
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

	private void cleanEmptyFields(String[] inputs) {
		for (int i = 0; i < inputs.length; i++) {
			if (inputs[i].trim().isEmpty()) {
				inputs[i] = null;
			}
		}
	}

}

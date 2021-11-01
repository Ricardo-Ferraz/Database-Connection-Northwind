package br.com.unicap.bd2.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 8921567679971454435L;
	
	private MainWindowView acess;
	
	private JButton b1;
	private JButton b2;
	
	
	public MainWindow() {
		this.acess= new MainWindowView();
		
		this.b1= new JButton("Customers");
		this.b2= new JButton("Consulta");
		
		super.setLayout(null);
		this.b1.setBounds(100, 30, 100, 50);
		this.b1.addActionListener(this);
		this.b2.setBounds(300, 30, 100, 50);
		this.b2.addActionListener(this);
		
		super.getContentPane().add(this.b1);
		super.getContentPane().add(this.b2);
		
		super.setSize(500, 500);
		super.setTitle("Teste");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.b1) { //Chama a janela de Customers
			new CustomerWindow();
		}
		else if(e.getSource() == this.b2) { //Chama a janela de qualquer consulta
			this.acess.query();
		}
	}
}

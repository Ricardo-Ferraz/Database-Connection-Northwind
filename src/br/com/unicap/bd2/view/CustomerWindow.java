package br.com.unicap.bd2.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CustomerWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1454904928302545016L;
	
	private CustomerView acess = new CustomerView();
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	
	public CustomerWindow() {
		this.b1= new JButton("Lê todos");
		this.b2= new JButton("Lê CustomerID");
		this.b3= new JButton("Inserir");
		this.b4= new JButton("Remover");
		this.setLayout(null);
		
		this.b1.setBounds(100, 30, 100, 50);
		this.b1.addActionListener(this);
		
		this.b2.setBounds(300, 30, 150, 50);
		this.b2.addActionListener(this);
		
		this.b3.setBounds(100, 100, 100, 50);
		this.b3.addActionListener(this);
		
		this.b4.setBounds(300, 100, 100, 50);
		this.b4.addActionListener(this);
		
		this.getContentPane().add(this.b1);
        this.getContentPane().add(this.b2);
        this.getContentPane().add(this.b3);
		this.getContentPane().add(this.b4);
	
        
		this.setSize(500,500);
        this.setTitle("Teste Customers");
        this.setVisible(true);
        this.setResizable(false);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.b1) { //Lê todos
			this.acess.read();
		}
		else if(e.getSource() == this.b2) { //Lê por CustomerID
			this.acess.readOne();
		}
		else if(e.getSource() == this.b3) { //Insere um customer
			this.acess.create();
		}
		else if(e.getSource() == this.b4) { //Remove um Customer
			
		}
	}

}

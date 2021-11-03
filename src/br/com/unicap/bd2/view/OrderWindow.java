package br.com.unicap.bd2.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class OrderWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1454904928302545016L;
	
	private OrderView acess = new OrderView();
	private JButton b1;
	private JButton b2;
	private JButton b3;
	
	public OrderWindow() {
		this.b1= new JButton("Ver todos");
		this.b2= new JButton("Buscar OrderID");
		this.b3= new JButton("Adicionar");
		super.setLayout(null);
		
		this.b1.setBounds(100, 30, 100, 50);
		this.b1.addActionListener(this);
		
		this.b2.setBounds(300, 30, 150, 50);
		this.b2.addActionListener(this);
		
		this.b3.setBounds(100, 100, 100, 50);
		this.b3.addActionListener(this);
				
		super.getContentPane().add(this.b1);
		super.getContentPane().add(this.b2);
		super.getContentPane().add(this.b3);
        
		super.setSize(500,500);
		super.setTitle("Teste Orders");
		super.setVisible(true);
		super.setResizable(false);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.b1) { //Lê todos (Lista Orders)
			this.acess.read();
		}
		else if(e.getSource() == this.b2) { //Lê por OrderID (Lista Order específica com todos os Order Details)
			this.acess.readOne();
		}
		else if(e.getSource() == this.b3) { //Abre a pagina de criação de order + inserção de Order Details nessa nova Order
			this.acess.create();
		}
	}

}

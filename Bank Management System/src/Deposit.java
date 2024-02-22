package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.*;

public class Deposit extends JFrame implements ActionListener{
	
	JTextField amount;
	JButton deposit,back;
	String cardNumber;
	
	Deposit(String cardNumber){
		
		this.cardNumber = cardNumber;
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(0,0,900,900);
		add(image);
		
		JLabel text = new JLabel("Enter the amount to deposit");
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Raleway",Font.BOLD,16));
		text.setBounds(170,300,400,20);
		image.add(text);
		
		amount = new JTextField();
		amount.setFont(new Font("Raleway",Font.BOLD,22));
		amount.setBounds(170,350,320,25);
		image.add(amount);
		
		deposit = new JButton("Deposit");
		deposit.setBounds(355,455,150,30);
		deposit.addActionListener(this);
		image.add(deposit);
		
		back = new JButton("Back");
		back.setBounds(355,495,150,30);
		back.addActionListener(this);
		image.add(back);
		
		setSize(900,900);
		setLocation(300,0);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Deposit("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==deposit) {
			String money = amount.getText();
			Date date = new Date();
			if(money.equals("")) {
				JOptionPane.showMessageDialog(null, "Please enter the amonut");
			}else {
				Conn c = new Conn();
				
				try {
					String query1 = "Select amount from bank where card ="+cardNumber;
					ResultSet rs = c.s.executeQuery(query1);
					if(rs.next()) {
						int nMoney = Integer.parseInt(rs.getString(1)) + Integer.parseInt(money);
						String n_Money = String.valueOf(nMoney);
						String query2 = "UPDATE bank SET amount = '" + n_Money + "' WHERE card = " + cardNumber;
						c.s.executeUpdate(query2);
						JOptionPane.showMessageDialog(null, "Rs "+money+" Deposited Successfully");
						String query3 = "insert into operations values('"+cardNumber+"','"+date+"','Deposit','"+money+"')";
						c.s.executeUpdate(query3);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		}else if(e.getSource()==back) {
			setVisible(false);
			new Transactions(cardNumber);
		}
		
	}

}

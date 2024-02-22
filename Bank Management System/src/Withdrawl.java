package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Withdrawl extends JFrame implements ActionListener{
	
	JTextField amount;
	JButton withdrawl,back;
	String cardNumber;
	
	Withdrawl(String cardNumber){
		
		this.cardNumber = cardNumber;
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(0,0,900,900);
		add(image);
		
		JLabel text = new JLabel("Enter the amount to withdrawl");
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Raleway",Font.BOLD,16));
		text.setBounds(170,300,400,20);
		image.add(text);
		
		amount = new JTextField();
		amount.setFont(new Font("Raleway",Font.BOLD,22));
		amount.setBounds(170,350,320,25);
		image.add(amount);
		
		withdrawl = new JButton("WithDrawl");
		withdrawl.setBounds(355,455,150,30);
		withdrawl.addActionListener(this);
		image.add(withdrawl);
		
		back = new JButton("Back");
		back.setBounds(355,495,150,30);
		back.addActionListener(this);
		image.add(back);
		
		setSize(900,900);
		setLocation(300,0);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Withdrawl("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==withdrawl) {
			String money = amount.getText();
			Date date = new Date();
			if(money.equals("")) {
				JOptionPane.showMessageDialog(null, "Please enter the amonut");
			}else {
				Conn c = new Conn();
				
				try {
					String query1 = "select amount from bank where card ="+cardNumber;
					ResultSet rs = c.s.executeQuery(query1);
					if(rs.next()) {
						int nMoney = Integer.parseInt(rs.getString(1));
					    if(nMoney < Integer.parseInt(money)) {
					    	JOptionPane.showMessageDialog(null, "Enter amount less than your balance");
					    }else {
					    	nMoney -= Integer.parseInt(money);  
					    	String n_Money = String.valueOf(nMoney);
							String query2 = "update bank set amount ='"+n_Money+"' where card ="+cardNumber;
							c.s.executeUpdate(query2);
							JOptionPane.showMessageDialog(null, "Rs "+money+" Withdrawed Successfully");
							String query3 = "insert into operations values('"+cardNumber+"','"+date+"','Withdraw','"+money+"')";
							c.s.executeUpdate(query3);
					    }
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

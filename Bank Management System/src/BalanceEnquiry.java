package bank.management.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BalanceEnquiry extends JFrame implements ActionListener{

	String cardnumber;
	JButton back;
	BalanceEnquiry(String cardnumber){
		setLayout(null);
		this.cardnumber = cardnumber;
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(0,0,900,900);
		add(image);
		
		try {
			Conn c = new Conn();
			String query = "select amount from bank where card = "+cardnumber;
			ResultSet rs = c.s.executeQuery(query);
			 if (rs.next()) { 
			        JLabel text = new JLabel("Your Current Balance is Rs " + rs.getString("amount"));
			        text.setForeground(Color.WHITE);
			        text.setBounds(170, 300, 400, 30);
			        image.add(text);
			 } 
		}catch(Exception e) {
			System.out.println(e);
		}
		
		back = new JButton("Back");
		back.setBounds(355,520,150,30);
		back.addActionListener(this);
		image.add(back);
		
		setSize(900,900);
		setLocation(300,0);
		//setUndecorated(true);
		setVisible(true);
	}
	public static void main(String[] args) {
		new BalanceEnquiry("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		new Transactions(cardnumber);
		
	}

}

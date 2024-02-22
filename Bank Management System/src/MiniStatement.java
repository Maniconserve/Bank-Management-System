package bank.management.system;
import java.awt.Image;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MiniStatement extends JFrame{

	String cardnumber;
	MiniStatement(String cardnumber){
		setLayout(null);
		this.cardnumber = cardnumber;
		
		JLabel mini  = new JLabel();
		mini.setBounds(20,140,400,200);
		add(mini);
		
		JLabel bank = new JLabel("Indian Bank");
		bank.setBounds(150,20,100,20);
		add(bank);
		
		JLabel card = new JLabel();
		card.setBounds(20,80,300,20);
		add(card);
		
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from operations where card ='"+cardnumber+"'");
			card.setText("card number: "+cardnumber.substring(0,4)+"XXXXXXXX"+cardnumber.substring(12));
			while(rs.next()) {
				mini.setText(mini.getText()+ "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount")+"<br><br><html>");
			}
		}catch(Exception exc) {
			System.out.println(exc);
		}
		
		setSize(400,600);
		setLocation(20,20);
		//setUndecorated(true);
		setVisible(true);
	}
	public static void main(String[] args) {
		new MiniStatement("");
	}

}

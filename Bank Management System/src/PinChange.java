package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PinChange extends JFrame implements ActionListener{
	String cardnumber;
	JButton change,back;
	JPasswordField pin,repin;
	PinChange(String cardnumber){
		setLayout(null);
		this.cardnumber = cardnumber;
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(0,0,900,900);
		add(image);
		
		JLabel text = new JLabel("CHANGE YOUR PIN");
		text.setFont(new Font("System",Font.BOLD,16));
		text.setForeground(Color.WHITE);
		text.setBounds(250,280,500,35);
		image.add(text);
		
		JLabel pintext = new JLabel("NEW PIN");
		pintext.setFont(new Font("System",Font.BOLD,16));
		pintext.setForeground(Color.WHITE);
		pintext.setBounds(165,320,180,25);
		image.add(pintext);
		
		pin = new JPasswordField();
		pin.setFont(new Font("System",Font.BOLD,25));
		pin.setBounds(330,320,180,25);
		image.add(pin);
		
		JLabel repintext = new JLabel("Re Enter NEW PIN");
		repintext.setFont(new Font("System",Font.BOLD,16));
		repintext.setForeground(Color.WHITE);
		repintext.setBounds(165,360,180,25);
		image.add(repintext);
		
		repin = new JPasswordField();
		repin.setFont(new Font("System",Font.BOLD,25));
		repin.setBounds(330,360,180,25);
		image.add(repin);
		
		change = new JButton("CHANGE");
		change.setBounds(355,485,150,30);
		change.addActionListener(this);
		image.add(change);
		
		back = new JButton("BACK");
		back.setBounds(355,520,150,30);
		back.addActionListener(this);
		image.add(back);
		
		setSize(900,900);
		setLocation(300,0);
		//setUndecorated(true);
		setVisible(true);
	}
	public static void main(String[] args) {
		new PinChange("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==change) {
			String npin = pin.getText();
			String rpin = repin.getText();
			if(npin.length() < 4 || npin.length() > 4 || rpin.length() > 4 || rpin.length() < 4) {
				JOptionPane.showMessageDialog(null, "Enter 4 digit pin");
				return;
			}
			if(npin.equals(rpin)) {
				Conn c = new Conn();
				try{
					String query = "update login set pinnumber ='"+npin+"' where cardnumer ="+cardnumber;
					c.s.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Pin Changed Successfully");
					
				}catch(Exception exc) {
					System.out.println(exc);
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "Pins entered are not equal");
			}
			
		}else {
			setVisible(false);
			new Transactions(cardnumber);
		}
	}
}

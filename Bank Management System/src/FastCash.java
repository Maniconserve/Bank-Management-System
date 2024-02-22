package bank.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.*;

public class FastCash extends JFrame implements ActionListener{
	
	JButton hundred,fivehun,thousand,twothou,fivethou,tenthou,back;
	String cardnumber;
	FastCash(String cardnumber){
		setLayout(null);
		this.cardnumber = cardnumber;
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(0,0,900,900);
		add(image);
		
		JLabel text = new JLabel("Select Withdrawl Amount");
		text.setBounds(210,300,700,35);
		text.setForeground(Color.WHITE);
		text.setFont(new Font("System",Font.BOLD,16));
		image.add(text);
		
		hundred = new JButton("Rs 100");
		hundred.setBounds(170,415,150,30);
		hundred.addActionListener(this);
		image.add(hundred);
		
		fivehun = new JButton("Rs 500");
		fivehun.setBounds(355,415,150,30);
		fivehun.addActionListener(this);
		image.add(fivehun);
		
		thousand = new JButton("Rs 1000");
		thousand.setBounds(170,450,150,30);
		thousand.addActionListener(this);
		image.add(thousand);
		
		twothou = new JButton("Rs 2000");
		twothou.setBounds(355,450,150,30);
		twothou.addActionListener(this);
		image.add(twothou);
		
		fivethou = new JButton("Rs 5000");
		fivethou.setBounds(170,485,150,30);
		fivethou.addActionListener(this);
		image.add(fivethou);
		
		tenthou = new JButton("Rs 10000");
		tenthou.setBounds(355,485,150,30);
		tenthou.addActionListener(this);
		image.add(tenthou);
		
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
		new FastCash("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back) {
			setVisible(false);
			new Transactions(cardnumber);
		}else {
				String amount = ((JButton)e.getSource()).getText().substring(3);
				Conn c = new Conn();
				
				try {
					String query1 = "Select amount from bank where card ="+cardnumber;
					ResultSet rs = c.s.executeQuery(query1);
					Date date  = new Date();
					if(rs.next()) {
						int nMoney = Integer.parseInt(rs.getString(1));
					    if(nMoney < Integer.parseInt(amount)) {
					    	JOptionPane.showMessageDialog(null, "Enter amount less than your balance");
					    }else {
					    	nMoney -= Integer.parseInt(amount);  
					    	String n_Money = String.valueOf(nMoney);
							String query2 = "update bank set amount ='"+n_Money+"' where card ="+cardnumber;
							c.s.executeUpdate(query2);
							JOptionPane.showMessageDialog(null, "Rs "+amount+" Withdrawed Successfully");
							String query3 = "insert into operations values('"+cardnumber+"','"+date+"','Withdraw','"+amount+"')";
							c.s.executeUpdate(query3);
					    }
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		
	}

}

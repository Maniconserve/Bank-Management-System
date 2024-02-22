package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class SignupTwo extends JFrame implements ActionListener{

	JTextField panTextField,aadTextField;
	JRadioButton y,n,ye,no;
	JComboBox religion,category,income,educational,occupation;
	JButton next;
	String formno;
	SignupTwo(String formno){
		this.formno = formno;
		
		setLayout(null);
		 
		setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");
		
		JLabel additionalDetails = new JLabel("Page 2: Additional Details");
		additionalDetails.setFont(new Font("Raleway",Font.BOLD, 22));		
		additionalDetails.setBounds(290,80,300,30);
		add(additionalDetails);
		
		JLabel rel = new JLabel("Religion");
		rel.setFont(new Font("Raleway",Font.BOLD, 20));		
		rel.setBounds(100,140,100,30);
		add(rel);
		
		String[] valReligion = {"N/A","Hindu","Muslim","Sikh","Christian","Other"};
		religion = new JComboBox(valReligion);
		religion.setBounds(300,140, 400,30);
		add(religion);
		
		JLabel cat = new JLabel("Category");
		cat.setFont(new Font("Raleway",Font.BOLD, 20));		
		cat.setBounds(100,190,200,30);
		add(cat);
		
		String[] valCategory = {"N/A","Gen","OBC","SC","ST","Other"};
		category = new JComboBox(valCategory);
		category.setBounds(300,190, 400,30);
		add(category);
		
		
		JLabel inc = new JLabel("Income");
		inc.setFont(new Font("Raleway",Font.BOLD, 20));		
		inc.setBounds(100,240,200,30);
		add(inc);
		
		String[] valIncome = {"N/A","< 1,50,000","< 2,50,000","< 5,00,000","upto 10,00,000"};
		income = new JComboBox(valIncome);
		income.setBounds(300,240, 400,30);
		add(income);
		
		JLabel edu = new JLabel("Educational");
		edu.setFont(new Font("Raleway",Font.BOLD, 20));		
		edu.setBounds(100,290,200,30);
		add(edu);
		
		JLabel qua = new JLabel("Qualification");
		qua.setFont(new Font("Raleway",Font.BOLD, 20));		
		qua.setBounds(100,315,200,30);
		add(qua);
		
		String[] valEducational   = {"N/A","Non Graduate","Graduate","Pos Graduation","Doctrate","Others"};
		educational = new JComboBox(valEducational);
		educational.setBounds(300,315, 400,30);
		add(educational);
		
		JLabel occ = new JLabel("Occupation");
		occ.setFont(new Font("Raleway",Font.BOLD, 20));		
		occ.setBounds(100,390,200,30);
		add(occ);
		
		String[] valOccupation   = {"N/A","Salaried","Self-Employed","Bussiness","Student","Others"};
		occupation = new JComboBox(valOccupation);
		occupation.setBounds(300,390, 400,30);
		add(occupation);
		
		JLabel pan = new JLabel("Pan Number");
		pan.setFont(new Font("Raleway",Font.BOLD, 20));		
		pan.setBounds(100,440,200,30);
		add(pan);
		
		panTextField = new JTextField();
		panTextField.setFont(new Font("Raleway",Font.BOLD,14));
		panTextField.setBounds(300,440, 400,30);
		add(panTextField);
		
		JLabel aad = new JLabel("Aadhaar Number");
		aad.setFont(new Font("Raleway",Font.BOLD, 20));		
		aad.setBounds(100,490,200,30);
		add(aad);
		
		aadTextField = new JTextField();
		aadTextField.setFont(new Font("Raleway",Font.BOLD,14));
		aadTextField.setBounds(300,490, 400,30);
		add(aadTextField);
		
		JLabel sen = new JLabel("Senior Citizen");
		sen.setFont(new Font("Raleway",Font.BOLD, 20));		
		sen.setBounds(100,540,200,30);
		add(sen);
		
		y = new JRadioButton("Yes");
		y.setBounds(300,540,100,30);
		add(y);
		n = new JRadioButton("No");
		n.setBounds(500,540,100,30);
		add(n);
		
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(y);
		bg1.add(n);
		
		JLabel exi = new JLabel("Existing Account");
		exi.setFont(new Font("Raleway",Font.BOLD, 20));		
		exi.setBounds(100,590,200,30);
		add(exi);
		
		ye = new JRadioButton("Yes");
		ye.setBounds(300,590,100,30);
		add(ye);
		no = new JRadioButton("No");
		no.setBounds(500,590,100,30);
		add(no);
		
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(ye);
		bg2.add(no);
		
		next = new JButton("Next");
		next.setForeground(Color.WHITE);
		next.setBackground(Color.BLACK);
		next.setFont(new Font("Raleway",Font.BOLD,14));
		next.setBounds(620,660,80,30); 
		next.addActionListener(this);
		add(next);
		
		setSize(850,800);
		setLocation(350,10);
		setVisible(true);
		
		 
	}

	public static void main(String[] args) {
		 new SignupTwo("");
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		String srel = (String) religion.getSelectedItem();
		String scat = (String) category.getSelectedItem();
		String sinc = (String) income.getSelectedItem();
		String sedu = (String) educational.getSelectedItem();
		String socc = (String) occupation.getSelectedItem();
		String span = panTextField.getText(); 
		String saad = aadTextField.getText();
		String ssen = "";
		if(y.isSelected()) {
			ssen = "Yes";
		}else if(n.isSelected()) {
			ssen  ="No";
		}
		String s_exist = "";
		if(ye.isSelected()) {
			s_exist = "Yes";
		}else if(no.isSelected()) {
			s_exist  ="No";
		}
		
		try {
			
			if(srel.equals("")) {
				JOptionPane.showMessageDialog(null, "Religion is Required");
			}
			if(scat.equals("")) {
				JOptionPane.showMessageDialog(null, "Category Name is Required");
			}
			if(sinc.equals("")) {
				JOptionPane.showMessageDialog(null, "Income is Required");
			}
			if(sedu.equals("")) {
				JOptionPane.showMessageDialog(null, "Education is Required");
			}
			if(socc.equals("")) {
				JOptionPane.showMessageDialog(null, "Occupation is Required");
			}
			if(span.equals("")) {
				JOptionPane.showMessageDialog(null, "Pan Number is Required");
			}
			if(saad.equals("")) {
				JOptionPane.showMessageDialog(null, "Aadhaar Number is Required");
			}else {
				String no = String.valueOf(formno);
				Conn c = new Conn();
				String query = "insert into signuptwo values('" + formno + "','" + srel + "','" + scat + "','" + sinc + "','" + sedu + "','" + socc + "','" + span + "','" + saad + "','" + ssen + "','" + s_exist + "')";
				c.s.executeUpdate(query);
				setVisible(false);
				new SignupThree(formno);
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}

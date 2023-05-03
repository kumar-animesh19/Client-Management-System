import java.awt.*;
import javax.swing.*;

public class ContactUs extends JFrame
{
	JPanel p;
	JLabel lcontactus,lcall,lnum,lmail,lid;
	
	ContactUs()
	{
		setTitle("CLIENT MANAGEMENT SYSTEM");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		lcontactus=new JLabel("Contact");
		lcontactus.setBounds(30, 40, 200, 30);
		lcontactus.setFont(new Font("Times New Roman",Font.BOLD,25));
		lcontactus.setForeground(Color.DARK_GRAY);
		add(lcontactus);
		
		p=new JPanel();
		p.setLayout(null);
		p.setBounds(30, 70, 320, 260);
		p.setBackground(new Color(255,180,105));
		add(p);
		
		lcall=new JLabel("Call Us :-");
		lcall.setBounds(30, 30, 50, 20);
		lcall.setForeground(Color.RED);
		p.add(lcall);
		
		lnum=new JLabel("=> 9155840228  (Kr. Animesh)");
		lnum.setBounds(70, 50, 200, 20);
		lnum.setForeground(Color.BLUE);
		p.add(lnum);
		
		
		lmail=new JLabel("Mail Us :-");
		lmail.setBounds(30, 130, 100, 20);
		lmail.setForeground(Color.RED);
		p.add(lmail);
		
		lid=new JLabel("=> kumaranimesh1905@gmail.com");
		lid.setBounds(70, 150, 210, 20);
		lid.setForeground(Color.BLUE);
		p.add(lid);
		
		setVisible(true);
	}
}

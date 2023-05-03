import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Main extends JFrame implements ActionListener
{
	JLabel lhead,lcd,lpd,lmd,lpayd;
	JButton bcd,bpd,bmd,bpayd;
	JMenuBar mb;
	JMenu muser,mreport,mhelp,mlogout;
	JMenuItem miadduser,miremoveuser,michangepass,miproreport,mimaintreport,mipayreport,micontact,milogout;
	String username;
	
	Main(String name, String username)
	{
		this.username=username;
		setTitle("CLIENT MANAGEMENT SYSTEM");
		setSize(700, 600);
		getContentPane().setBackground(Color.WHITE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		mb=new JMenuBar();
		mb.setBounds(0, 0, 685, 25);
		add(mb);
		
		muser=new JMenu("User");
		mb.add(muser);
		miadduser=new JMenuItem("Add User");
	    muser.add(miadduser);
	    miremoveuser=new JMenuItem("Remove User");
	    muser.add(miremoveuser);
	    michangepass=new JMenuItem("Change Password");
	    muser.add(michangepass);
	    
	    mreport=new JMenu("Report");
		mb.add(mreport);
		miproreport=new JMenuItem("Project Report");
	    mreport.add(miproreport);
	    mimaintreport=new JMenuItem("Maintenance Report");
	    mreport.add(mimaintreport);
	    mipayreport=new JMenuItem("Payment Report");
	    mreport.add(mipayreport);
	
	    mhelp=new JMenu("Help");
		mb.add(mhelp);
		micontact=new JMenuItem("Contact Us");
		mhelp.add(micontact);
	    
		mb.add(Box.createHorizontalGlue());
		mlogout=new JMenu(name);
		mb.add(mlogout);
		milogout=new JMenuItem("Logout");
		mlogout.add(milogout);
		
		lhead=new JLabel(new ImageIcon(Main.class.getResource("/imgs/Head.jpg")));
		lhead.setBounds(0, 25, 700, 200);
		add(lhead);
		
		bcd=new JButton(new ImageIcon(Main.class.getResource("/imgs/Client.jpg")));
		bcd.setBounds(40, 250, 120, 100);
		add(bcd);
		lcd=new JLabel("CLIENT DETAILS");
		lcd.setBounds(55, 350, 150, 30);
		lcd.setForeground(Color.BLUE);
		add(lcd);
		
		bpd=new JButton(new ImageIcon(Main.class.getResource("/imgs/Project.jpg")));
		bpd.setBounds(205, 250, 120, 100);
		add(bpd);
		lpd=new JLabel("PROJECT DETAILS");
		lpd.setBounds(215, 350, 150, 30);
		lpd.setForeground(Color.BLUE);
		add(lpd);
		
		bmd=new JButton(new ImageIcon(Main.class.getResource("/imgs/Maintenance.jpg")));
		bmd.setBounds(370, 250, 120, 100);
		add(bmd);
		lmd=new JLabel("MAINTENANCE DETAILS");
		lmd.setBounds(365, 350, 150, 30);
		lmd.setForeground(Color.BLUE);
		add(lmd);
		
		bpayd=new JButton(new ImageIcon(Main.class.getResource("/imgs/Payment.jpg")));
		bpayd.setBounds(530, 250, 120, 100);
		add(bpayd);
		lpayd=new JLabel("PAYMENT DETAILS");
		lpayd.setBounds(540, 350, 150, 30);
		lpayd.setForeground(Color.BLUE);
		add(lpayd);
		
		miadduser.addActionListener(this);
		miremoveuser.addActionListener(this);
		michangepass.addActionListener(this);
		miproreport.addActionListener(this);
		mimaintreport.addActionListener(this);
		mipayreport.addActionListener(this);
		micontact.addActionListener(this);
		milogout.addActionListener(this);
		bcd.addActionListener(this);
		bpd.addActionListener(this);
		bmd.addActionListener(this);
		bpayd.addActionListener(this);
		
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==miadduser)
		{
			new AddUser();
		}
		
		if(ae.getSource()==miremoveuser)
		{
			new RemoveUser();
		}
		
		if(ae.getSource()==michangepass)
		{
			new ChangePassword(username);
		}
		
		if(ae.getSource()==miproreport)
		{
			new ProjectReport();
		}
		
		if(ae.getSource()==mimaintreport)
		{
			new MaintenanceReport();
		}
		
		if(ae.getSource()==mipayreport)
		{
			new PaymentReport();
		}
		
		if(ae.getSource()==micontact)
		{
			new ContactUs();
		}
		
		if(ae.getSource()==milogout)
		{
			int cd=JOptionPane.showConfirmDialog(this, "Are you sure..", "Logout", JOptionPane.YES_NO_OPTION);
			if(cd==0)
			{
				dispose();
			}
		}
		if(ae.getSource()==bcd)
		{
			new Client();
		}
		
		if(ae.getSource()==bpd)
		{
			new Project();
		}
		
		if(ae.getSource()==bmd)
		{
			new Maintenance();
		}
		
		if(ae.getSource()==bpayd)
		{
			new Payment();
		}
	}
} 
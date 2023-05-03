import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class ChangePassword extends JFrame implements ActionListener
{
	JPanel p;
	JLabel lchangepass,lnewpass;
	JTextField tnewpass;
	JButton change,cancel;
	Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String username,new_pass;
    
    ChangePassword(String user)
	{
		try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","project","client");
            System.out.print("Connection established");

        }
        catch(Exception e)
        {
            System.out.print("Not connected");
        }
		
		username=user;
		setTitle("CLIENT MANAGEMENT SYSTEM");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		lchangepass=new JLabel("CHANGE PASSWORD");
		lchangepass.setBounds(30, 40, 220, 30);
		lchangepass.setFont(new Font("Times New Roman",Font.BOLD,25));
		lchangepass.setForeground(Color.BLUE);
		add(lchangepass);
		
		p= new JPanel();
	    p.setLayout(null);
	    p.setBounds(30, 70, 320, 260);
	    p.setBackground(Color.LIGHT_GRAY);
	    add(p);
		
		lnewpass=new JLabel("New Password :");
	    lnewpass.setBounds(40, 75, 100, 30);
		p.add(lnewpass);
		tnewpass=new JTextField();
		tnewpass.setBounds(140,75,150,30);
		p.add(tnewpass);
		
		change=new JButton("CHANGE");
		change.setBounds(60, 190, 100, 30);
		change.setFont(new Font("Arial",Font.BOLD,15));
		p.add(change);
		
		cancel=new JButton("CANCEL");
		cancel.setBounds(180, 190, 100, 30);
		cancel.setFont(new Font("Arial",Font.BOLD,15));
		p.add(cancel);
		
		change.addActionListener(this);
		cancel.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==change)
		{
			new_pass=tnewpass.getText();
			try
			{
				int cd=JOptionPane.showConfirmDialog(this, "Are you sure..", "Change", JOptionPane.YES_NO_OPTION);
				if(cd==0)
				{
					ps=con.prepareStatement("update login set password='"+new_pass+"' where username='"+username+"'");
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this,"Password changed..","Change",JOptionPane.INFORMATION_MESSAGE);
						
					tnewpass.setText("");
				}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"Password could not be changed..","Change",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(ae.getSource()==cancel)
		{
			try 
			{
				con.close();
			} 
			catch (Exception ex)
			{
				
			}
			dispose();
		}
	}
}    
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class Login extends JFrame implements ActionListener
{
	JPanel p;
	JLabel llogin,luser,lpass;
	JTextField tuser;
	JPasswordField tpass;
	JCheckBox showpass;
	JButton login,cancel;
	Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String username,password;
    
	Login()
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
		
		setTitle("CLIENT MANAGEMENT SYSTEM");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		llogin=new JLabel("LOGIN");
		llogin.setBounds(30, 40, 200, 30);
		llogin.setFont(new Font("Times New Roman",Font.BOLD,25));
		llogin.setForeground(Color.BLUE);
		add(llogin);
		
		p= new JPanel();
	    p.setLayout(null);
	    p.setBounds(30, 70, 320, 260);
	    p.setBackground(Color.LIGHT_GRAY);
	    add(p);
	   
	    luser=new JLabel("Username :");
	    luser.setBounds(40, 50, 70, 30);
		p.add(luser);
		tuser=new JTextField();
		tuser.setBounds(120,50,150,30);
		p.add(tuser);
		
		lpass=new JLabel("Password :");
	    lpass.setBounds(40, 100, 70, 30);
		p.add(lpass);
		tpass=new JPasswordField();
		tpass.setBounds(120,100,150,30);
		p.add(tpass);
		
		showpass=new JCheckBox("Show");
		showpass.setBounds(220, 140, 60, 15);
		showpass.setBackground(Color.LIGHT_GRAY);
		p.add(showpass);

		login=new JButton("LOGIN");
		login.setBounds(60, 190, 100, 30);
		login.setFont(new Font("Arial",Font.BOLD,15));
		p.add(login);
		
		cancel=new JButton("CANCEL");
		cancel.setBounds(180, 190, 100, 30);
		cancel.setFont(new Font("Arial",Font.BOLD,15));
		p.add(cancel);
		
		showpass.addActionListener(this);
		login.addActionListener(this);
		cancel.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==showpass)
		{
			if(showpass.isSelected())
			{
				tpass.setEchoChar((char) 0);
			}
			else
			{
				tpass.setEchoChar('*');
			}
		}
		
		if(ae.getSource()==login)
		{
			username=tuser.getText();
			password=String.valueOf(tpass.getPassword());
			try
			{
				ps=con.prepareStatement("select * from login where username='"+username+"' and password='"+password+"'");
				rs=ps.executeQuery();
				if(rs.next())
				{
					tuser.setText("");
					tpass.setText("");
					JOptionPane.showMessageDialog(this, "Login Successful...");
					new Main(rs.getString(1),rs.getString(2));
					try 
					{
						con.close();
					} 
					catch (Exception ex)
					{
						
					}
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Wrong Username or Password...","Login",JOptionPane.ERROR_MESSAGE);						
					
				}
				rs.close();
			}
			catch(Exception ex)
			{
				
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

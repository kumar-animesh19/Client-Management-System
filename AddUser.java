import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class AddUser extends JFrame implements ActionListener
{
	JPanel p;
	JLabel ladduser,lname,luser,lpass;
	JTextField tname,tuser,tpass;
	JButton add,cancel;
	Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String name,username,password;
    
	AddUser()
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
		ladduser=new JLabel("ADD USER");
		ladduser.setBounds(30, 40, 200, 30);
		ladduser.setFont(new Font("Times New Roman",Font.BOLD,25));
		ladduser.setForeground(Color.BLUE);
		add(ladduser);
		
		p= new JPanel();
	    p.setLayout(null);
	    p.setBounds(30, 70, 320, 260);
	    p.setBackground(Color.LIGHT_GRAY);
	    add(p);
	   
	    lname=new JLabel("NAME :");
	    lname.setBounds(40, 40, 50, 30);
		p.add(lname);
		tname=new JTextField();
		tname.setBounds(120,40,150,30);
		p.add(tname);
	    
	    luser=new JLabel("Username :");
	    luser.setBounds(40, 90, 70, 30);
		p.add(luser);
		tuser=new JTextField();
		tuser.setBounds(120,90,150,30);
		p.add(tuser);
		
		lpass=new JLabel("Password :");
	    lpass.setBounds(40, 140, 70, 30);
		p.add(lpass);
		tpass=new JTextField();
		tpass.setBounds(120,140,150,30);
		p.add(tpass);

		add=new JButton("ADD");
		add.setBounds(60, 190, 100, 30);
		add.setFont(new Font("Arial",Font.BOLD,15));
		p.add(add);
		
		cancel=new JButton("CANCEL");
		cancel.setBounds(180, 190, 100, 30);
		cancel.setFont(new Font("Arial",Font.BOLD,15));
		p.add(cancel);
		
		add.addActionListener(this);
		cancel.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==add)
		{
			name=tname.getText();
			username=tuser.getText();
			password=tpass.getText();
			try
			{
				ps=con.prepareStatement("select * from login where name='"+name+"' and username='"+username+"'");
				rs=ps.executeQuery();			  
				if(rs.next())
				{
					JOptionPane.showMessageDialog(this,"User Already Exist..","Add",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					ps=con.prepareStatement("insert into login values('"+name+"','"+username+"','"+password+"')");
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this,"User added..","Add",JOptionPane.INFORMATION_MESSAGE);
					
					tname.setText("");
					tuser.setText("");
					tpass.setText("");
					
				}
				rs.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"User could not be added..","Add",JOptionPane.INFORMATION_MESSAGE);
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

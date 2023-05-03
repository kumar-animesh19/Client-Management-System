import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class RemoveUser extends JFrame implements ActionListener
{
	JPanel p;
	JLabel lremoveuser,luser;
	JTextField tuser;
	JButton remove,cancel;
	Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String username;
    
	RemoveUser()
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
		
		lremoveuser=new JLabel("REMOVE USER");
		lremoveuser.setBounds(30, 40, 200, 30);
		lremoveuser.setFont(new Font("Times New Roman",Font.BOLD,25));
		lremoveuser.setForeground(Color.BLUE);
		add(lremoveuser);
		
		p= new JPanel();
	    p.setLayout(null);
	    p.setBounds(30, 70, 320, 260);
	    p.setBackground(Color.LIGHT_GRAY);
	    add(p);
	    
	    luser=new JLabel("Username :");
	    luser.setBounds(40, 70, 70, 30);
		p.add(luser);
		tuser=new JTextField();
		tuser.setBounds(120,70,150,30);
		p.add(tuser);

		remove=new JButton("REMOVE");
		remove.setBounds(40, 190, 120, 30);
		remove.setFont(new Font("Arial",Font.BOLD,15));
		p.add(remove);
		
		cancel=new JButton("CANCEL");
		cancel.setBounds(180, 190, 100, 30);
		cancel.setFont(new Font("Arial",Font.BOLD,15));
		p.add(cancel);
		
		remove.addActionListener(this);
		cancel.addActionListener(this);
		
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==remove)
		{
			username=tuser.getText();
			try
			{
				ps=con.prepareStatement("select * from login where username='"+username+"'");
				rs=ps.executeQuery();			  
				if(rs.next())
				{
					int cd=JOptionPane.showConfirmDialog(this, "Are you sure..", "Remove", JOptionPane.YES_NO_OPTION);
					if(cd==0)
					{
						ps=con.prepareStatement("delete from login where username='"+username+"'");
						ps.executeUpdate();
						JOptionPane.showMessageDialog(this,"User removed..","Remove",JOptionPane.INFORMATION_MESSAGE);
						
						tuser.setText("");						
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,"User could not found..","Remove",JOptionPane.INFORMATION_MESSAGE);
				
				}
				rs.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"User could not be removed..","Insert",JOptionPane.INFORMATION_MESSAGE);
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
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.MessageFormat;
import javax.swing.*;
import javax.swing.table.*;
public class MaintenanceReport extends JFrame implements ActionListener
{
	JPanel p;
	JLabel maintreport,lmid,lpid;
	JButton show1,show2,showall,print,close;
	JComboBox<String> cbmid,cbpid;
	JTable jt;
	JScrollPane sp;
	DefaultTableModel model;
	Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
	MaintenanceReport()
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
   		setSize(700, 600);
   		setLocationRelativeTo(null);
   		setResizable(false);
   		setLayout(null);
		maintreport=new JLabel("MAINTENANCE   REPORT");
		maintreport.setBounds(200, 30, 350, 30);
		maintreport.setFont(new Font("Monotype corsiva",Font.BOLD,25));
		maintreport.setForeground(new Color(165, 42, 42));
		add(maintreport);
		
		close=new JButton("Close");
		close.setBounds(580, 25, 70, 20);
		close.setFont(new Font("Arial",Font.BOLD,12));
		add(close);
		
		p= new JPanel();
		p.setLayout(null);
		p.setBounds(30,70,620,450);
		p.setBackground(new Color(179,242,255));
		add(p);
		
		lmid=new JLabel("SELECT MAINTENANCE ID :");
		lmid.setBounds(2, 7, 170, 20);
		lmid.setForeground(Color.RED);
		p.add(lmid);
		cbmid=new JComboBox<>();
		cbmid.setBounds(165, 10, 100, 20);
		cbmid.addItem("---Select---");
		try
		{
			ps=con.prepareStatement("Select m_id from maintenance");
			rs=ps.executeQuery();
			while(rs.next())
			{
				cbmid.addItem(rs.getString(1));
			}
			rs.close();
		}
		catch(Exception ex)
		{
			
		}
		p.add(cbmid);
		
		lpid=new JLabel("SELECT PROJECT ID :");
		lpid.setBounds(30, 35, 150, 30);
		lpid.setForeground(Color.RED);
		p.add(lpid);
		cbpid=new JComboBox<>();
		cbpid.setBounds(165, 40, 100, 20);
		cbpid.addItem("---Select---");
		try
		{
			ps=con.prepareStatement("Select p_id from project");
			rs=ps.executeQuery();
			while(rs.next())
			{
				cbpid.addItem(rs.getString(1));
			}
			rs.close();
		}
		catch(Exception ex)
		{
			
		}
		p.add(cbpid);
		
		show1=new JButton("SHOW");
		show1.setBounds(270, 10, 100, 20);
		show1.setFont(new Font("Arial",Font.BOLD,15));
		p.add(show1);
		
		show2=new JButton("SHOW");
		show2.setBounds(270, 40, 100, 20);
		show2.setFont(new Font("Arial",Font.BOLD,15));
		p.add(show2);
		
		showall=new JButton("SHOW ALL");
		showall.setBounds(380, 20, 120, 25);
		showall.setFont(new Font("Arial",Font.BOLD,15));
		p.add(showall);
		
		print=new JButton("PRINT");
		print.setBounds(510, 20, 100, 25);
		print.setFont(new Font("Arial",Font.BOLD,15));
		p.add(print);
		
		jt=new JTable();
	    jt.getTableHeader().setBackground(Color.BLACK);
	    jt.getTableHeader().setForeground(Color.WHITE);
	    jt.setForeground(Color.BLUE);
	    jt.setRowHeight(30);
	    sp=new JScrollPane(jt);
	    sp.setBounds(0, 70, 620, 380);
	    sp.getViewport().setBackground(new Color(179,242,255));
	    p.add(sp);
		
		model=new DefaultTableModel();
		model.addColumn("MAINTENANCE ID");
		model.addColumn("PROJECT ID");
		model.addColumn("DATE");
		model.addColumn("COST");
		jt.setModel(model);
		
		sp.setVisible(false);
		
		cbmid.addActionListener(this);
		cbpid.addActionListener(this);
		show1.addActionListener(this);
		show2.addActionListener(this);
		showall.addActionListener(this);
		print.addActionListener(this);
		close.addActionListener(this);
		
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==show1)
		{
			if(cbmid.getSelectedItem().equals("---Select---"))
			{
				sp.setVisible(false);
				model.setRowCount(0);
				JOptionPane.showMessageDialog(this,"Please select maintenance ID","Show",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				sp.setVisible(true);
				model.setRowCount(0);
				String row[]=new String[4];
				try
				{
					ps=con.prepareStatement("select * from maintenance where m_id='"+cbmid.getSelectedItem()+"'");
					rs=ps.executeQuery();
					if(rs.next())
					{
						for(int i=0;i<4;i++)
						row[i]=rs.getString(i+1);
						model.addRow(row);
					}

					rs.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(this,ex.getMessage(),"Show",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		if(ae.getSource()==show2)
		{
			if(cbpid.getSelectedItem().equals("---Select---"))
			{
				sp.setVisible(false);
				model.setRowCount(0);
				JOptionPane.showMessageDialog(this,"Please select project ID","Show",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				sp.setVisible(false);
				model.setRowCount(0);
				int flag=0;
				String row[]=new String[4];
				try
				{
					ps=con.prepareStatement("select * from maintenance where p_id='"+cbpid.getSelectedItem()+"'");
					rs=ps.executeQuery();
					while(rs.next())
					{
						for(int i=0;i<4;i++)
						row[i]=rs.getString(i+1);
						model.addRow(row);
						flag++;
						sp.setVisible(true);
					}
					if(flag==0)	
			  		{
						JOptionPane.showMessageDialog(this,"Data could not found...","Show",JOptionPane.ERROR_MESSAGE);						
						
					}
					rs.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(this,ex.getMessage(),"Show",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		if(ae.getSource()==showall)
		{
			sp.setVisible(false);
			model.setRowCount(0);
			int flag=0;
			String row[]=new String[4];
			try
			{
				ps=con.prepareStatement("select * from maintenance");
				rs=ps.executeQuery();
				while(rs.next())
				{
					for(int i=0;i<4;i++)
					row[i]=rs.getString(i+1);
					model.addRow(row);
					flag++;
					sp.setVisible(true);
				}
				if(flag==0)	
		  		{
					JOptionPane.showMessageDialog(this,"Data could not found...","Show All",JOptionPane.ERROR_MESSAGE);						
					
				}
				rs.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,ex.getMessage(),"ShowAll",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(ae.getSource()==print)
		{
			MessageFormat header=new MessageFormat("MAINTENANCE REPORT");
			MessageFormat footer=new MessageFormat("Page {0,number,integer}");
			try
			{
				jt.print(JTable.PrintMode.FIT_WIDTH,header,footer);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null,ex.getMessage());
			}
		}
		
		if(ae.getSource()==close)
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

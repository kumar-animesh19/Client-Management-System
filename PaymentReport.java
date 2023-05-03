import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.MessageFormat;
import javax.swing.*;
import javax.swing.table.*;
public class PaymentReport extends JFrame implements ActionListener
{
	JPanel p;
	JLabel payreport,lpayid,lcid;
	JButton show1,show2,showall,print,close;
	JComboBox<String> cbpayid,cbcid;
	JTable jt;
	JScrollPane sp;
	DefaultTableModel model;
	Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
	PaymentReport()
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
		payreport=new JLabel("PAYMENT   REPORT");
		payreport.setBounds(200, 30, 350, 30);
		payreport.setFont(new Font("Monotype corsiva",Font.BOLD,25));
		payreport.setForeground(new Color(165, 42, 42));
		add(payreport);
		
		close=new JButton("Close");
		close.setBounds(580, 25, 70, 20);
		close.setFont(new Font("Arial",Font.BOLD,12));
		add(close);

		p= new JPanel();
		p.setLayout(null);
		p.setBounds(30,70,620,450);
		p.setBackground(new Color(179,242,255));
		add(p);
		
		lpayid=new JLabel("SELECT PAYMENT ID :");
		lpayid.setBounds(30, 10, 150, 30);
		lpayid.setForeground(Color.RED);
		p.add(lpayid);
		cbpayid=new JComboBox<>();
		cbpayid.setBounds(165, 15, 100, 20);
		cbpayid.addItem("---Select---");
		try
		{
			ps=con.prepareStatement("Select pay_id from payment");
			rs=ps.executeQuery();
			while(rs.next())
			{
				cbpayid.addItem(rs.getString(1));
			}
			rs.close();
		}
		catch(Exception ex)
		{
			
		}
		p.add(cbpayid);
		
		lcid=new JLabel("SELECT CLIENT ID :");
		lcid.setBounds(43, 35, 150, 30);
		lcid.setForeground(Color.RED);
		p.add(lcid);
		cbcid=new JComboBox<>();
		cbcid.setBounds(165, 40, 100, 20);
		cbcid.addItem("---Select---");
		try
		{
			ps=con.prepareStatement("Select c_id from client");
			rs=ps.executeQuery();
			while(rs.next())
			{
				cbcid.addItem(rs.getString(1));
			}
			rs.close();
		}
		catch(Exception ex)
		{
			
		}
		p.add(cbcid);
		
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
		model.addColumn("PAYMENT ID");
		model.addColumn("CLIENT ID");
		model.addColumn("PROJECT ID");
		model.addColumn("MAINTENANCE ID");
		model.addColumn("DATE");
		model.addColumn("MODE");
		model.addColumn("AMOUNT");
		model.addColumn("DUES");
		jt.setModel(model);
		
		sp.setVisible(false);
		
		cbpayid.addActionListener(this);
		cbcid.addActionListener(this);
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
			if(cbpayid.getSelectedItem().equals("---Select---"))
			{
				sp.setVisible(false);
				model.setRowCount(0);
				JOptionPane.showMessageDialog(this,"Please select payment ID","Show",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				sp.setVisible(true);
				model.setRowCount(0);
				String row[]=new String[8];
				try
				{
					ps=con.prepareStatement("select * from payment where pay_id='"+cbpayid.getSelectedItem()+"'");
					rs=ps.executeQuery();
					if(rs.next())
					{
						for(int i=0;i<8;i++)
						row[i]=rs.getString(i+1);
						model.addRow(row);
					}
					autoColumnAdjust();
	
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
			if(cbcid.getSelectedItem().equals("---Select---"))
			{
				sp.setVisible(false);
				model.setRowCount(0);
				JOptionPane.showMessageDialog(this,"Please select client ID","Show",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				sp.setVisible(false);
				model.setRowCount(0);
				int flag=0;
				String row[]=new String[8];
				try
				{
					ps=con.prepareStatement("select * from payment where c_id='"+cbcid.getSelectedItem()+"'");
					rs=ps.executeQuery();
					while(rs.next())
					{
						for(int i=0;i<8;i++)
						row[i]=rs.getString(i+1);
						model.addRow(row);
						flag++;
						sp.setVisible(true);
					}
					autoColumnAdjust();
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
			String row[]=new String[8];
			try
			{
				ps=con.prepareStatement("select * from payment");
				rs=ps.executeQuery();
				while(rs.next())
				{
					for(int i=0;i<8;i++)
					row[i]=rs.getString(i+1);
					model.addRow(row);
					flag++;
					sp.setVisible(true);
				}
				autoColumnAdjust();
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
			MessageFormat header=new MessageFormat("PAYMENT REPORT");
			MessageFormat footer=new MessageFormat("Page{0,number,integer}");
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
	
	void autoColumnAdjust()
	{
		jt.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
	   	for (int c=0; c<jt.getColumnCount(); c++)
	   	{
	   		TableColumn tableColumn=jt.getColumnModel().getColumn(c);
	   		int preferredWidth = 100;
	   		int maxWidth=tableColumn.getMaxWidth();

	   		for (int r = 0; r < jt.getRowCount(); r++)
	   		{
	   			TableCellRenderer cellRenderer = jt.getCellRenderer(r, c);
	   		    Component com = jt.prepareRenderer(cellRenderer, r, c);
	   		    int width = com.getPreferredSize().width + jt.getIntercellSpacing().width;
	   		    preferredWidth = Math.max(preferredWidth, width);
	   		        
	   		    if (preferredWidth >= maxWidth)
	   		    {
	   		    	preferredWidth = maxWidth;
	   		        break;
	   		    }
	   		}
	   		tableColumn.setPreferredWidth(preferredWidth);
	   	}
	}
}

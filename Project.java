import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
public class Project extends JFrame implements ActionListener
{
	JPanel p1,p2;
	JTabbedPane tp;
	JLabel project,lcid,lname,lcost,lsdate,lsformat,ldelivery,ldformat,lsearch;
	JTextField tcid,tname,tcost,tsdate,tdelivery,tsearch;
	JButton search,save,update,fetch,reset,close;
	JTable jt;
	JScrollPane sp;
	DefaultTableModel model;
	JCheckBox cbpid,cbcid,cball;
	ButtonGroup bg;
	Connection con;
    PreparedStatement ps;
    ResultSet rs,rs1;
    String p_id,c_id,p_name,cost,start_date,delivery_date;
    
    Project()
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
		
		project=new JLabel("PROJECT   DETAILS");
		project.setBounds(225, 30, 250, 30);
		project.setFont(new Font("Monotype corsiva",Font.BOLD,25));
		project.setForeground(new Color(165, 42, 42));
		add(project);
		
		close=new JButton("Close");
		close.setBounds(580, 25, 70, 20);
		close.setFont(new Font("Arial",Font.BOLD,12));
		add(close);
		
		tp = new JTabbedPane();
        tp.setBounds(30,70,620,450);
        add(tp);
        
        p1= new JPanel();
		p1.setLayout(null);
	    p1.setBackground(new Color(179,242,255));
	    add(p1);
	    
	    tp.addTab("Search",p1);
	    
	    cbpid=new JCheckBox("PROJECT ID");
	    cbpid.setBounds(30, 50, 100, 15);
		cbpid.setBackground(new Color(179,242,255));
		p1.add(cbpid);
		
		cbcid=new JCheckBox("CLIENT ID");
		cbcid.setBounds(140, 50, 100, 15);
		cbcid.setBackground(new Color(179,242,255));
		p1.add(cbcid);
		
		cball=new JCheckBox("ALL PROJECT");
		cball.setBounds(240, 50, 120, 15);
		cball.setBackground(new Color(179,242,255));
		p1.add(cball);
		
		bg=new ButtonGroup();
		bg.add(cbpid);
		bg.add(cbcid);
		bg.add(cball);
		
		search=new JButton("SEARCH");
		search.setBounds(400, 40, 100, 30);
		search.setFont(new Font("Arial",Font.BOLD,15));
		p1.add(search);
		
		jt=new JTable();
	    jt.getTableHeader().setBackground(Color.BLACK);
	    jt.getTableHeader().setForeground(Color.WHITE);
	    jt.setForeground(Color.BLUE);
	    jt.setRowHeight(30);
	    sp=new JScrollPane(jt);
	    sp.setBounds(0, 100, 616, 323);
	    sp.getViewport().setBackground(new Color(179,242,255));
	    p1.add(sp);

		model=new DefaultTableModel();
		model.addColumn("PROJECT ID");
		model.addColumn("CLIENT ID");
		model.addColumn("PROJECT NAME");
		model.addColumn("COST");
		model.addColumn("START DATE");
		model.addColumn("DELIVERY DATE");
		jt.setModel(model);
		
		sp.setVisible(false);
		
	    p2= new JPanel();
	    p2.setLayout(null);
	    p2.setBackground(new Color(179,242,255));
	    add(p2);
	    
	    tp.addTab("Insert & Update",p2);
		
		lcid=new JLabel("CLIENT ID : *");
		lcid.setBounds(180, 30, 100, 30);
		lcid.setForeground(Color.RED);
		p2.add(lcid);
		tcid=new JTextField();
		tcid.setBounds(310, 30, 70, 30);
		p2.add(tcid);
		
		lname=new JLabel("PROJECT NAME : *");
		lname.setBounds(180, 80, 110, 30);
		lname.setForeground(Color.RED);
		p2.add(lname);
		tname=new JTextField();
		tname.setBounds(310, 80, 150, 30);
		p2.add(tname);
		
		lcost=new JLabel("COST : *");
		lcost.setBounds(180, 130, 100, 30);
		lcost.setForeground(Color.RED);
		p2.add(lcost);
		tcost=new JTextField();
		tcost.setBounds(310, 130, 100, 30);
		p2.add(tcost);
		
		lsdate=new JLabel("START DATE : *");
		lsdate.setBounds(180,180, 100, 30);
		lsdate.setForeground(Color.RED);
		p2.add(lsdate);
		tsdate=new JTextField();
		tsdate.setBounds(310, 180, 100, 30);
		p2.add(tsdate);
		lsformat=new JLabel("(dd-mm-yyyy)");
		lsformat.setBounds(320, 210, 100, 15);
		lsformat.setFont(new Font("Arial",Font.PLAIN,12));
		lsformat.setForeground(Color.BLUE);
		p2.add(lsformat);
		
		ldelivery=new JLabel("DELIVERY DATE :");
		ldelivery.setBounds(180, 240, 110, 30);
		ldelivery.setForeground(Color.RED);
		p2.add(ldelivery);
		tdelivery=new JTextField();
		tdelivery.setBounds(310, 240, 100, 30);
		p2.add(tdelivery);
		ldformat=new JLabel("(dd-mm-yyyy)");
		ldformat.setBounds(320, 270, 100, 15);
		ldformat.setFont(new Font("Arial",Font.PLAIN,12));
		ldformat.setForeground(Color.BLUE);
		p2.add(ldformat);
		
		save=new JButton("SAVE");
		save.setBounds(50, 320, 100, 30);
		save.setFont(new Font("Arial",Font.BOLD,15));
		p2.add(save);
		
		update=new JButton("UPDATE");
		update.setBounds(180, 320, 100, 30);
		update.setFont(new Font("Arial",Font.BOLD,15));
		p2.add(update);
		
		fetch=new JButton("FETCH");
		fetch.setBounds(310, 320, 100, 30);
		fetch.setFont(new Font("Arial",Font.BOLD,15));
		p2.add(fetch);
		
		reset=new JButton("RESET");
		reset.setBounds(440, 320, 100, 30);
		reset.setFont(new Font("Arial",Font.BOLD,15));
		p2.add(reset);
		
		search.addActionListener(this);
		save.addActionListener(this);
		update.addActionListener(this);
		fetch.addActionListener(this);
		reset.addActionListener(this);
		close.addActionListener(this);
		
		setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
    	if(ae.getSource()==search)
		{
			model.setRowCount(0);
			if(cbpid.isSelected())
			{
				sp.setVisible(false);
				int flag=0;
				String row[]=new String[6];
				
				try
				{
					p_id=JOptionPane.showInputDialog(this, "Enter project ID :");
					ps=con.prepareStatement("select * from project where p_id='"+p_id+"'");
					rs=ps.executeQuery();
					if(rs.next())
					{
						for(int i=0;i<6;i++)
						row[i]=rs.getString(i+1);
						model.addRow(row);
						flag++;
						sp.setVisible(true);
					}
					autoColumnAdjust();
					if(flag==0)	
			  		{
						JOptionPane.showMessageDialog(this,"Data could not found...","Search",JOptionPane.ERROR_MESSAGE);						
						
					}
					rs.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(this,ex.getMessage(),"Search",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else if(cbcid.isSelected())
			{
				sp.setVisible(false);
				int flag=0;
				String row[]=new String[6];
				
				try
				{
					c_id=JOptionPane.showInputDialog(this, "Enter customer ID :");
					ps=con.prepareStatement("select * from project where c_id='"+c_id+"'");
					rs=ps.executeQuery();
					while(rs.next())
					{
						for(int i=0;i<6;i++)
						row[i]=rs.getString(i+1);
						model.addRow(row);
						flag++;
						sp.setVisible(true);
					}
					autoColumnAdjust();
					if(flag==0)	
			  		{
						JOptionPane.showMessageDialog(this,"Data could not found...","Search",JOptionPane.ERROR_MESSAGE);						
						
					}
					rs.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(this,ex.getMessage(),"Search",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			else if(cball.isSelected())
			{
				sp.setVisible(false);
				int flag=0;
				String row[]=new String[6];
				
				try
				{
					ps=con.prepareStatement("select * from project");
					rs=ps.executeQuery();
					while(rs.next())
					{
						for(int i=0;i<6;i++)
						row[i]=rs.getString(i+1);
						model.addRow(row);
						flag++;
						sp.setVisible(true);
					}
					autoColumnAdjust();
					if(flag==0)	
			  		{
						JOptionPane.showMessageDialog(this,"Data could not found...","Search",JOptionPane.ERROR_MESSAGE);						
						
					}
					rs.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(this,ex.getMessage(),"Search",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			else
			{
				JOptionPane.showMessageDialog(this,"Please select required checkbox...","Search",JOptionPane.INFORMATION_MESSAGE);
			}
		}
    	
    	if(ae.getSource()==save)
		{
    		try
			{
				ps=con.prepareStatement("Select max(p_id) from project");
				rs=ps.executeQuery();
				if(rs.next())
				{
					char id[]=rs.getString(1).toCharArray();
					id[id.length-1]++;
					p_id=(String.valueOf(id));
				}
				
				rs.close();
			}
			catch(Exception ex)
			{
				p_id="P101";
			}
    		
			c_id=tcid.getText();
			p_name=tname.getText();
			cost=tcost.getText();
			start_date=tsdate.getText();
			delivery_date=tdelivery.getText();
			
			try
			{
				ps=con.prepareStatement("select * from project where c_id='"+c_id+"' and p_name='"+p_name+"'");
				rs=ps.executeQuery();			  
				if(rs.next())
				{
					JOptionPane.showMessageDialog(this,"Data Already Exist..","Save",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					ps=con.prepareStatement("insert into project values('"+p_id+"','"+c_id+"','"+p_name+"',"+cost+",'"+start_date+"','"+delivery_date+"')");
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this,"Saved..\nPROJECT ID="+p_id,"Save",JOptionPane.INFORMATION_MESSAGE);
					
					tcid.setText("");
					tname.setText("");
					tcost.setText("");
					tsdate.setText("");
					tdelivery.setText("");
				}
				rs.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"Data could not be saved..","Save",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
    	if(ae.getSource()==update)
		{
			c_id=tcid.getText();
			p_name=tname.getText();
			cost=tcost.getText();
			start_date=tsdate.getText();
			delivery_date=tdelivery.getText();
			
			try
			{
				ps=con.prepareStatement("select * from payment where p_id='"+p_id+"' and dues>0");
				rs=ps.executeQuery();
				if(rs.next())
				{
					int dues;
					ps=con.prepareStatement("select cost from project where p_id='"+p_id+"'");
					rs1=ps.executeQuery();
					if(rs1.next())
					{
						dues=Integer.valueOf(rs.getString(8))+(Integer.valueOf(cost)-Integer.valueOf(rs1.getString(1)));
						ps=con.prepareStatement("update project set  c_id='"+c_id+"',p_name='"+p_name+"',cost="+cost+",start_date='"+start_date+"',delivery_date='"+delivery_date+"' where p_id='"+p_id+"'");
						ps.executeUpdate();
						ps=con.prepareStatement("update payment set dues="+dues+" where pay_id='"+rs.getString(1)+"'");
						ps.executeUpdate();
					}
				}
				else
				{
					ps=con.prepareStatement("update project set c_id='"+c_id+"',p_name='"+p_name+"',cost="+cost+",start_date='"+start_date+"',delivery_date='"+delivery_date+"' where p_id='"+p_id+"'");
					ps.executeUpdate();
				}
				JOptionPane.showMessageDialog(this,"Update Successful..","Update",JOptionPane.INFORMATION_MESSAGE);
				
				tcid.setText("");
				tname.setText("");
				tcost.setText("");
				tsdate.setText("");
				tdelivery.setText("");
				
				rs.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"Data could not be updated..","Update",JOptionPane.INFORMATION_MESSAGE);
			}
		}
			
		if(ae.getSource()==fetch)
		{
			try
			{
				p_id=JOptionPane.showInputDialog(this, "Enter project ID :");
				ps=con.prepareStatement("select * from project where p_id='"+p_id+"'");
				rs=ps.executeQuery();
				
				if(rs.next())
				{
					tcid.setText(rs.getString(2));
					tname.setText(rs.getString(3));
					tcost.setText(rs.getString(4));
					tsdate.setText(rs.getString(5));
					tdelivery.setText(rs.getString(6));
				}
				else	
		  		{
					JOptionPane.showMessageDialog(this,"Data could not found...","Fetch",JOptionPane.ERROR_MESSAGE);						
					
				}
				rs.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,ex.getMessage(),"Fetch",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(ae.getSource()==reset)
		{
			tcid.setText("");
			tname.setText("");
			tcost.setText("");
			tsdate.setText("");
			tdelivery.setText("");
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
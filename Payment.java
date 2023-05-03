import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
public class Payment extends JFrame implements ActionListener
{
	JPanel p1,p2;
	JTabbedPane tp;
	JLabel payment,lcid,ltype,lpomid,ldate,lformat,lmode,lamount;
	JTextField tcid,tpomid,tdate,tamount;
	JButton search,save,update,fetch,reset,close;
	JComboBox<String> cbtype,cbmode;
	JTable jt;
	JScrollPane sp;
	DefaultTableModel model;
	JCheckBox cbpayid,cbcid,cbpid,cbmid,cball;
	ButtonGroup bg;
	Connection con;
    PreparedStatement ps;
    ResultSet rs,rs1;
    String pay_id,c_id,p_id,m_id,pay_date,pay_mode;
    int amount,dues;
    
    Payment()
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
		
		payment=new JLabel("PAYMENT   DETAILS");
		payment.setBounds(200, 30, 350, 30);
		payment.setFont(new Font("Monotype corsiva",Font.BOLD,25));
		payment.setForeground(new Color(165, 42, 42));
		add(payment);
		
		close=new JButton("Close");
		close.setBounds(580, 25, 70, 20);
		close.setFont(new Font("Arial",Font.BOLD,12));
		add(close);
		
		tp=new JTabbedPane();
		tp.setBounds(30,70,620,450);
	    add(tp);
	    
	    p1= new JPanel();
	    p1.setLayout(null);
	    p1.setBackground(new Color(179,242,255));
	    add(p1);
	    
	    tp.addTab("Search",p1);
	    
	    cbpayid=new JCheckBox("PAYMENT ID");
	    cbpayid.setBounds(20, 40, 130, 15);
		cbpayid.setBackground(new Color(179,242,255));
		p1.add(cbpayid);
		
		cbcid=new JCheckBox("CLIENT ID");
		cbcid.setBounds(150, 40, 100, 15);
		cbcid.setBackground(new Color(179,242,255));
		p1.add(cbcid);
		
		cbpid=new JCheckBox("PROJECT ID");
		cbpid.setBounds(260, 40, 100, 15);
		cbpid.setBackground(new Color(179,242,255));
		p1.add(cbpid);
		
		cbmid=new JCheckBox("MAINTENANCE ID");
		cbmid.setBounds(20, 65, 130, 15);
		cbmid.setBackground(new Color(179,242,255));
		p1.add(cbmid);
		
		cball=new JCheckBox("ALL PAYMENT");
		cball.setBounds(150, 65, 120, 15);
		cball.setBackground(new Color(179,242,255));
		p1.add(cball);
		
		bg=new ButtonGroup();
		bg.add(cbpayid);
		bg.add(cbcid);
		bg.add(cbpid);
		bg.add(cbmid);
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
		
	    p2= new JPanel();
	    p2.setLayout(null);
	    p2.setBackground(new Color(179,242,255));
	    add(p2);
	    
	    tp.addTab("Insert & Update",p2);
	    
		lcid=new JLabel("CLIENT ID : *");
		lcid.setBounds(30, 50, 100, 30);
		lcid.setForeground(Color.RED);
		p2.add(lcid);
		tcid=new JTextField();
		tcid.setBounds(145, 50, 70, 30);
		p2.add(tcid);
		
		ltype=new JLabel("PAYMENT TYPE : *");
		ltype.setBounds(30, 110, 120, 30);
		ltype.setForeground(Color.RED);
		p2.add(ltype);
		String titem[]= {"---Select---","Project","Maintenance"};
		cbtype=new JComboBox<>(titem);
		cbtype.setBounds(145, 110, 100, 25);
		p2.add(cbtype);
		
		lpomid=new JLabel("");
		lpomid.setBounds(28, 170, 130, 30);
		lpomid.setForeground(Color.RED);
		p2.add(lpomid);
		tpomid=new JTextField();
		tpomid.setBounds(145, 170, 70, 30);
		tpomid.setVisible(false);
		p2.add(tpomid);
		
		ldate=new JLabel("PAYMENT DATE : *");
		ldate.setBounds(320, 50, 120, 30);
		ldate.setForeground(Color.RED);
		p2.add(ldate);
		tdate=new JTextField();
		tdate.setBounds(440, 50, 100, 30);
		p2.add(tdate);
		lformat=new JLabel("(dd-mm-yyyy)");
		lformat.setBounds(450, 80, 100, 15);
		lformat.setFont(new Font("Arial",Font.PLAIN,12));
		lformat.setForeground(Color.BLUE);
		p2.add(lformat);
		
		lmode=new JLabel("PAYMENT MODE : *");
		lmode.setBounds(320, 110, 120, 30);
		lmode.setForeground(Color.RED);
		p2.add(lmode);
		String mitem[]= {"---Select---","Cash","Online","Cheque"};
		cbmode=new JComboBox<>(mitem);
		cbmode.setBounds(440, 110, 100, 25);
		p2.add(cbmode);
		
		lamount=new JLabel("AMOUNT : *");
		lamount.setBounds(320, 170, 100, 30);
		lamount.setForeground(Color.RED);
		p2.add(lamount);
		tamount=new JTextField();
		tamount.setBounds(440, 170, 100, 30);
		p2.add(tamount);
		
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
		
		cbtype.addActionListener(this);
		cbmode.addActionListener(this);
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
    	if(ae.getSource()==cbtype)
    	{
    		if(cbtype.getSelectedItem().equals("Project"))
    		{
    			lpomid.setText("PROJECT ID : *");
    			tpomid.setVisible(true);
    		}
    		else if(cbtype.getSelectedItem().equals("Maintenance"))
    		{
    			lpomid.setText("MAINTENANCE ID : *");
    			tpomid.setVisible(true);
    		}
    		else
    		{
    			lpomid.setText("");
    			tpomid.setVisible(false);
    		}
    	}
    	
    	if(ae.getSource()==search)
		{
			model.setRowCount(0);
			if(cbpayid.isSelected())
			{
				sp.setVisible(false);
				int flag=0;
				String row[]=new String[8];
				
				try
				{
					pay_id=JOptionPane.showInputDialog(this, "Enter payment ID :");
					ps=con.prepareStatement("select * from payment where pay_id='"+pay_id+"'");
					rs=ps.executeQuery();
					if(rs.next())
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
				String row[]=new String[8];
				
				try
				{
					c_id=JOptionPane.showInputDialog(this, "Enter client ID :");
					ps=con.prepareStatement("select * from payment where c_id='"+c_id+"'");
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
						JOptionPane.showMessageDialog(this,"Data could not found...","Search",JOptionPane.ERROR_MESSAGE);						
						
					}
					rs.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(this,ex.getMessage(),"Search",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			else if(cbpid.isSelected())
			{
				sp.setVisible(false);
				int flag=0;
				String row[]=new String[8];
				
				try
				{
					p_id=JOptionPane.showInputDialog(this, "Enter project ID :");
					ps=con.prepareStatement("select * from payment where p_id='"+p_id+"'");
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
						JOptionPane.showMessageDialog(this,"Data could not found...","Search",JOptionPane.ERROR_MESSAGE);						
						
					}
					rs.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(this,ex.getMessage(),"Search",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else if(cbmid.isSelected())
			{
				sp.setVisible(false);
				int flag=0;
				String row[]=new String[8];
				
				try
				{
					m_id=JOptionPane.showInputDialog(this, "Enter maintenance ID :");
					ps=con.prepareStatement("select * from payment where m_id='"+m_id+"'");
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
				ps=con.prepareStatement("Select max(pay_id) from payment");
				rs=ps.executeQuery();
				if(rs.next())
				{
					char id[]=rs.getString(1).toCharArray();
					id[id.length-1]++;
					pay_id=(String.valueOf(id));
				}

				rs.close();
			}
			catch(Exception ex)
			{
				pay_id="PAY101";
			}
    		
    		c_id=tcid.getText();
			pay_date=tdate.getText();
			if(cbmode.getSelectedItem().equals("---Select---"))
			{
				JOptionPane.showMessageDialog(this, "Select Payment Mode");
				return;
			}
			else
				pay_mode=(String)cbmode.getSelectedItem();
			amount=Integer.valueOf(tamount.getText());
			
			try
			{	
				if(cbtype.getSelectedItem().equals("Project"))
				{
					p_id=tpomid.getText();
					ps=con.prepareStatement("select * from payment where p_id='"+p_id+"' and dues>0");
					rs=ps.executeQuery();
					if(rs.next())
					{
						dues=Integer.valueOf(rs.getString(8))-amount;
						ps=con.prepareStatement("update payment set dues=0 where pay_id='"+rs.getString(1)+"'");
						ps.executeUpdate();
					}
					else 
					{
						ps=con.prepareStatement("select cost from project where p_id='"+p_id+"'");
						rs=ps.executeQuery();			  
						if(rs.next())
						{
							dues=Integer.valueOf(rs.getString(1))-amount;
						}
					}
					if(dues<0)
					{
						JOptionPane.showMessageDialog(this,"Entered amount is more than dues..","Save",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					ps=con.prepareStatement("insert into payment(pay_id,c_id,p_id,pay_date,pay_mode,amount,dues) values('"+pay_id+"','"+c_id+"','"+p_id+"','"+pay_date+"','"+pay_mode+"',"+amount+","+dues+")");
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this,"Saved..\nPAYMENT ID="+pay_id,"Save",JOptionPane.INFORMATION_MESSAGE);
								
					tcid.setText("");
					cbtype.setSelectedIndex(0);
					tpomid.setText("");
					tdate.setText("");
					cbmode.setSelectedIndex(0);
					tamount.setText("");
				}
				else if(cbtype.getSelectedItem().equals("Maintenance"))
				{
					m_id=tpomid.getText();
					ps=con.prepareStatement("select * from payment where m_id='"+m_id+"' and dues>0");
					rs=ps.executeQuery();
					if(rs.next())
					{
						dues=Integer.valueOf(rs.getString(8))-amount;
						ps=con.prepareStatement("update payment set dues=0 where pay_id='"+rs.getString(1)+"'");
						ps.executeUpdate();
					}
					else
					{
						ps=con.prepareStatement("select cost from maintenance where m_id='"+m_id+"'");
						rs=ps.executeQuery();			  
						if(rs.next())
						{
							dues=Integer.valueOf(rs.getString(1))-amount;
						}
					}
					if(dues<0)
					{
						JOptionPane.showMessageDialog(this,"Entered amount is more than dues..","Save",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					ps=con.prepareStatement("insert into payment(pay_id,c_id,m_id,pay_date,pay_mode,amount,dues) values('"+pay_id+"','"+c_id+"','"+m_id+"','"+pay_date+"','"+pay_mode+"',"+amount+","+dues+")");
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this,"Saved..\nPAYMENT ID="+pay_id,"Save",JOptionPane.INFORMATION_MESSAGE);
						
					tcid.setText("");
					cbtype.setSelectedIndex(0);
					tpomid.setText("");
					tdate.setText("");
					cbmode.setSelectedIndex(0);
					tamount.setText("");
				}	
				else
				{
					JOptionPane.showMessageDialog(this, "Select Payment Type");
					return;
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
			pay_date=tdate.getText();
			if(cbmode.getSelectedItem().equals("---Select---"))
			{
				JOptionPane.showMessageDialog(this, "Select Payment Mode");
				return;
			}
			else
				pay_mode=(String)cbmode.getSelectedItem();
			amount=Integer.valueOf(tamount.getText());
			
			try
			{
				if(cbtype.getSelectedItem().equals("Project"))
				{
					p_id=tpomid.getText();
					ps=con.prepareStatement("select * from payment where pay_id='"+pay_id+"'");
					rs=ps.executeQuery();
					if(rs.next())
					{
						if(Integer.valueOf(rs.getString(8))>0)
						{
							dues=Integer.valueOf(rs.getString(8))+(Integer.valueOf(rs.getString(7))-amount);
							if(dues<0)
							{
								JOptionPane.showMessageDialog(this,"Entered amount is more than dues..","Update",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							ps=con.prepareStatement("update payment set c_id='"+c_id+"',p_id='"+p_id+"',pay_date='"+pay_date+"',pay_mode='"+pay_mode+"',amount="+amount+",dues="+dues+" where pay_id='"+pay_id+"'");
							ps.executeUpdate();
						}
						else
						{
							ps=con.prepareStatement("select * from payment where p_id='"+p_id+"' and dues>0");
							rs1=ps.executeQuery();
							if(rs1.next())
							{
								dues=Integer.valueOf(rs1.getString(8))+(Integer.valueOf(rs.getString(7))-amount);
							}
							if(dues<0)
							{
								JOptionPane.showMessageDialog(this,"Entered amount is more than dues..","Update",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							ps=con.prepareStatement("update payment set c_id='"+c_id+"',p_id='"+p_id+"',pay_date='"+pay_date+"',pay_mode='"+pay_mode+"',amount="+amount+" where pay_id='"+pay_id+"'");
							ps.executeUpdate();
							ps=con.prepareStatement("update payment set dues="+dues+" where pay_id='"+rs1.getString(1)+"'");
							ps.executeUpdate();
						}
						JOptionPane.showMessageDialog(this,"Update Successful..","Update",JOptionPane.INFORMATION_MESSAGE);
						
						tcid.setText("");
						cbtype.setSelectedIndex(0);
						tpomid.setText("");
						tdate.setText("");
						cbmode.setSelectedIndex(0);
						tamount.setText("");
					}
				}
				else if(cbtype.getSelectedItem().equals("Maintenance"))
				{
					m_id=tpomid.getText();
					ps=con.prepareStatement("select * from payment where pay_id='"+pay_id+"'");
					rs=ps.executeQuery();
					if(rs.next())
					{
						if(Integer.valueOf(rs.getString(8))>0)
						{
							dues=Integer.valueOf(rs.getString(8))+(Integer.valueOf(rs.getString(7))-amount);
							if(dues<0)
							{
								JOptionPane.showMessageDialog(this,"Entered amount is more than dues..","Update",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							ps=con.prepareStatement("update payment set c_id='"+c_id+"',m_id='"+m_id+"',pay_date='"+pay_date+"',pay_mode='"+pay_mode+"',amount="+amount+",dues="+dues+" where pay_id='"+pay_id+"'");
							ps.executeUpdate();
						}
						else
						{
							ps=con.prepareStatement("select * from payment where m_id='"+m_id+"' and dues>0");
							rs1=ps.executeQuery();
							if(rs1.next())
							{
								dues=Integer.valueOf(rs1.getString(8))+(Integer.valueOf(rs.getString(7))-amount);
							}
							if(dues<0)
							{
								JOptionPane.showMessageDialog(this,"Entered amount is more than dues..","Update",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							ps=con.prepareStatement("update payment set c_id='"+c_id+"',m_id='"+m_id+"',pay_date='"+pay_date+"',pay_mode='"+pay_mode+"',amount="+amount+" where pay_id='"+pay_id+"'");
							ps.executeUpdate();
							ps=con.prepareStatement("update payment set dues="+dues+" where pay_id='"+rs1.getString(1)+"'");
							ps.executeUpdate();
						}
						JOptionPane.showMessageDialog(this,"Update Successful..","Update",JOptionPane.INFORMATION_MESSAGE);
							
						tcid.setText("");
						cbtype.setSelectedIndex(0);
						tpomid.setText("");
						tdate.setText("");
						cbmode.setSelectedIndex(0);
						tamount.setText("");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Select Payment Type");
					return;
				}
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
				pay_id=JOptionPane.showInputDialog(this, "Enter payment ID :");
				ps=con.prepareStatement("select * from payment where pay_id='"+pay_id+"'");
				rs=ps.executeQuery();
				if(rs.next())
				{
		    		tcid.setText(rs.getString(2));
		    		if(rs.getString(3)!=null)
		    		{
		    			cbtype.setSelectedIndex(1);
		    			tpomid.setText(rs.getString(3));
		    		}
		    		if(rs.getString(4)!=null)
		    		{
		    			cbtype.setSelectedIndex(2);
		    			tpomid.setText(rs.getString(4));
		    		}
		    		tdate.setText(rs.getString(5));
		    		if(rs.getString(6).equals("Cash"))
		    			cbmode.setSelectedIndex(1);
		    		if(rs.getString(6).equals("Online"))
		    			cbmode.setSelectedIndex(2);
		    		if(rs.getString(6).equals("Cheque"))
		    			cbmode.setSelectedIndex(3);
					tamount.setText(rs.getString(7));
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
			cbtype.setSelectedIndex(0);
			tpomid.setText("");
			tdate.setText("");
			cbmode.setSelectedIndex(0);
			tamount.setText("");
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
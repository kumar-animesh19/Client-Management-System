import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
public class Maintenance extends JFrame implements ActionListener
{
	JPanel p1,p2;
	JTabbedPane tp;
	JLabel maintenance,lpid,lcost,ldate,lformat,lsearch;
	JTextField tpid,tcost,tdate,tsearch;
	JButton search,save,update,fetch,reset,close;
	JTable jt;
	JScrollPane sp;
	DefaultTableModel model;
	JCheckBox cbmid,cbpid,cball;
	ButtonGroup bg;
	Connection con;
    PreparedStatement ps;
    ResultSet rs,rs1;
    String m_id,p_id,m_date,cost;
    
    Maintenance()
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
		
		maintenance=new JLabel("MAINTENANCE   DETAILS");
		maintenance.setBounds(200, 30, 350, 30);
		maintenance.setFont(new Font("Monotype corsiva",Font.BOLD,25));
		maintenance.setForeground(new Color(165, 42, 42));
		add(maintenance);
		
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
	    
	    cbmid=new JCheckBox("MAINTENANCE ID");
	    cbmid.setBounds(20, 50, 130, 15);
		cbmid.setBackground(new Color(179,242,255));
		p1.add(cbmid);
		
		cbpid=new JCheckBox("PROJECT ID");
		cbpid.setBounds(150, 50, 100, 15);
		cbpid.setBackground(new Color(179,242,255));
		p1.add(cbpid);
		
		cball=new JCheckBox("ALL PROJECT");
		cball.setBounds(260, 50, 120, 15);
		cball.setBackground(new Color(179,242,255));
		p1.add(cball);
		
		bg=new ButtonGroup();
		bg.add(cbmid);
		bg.add(cbpid);
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
		model.addColumn("MAINTENANCE ID");
		model.addColumn("PROJECT ID");
		model.addColumn("DATE");
		model.addColumn("COST");
		jt.setModel(model);
		
		sp.setVisible(false);
		
	    p2= new JPanel();
	    p2.setLayout(null);
	    p2.setBackground(new Color(179,242,255));
	    add(p2);
	    
	    tp.addTab("Insert & Update",p2);
		
		lpid=new JLabel("PROJECT ID : *");
		lpid.setBounds(180, 50, 100, 30);
		lpid.setForeground(Color.RED);
		p2.add(lpid);
		tpid=new JTextField();
		tpid.setBounds(310, 50, 70, 30);
		p2.add(tpid);
		
		ldate=new JLabel("DATE : *");
		ldate.setBounds(180, 110, 100, 30);
		ldate.setForeground(Color.RED);
		p2.add(ldate);
		tdate=new JTextField();
		tdate.setBounds(310, 110, 100, 30);
		p2.add(tdate);
		lformat=new JLabel("(dd-mm-yyyy)");
		lformat.setBounds(320, 140, 100, 15);
		lformat.setFont(new Font("Arial",Font.PLAIN,12));
		lformat.setForeground(Color.BLUE);
		p2.add(lformat);
		
		lcost=new JLabel("COST : *");
		lcost.setBounds(180, 170, 100, 30);
		lcost.setForeground(Color.RED);
		p2.add(lcost);
		tcost=new JTextField();
		tcost.setBounds(310, 170, 100, 30);
		p2.add(tcost);
	    
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
			if(cbmid.isSelected())
			{
				sp.setVisible(false);
				int flag=0;
				String row[]=new String[4];
				
				try
				{
					m_id=JOptionPane.showInputDialog(this, "Enter maintenance ID :");
					ps=con.prepareStatement("select * from maintenance where m_id='"+m_id+"'");
					rs=ps.executeQuery();
					if(rs.next())
					{
						for(int i=0;i<4;i++)
						row[i]=rs.getString(i+1);
						model.addRow(row);
						flag++;
						sp.setVisible(true);
					}
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
				String row[]=new String[4];
				
				try
				{
					p_id=JOptionPane.showInputDialog(this, "Enter project ID :");
					ps=con.prepareStatement("select * from maintenance where p_id='"+p_id+"'");
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
				ps=con.prepareStatement("Select max(m_id) from maintenance");
				rs=ps.executeQuery();
				if(rs.next())
				{
					char id[]=rs.getString(1).toCharArray();
					id[id.length-1]++;
					m_id=(String.valueOf(id));
				}
				
				rs.close();
			}
			catch(Exception ex)
			{
				m_id="M101";
			}
    		
			p_id=tpid.getText();
			m_date=tdate.getText();
			cost=tcost.getText();
			
			try
			{
				ps=con.prepareStatement("select * from maintenance where p_id='"+p_id+"' and m_date='"+m_date+"'");
				rs=ps.executeQuery();			  
				if(rs.next())
				{
					JOptionPane.showMessageDialog(this,"Data Already Exist..","Save",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					ps=con.prepareStatement("insert into maintenance values('"+m_id+"','"+p_id+"','"+m_date+"',"+cost+")");
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this,"Saved..\nMAINTENANCE ID="+m_id,"Save",JOptionPane.INFORMATION_MESSAGE);
					
					tpid.setText("");
					tdate.setText("");
					tcost.setText("");
				}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"Data could not be saved..","Save",JOptionPane.INFORMATION_MESSAGE);
			}
		}
    	
    	if(ae.getSource()==update)
		{
			p_id=tpid.getText();
			m_date=tdate.getText();
			cost=tcost.getText();
			
			try
			{
				ps=con.prepareStatement("select * from payment where m_id='"+m_id+"' and dues>0");
				rs=ps.executeQuery();
				if(rs.next())
				{
					int dues;
					ps=con.prepareStatement("select cost from maintenance where m_id='"+m_id+"'");
					rs1=ps.executeQuery();
					if(rs1.next())
					{
						dues=Integer.valueOf(rs.getString(8))+(Integer.valueOf(cost)-Integer.valueOf(rs1.getString(1)));
						ps=con.prepareStatement("update maintenance set  p_id='"+p_id+"',m_date='"+m_date+"',cost="+cost+" where m_id='"+m_id+"'");
						ps.executeUpdate();
						ps=con.prepareStatement("update payment set dues="+dues+" where pay_id='"+rs.getString(1)+"'");
						ps.executeUpdate();
					}
				}
				else
				{
					ps=con.prepareStatement("update maintenance set  p_id='"+p_id+"',m_date='"+m_date+"',cost="+cost+" where m_id='"+m_id+"'");
					ps.executeUpdate();
				}
				JOptionPane.showMessageDialog(this,"Update Successful..","Update",JOptionPane.INFORMATION_MESSAGE);
				
				tpid.setText("");
				tdate.setText("");
				tcost.setText("");
				
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
				m_id=JOptionPane.showInputDialog(this, "Enter maintenance ID :");
				ps=con.prepareStatement("select * from maintenance where m_id='"+m_id+"'");
				rs=ps.executeQuery();
				
				if(rs.next())
				{
					tpid.setText(rs.getString(2));
					tdate.setText(rs.getString(3));
					tcost.setText(rs.getString(4));
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
			tpid.setText("");
			tdate.setText("");
			tcost.setText("");
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

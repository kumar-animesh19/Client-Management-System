import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
public class Client extends JFrame implements ActionListener
{
	JPanel p1,p2;
	JTabbedPane tp;
	JLabel client,lname,lcompany,laddress,lcity,lstate,lpin,lcontact,lemail,lwebsite,lsearch;
	JTextField tname,tcompany,taddress,tcity,tstate,tpin,tcontact,temail,twebsite,tsearch;
	JButton search,save,update,fetch,reset,close;
	JTable jt;
	JScrollPane sp;
	DefaultTableModel model;
	JCheckBox cbcid,cball;
	ButtonGroup bg;
	Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String c_id,c_name,company,address,city,state,pincode,contact,email,website;
    
    Client()
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
		
		client=new JLabel("CLIENT   DETAILS");
		client.setBounds(225, 30, 250, 30);
		client.setFont(new Font("Monotype corsiva",Font.BOLD,25));
		client.setForeground(new Color(165, 42, 42));
		add(client);
		
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
		
		cbcid=new JCheckBox("CLIENT ID");
		cbcid.setBounds(80, 50, 100, 15);
		cbcid.setBackground(new Color(179,242,255));
		p1.add(cbcid);
		
		cball=new JCheckBox("ALL CLIENT");
		cball.setBounds(220, 50, 100, 15);
		cball.setBackground(new Color(179,242,255));
		p1.add(cball);
		
		bg=new ButtonGroup();
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
		model.addColumn("CLIENT ID");
		model.addColumn("CLIENT NAME");
		model.addColumn("COMPANY");
		model.addColumn("ADDRESS");
		model.addColumn("CITY");
		model.addColumn("STATE");
		model.addColumn("PINCODE");
		model.addColumn("CONTACT NO.");
		model.addColumn("EMAIL ID");
		model.addColumn("WEBSITE");
		jt.setModel(model);
		
		sp.setVisible(false);
		
		p2= new JPanel();
	    p2.setLayout(null);
	    p2.setBackground(new Color(179,242,255));
	    add(p2);
	    
	    tp.addTab("Insert & Update",p2);
		
		lname=new JLabel("CLIENT NAME : *");
		lname.setBounds(30, 30, 100, 30);
		lname.setForeground(Color.RED);
		p2.add(lname);
		tname=new JTextField();
		tname.setBounds(140, 30, 150, 30);
		p2.add(tname);
		
		lcompany=new JLabel("COMPANY :");
		lcompany.setBounds(30, 80, 100, 30);
		lcompany.setForeground(Color.RED);
		p2.add(lcompany);
		tcompany=new JTextField();
		tcompany.setBounds(140, 80, 150, 30);
		p2.add(tcompany);
		
		laddress=new JLabel("ADDRESS : *");
		laddress.setBounds(30, 130, 100, 30);
		laddress.setForeground(Color.RED);
		p2.add(laddress);
		taddress=new JTextField();
		taddress.setBounds(140, 130, 150, 30);
		p2.add(taddress);
		
		lcity=new JLabel("CITY : *");
		lcity.setBounds(30, 180, 100, 30);
		lcity.setForeground(Color.RED);
		p2.add(lcity);
		tcity=new JTextField();
		tcity.setBounds(140, 180, 150, 30);
		p2.add(tcity);
		
		lstate=new JLabel("STATE : *");
		lstate.setBounds(30, 230, 100, 30);
		lstate.setForeground(Color.RED);
		p2.add(lstate);
		tstate=new JTextField();
		tstate.setBounds(140, 230, 150, 30);
		p2.add(tstate);
		
		lpin=new JLabel("PINCODE : *");
		lpin.setBounds(330, 30, 100, 30);
		lpin.setForeground(Color.RED);
		p2.add(lpin);
		tpin=new JTextField();
		tpin.setBounds(440, 30, 150, 30);
		p2.add(tpin);
		
		lcontact=new JLabel("CONTACT NO. : *");
		lcontact.setBounds(330, 80, 100, 30);
		lcontact.setForeground(Color.RED);
		p2.add(lcontact);
		tcontact=new JTextField();
		tcontact.setBounds(440, 80, 150, 30);
		p2.add(tcontact);
		
		lemail=new JLabel("EMAIL ID :");
		lemail.setBounds(330, 130, 100, 30);
		lemail.setForeground(Color.RED);
		p2.add(lemail);
		temail=new JTextField();
		temail.setBounds(440, 130, 150, 30);
		p2.add(temail);
		
		lwebsite=new JLabel("WEBSITE :");
		lwebsite.setBounds(330, 180, 100, 30);
		lwebsite.setForeground(Color.RED);
		p2.add(lwebsite);
		twebsite=new JTextField();
		twebsite.setBounds(440, 180, 150, 30);
		p2.add(twebsite);
		
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
			if(cbcid.isSelected())
			{
				sp.setVisible(false);
				int flag=0;
				String row[]=new String[10];
				try
				{
					c_id=JOptionPane.showInputDialog(this, "Enter customer ID :");
					ps=con.prepareStatement("select * from client where c_id='"+c_id+"'");
					rs=ps.executeQuery();
					if(rs.next())
					{
						for(int i=0;i<10;i++)
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
				String row[]=new String[10];
				try
				{
					ps=con.prepareStatement("select * from client");
					rs=ps.executeQuery();
					while(rs.next())
					{
						for(int i=0; i<10; i++)
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
				ps=con.prepareStatement("Select max(c_id) from client");
				rs=ps.executeQuery();
				if(rs.next())
				{
					char id[]=rs.getString(1).toCharArray();
					id[id.length-1]++;
					c_id=(String.valueOf(id));
				}
				
				rs.close();	
			}
			catch(Exception ex)
			{
				c_id="C101";
			}
			
			c_name=tname.getText();
			company=tcompany.getText();
			address=taddress.getText();
			city=tcity.getText();
			state=tstate.getText();
			pincode=tpin.getText();
			contact=tcontact.getText();
			email=temail.getText();
			website=twebsite.getText();
			
			try
			{
				ps=con.prepareStatement("select * from client where c_name='"+c_name+"' and contact_no="+contact);
				rs=ps.executeQuery();			  
				if(rs.next())
				{
					JOptionPane.showMessageDialog(this,"Data Already Exist..","Save",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					ps=con.prepareStatement("insert into client values('"+c_id+"','"+c_name+"','"+company+"','"+address+"','"+city+"','"+state+"',"+pincode+","+contact+",'"+email+"','"+website+"')");
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this,"Saved..\nCLIENT ID="+c_id,"Save",JOptionPane.INFORMATION_MESSAGE);
					
					tname.setText("");
					tcompany.setText("");
					taddress.setText("");
					tcity.setText("");
					tstate.setText("");
					tpin.setText("");
					tcontact.setText("");
					temail.setText("");
					twebsite.setText("");
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
			c_name=tname.getText();
			company=tcompany.getText();
			address=taddress.getText();
			city=tcity.getText();
			state=tstate.getText();
			pincode=tpin.getText();
			contact=tcontact.getText();
			email=temail.getText();
			website=twebsite.getText();
			
			try
			{
				ps=con.prepareStatement("update client set c_name='"+c_name+"',company='"+company+"',address='"+address+"',city='"+city+"',state='"+state+"',pincode='"+pincode+"',contact_no="+contact+",email_id='"+email+"',website='"+website+"' where c_id='"+c_id+"'");
				ps.executeUpdate();
				JOptionPane.showMessageDialog(this,"Update Successful..","Update",JOptionPane.INFORMATION_MESSAGE);
				
				tname.setText("");
				tcompany.setText("");
				taddress.setText("");
				tcity.setText("");
				tstate.setText("");
				tpin.setText("");
				tcontact.setText("");
				temail.setText("");
				twebsite.setText("");
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
				c_id=JOptionPane.showInputDialog(this, "Enter customer ID :");
				ps=con.prepareStatement("select * from client where c_id='"+c_id+"'");
				rs=ps.executeQuery();
				
				if(rs.next())
				{
					tname.setText(rs.getString(2));
					tcompany.setText(rs.getString(3));
					taddress.setText(rs.getString(4));
					tcity.setText(rs.getString(5));
					tstate.setText(rs.getString(6));
					tpin.setText(rs.getString(7));
					tcontact.setText(rs.getString(8));
					temail.setText(rs.getString(9));
					twebsite.setText(rs.getString(10));
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
			tname.setText("");
			tcompany.setText("");
			taddress.setText("");
			tcity.setText("");
			tstate.setText("");
			tpin.setText("");
			tcontact.setText("");
			temail.setText("");
			twebsite.setText("");
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
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class Start extends JFrame
{
	JLabel l;
	static JProgressBar progressBar;
	Start()
	{
		setTitle("CLIENT MANAGEMENT SYSTEM");
		setSize(700, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		l=new JLabel(new ImageIcon(Start.class.getResource("/imgs/Start.jpg")));
		l.setBounds(0, 0, 700, 475);
		add(l);
		
		progressBar=new JProgressBar();
		progressBar.setBounds(0, 440, 685, 25);
	    progressBar.setStringPainted(true);
	    progressBar.setBackground(Color.WHITE);
	    progressBar.setForeground(Color.GREEN);
	    BasicProgressBarUI ui = new BasicProgressBarUI() {
	        protected Color getSelectionBackground() {
	            return Color.BLUE;
	        }
	        protected Color getSelectionForeground() {
	            return Color.BLACK;
	        }
	    };
	    progressBar.setUI(ui);
	    progressBar.setValue(0);
	    l.add(progressBar);
	}
	
	public static void main(String args[])
	{
		Start s=new Start();
		s.setVisible(true);
		
		int i=0;
        while( i<=100)
        {
            try
            {
                Thread.sleep(75);
                progressBar.setString("Loading. . . . . .   "+i+"%");
                progressBar.setValue(i);
                i++;
                if(i==100)
                {
                	new Login();
                	s.dispose();
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
	}
}

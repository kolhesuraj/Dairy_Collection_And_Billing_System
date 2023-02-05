package Project;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class index extends JFrame implements ActionListener {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		static JFrame LoginFrame;
		static JLabel UserId, Password,text;
		static JButton Login, reset;
		static JPasswordField pwd;
		static JTextField userName;
	public static void main(String[] args) {
		index obj = new index();
		obj.demo();
	}
	public  void demo() {
		//frame work
		LoginFrame = new JFrame("Login");
		LoginFrame.setLocation(550,250);
		LoginFrame.setSize(450,250);
		LoginFrame.setLayout(null);
		LoginFrame.setResizable(false);

		//labels user id and input
		UserId = new JLabel("User name");
		UserId.setBounds(100,40,150,50);
		LoginFrame.add(UserId);
		
		//input user id
		userName = new JTextField();
		userName.setBounds(180,55,150,20);
		LoginFrame.add(userName);
		
		//password field
		Password = new JLabel("password");
		Password.setBounds(100,80,150,50);
		LoginFrame.add(Password);
		
		//password input
		pwd = new JPasswordField();
		pwd.setBounds(180,100,150,20);
		KeyListener listner = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					verifyLogin();
						
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
		};
		pwd.addKeyListener(listner );
		LoginFrame.add(pwd);
		
		//
		text = new JLabel(" ");
		text.setBounds(120,120,200,30);
		LoginFrame.add(text);
		
		//login button
		Login = new JButton("login");
		Login.setSize(70,30);
		Login.setLocation(120,150);
		Login.addActionListener(this);
		LoginFrame.add(Login);
		
		//reset button
		reset = new JButton("reset");
		reset.setSize(70,30);
		reset.setLocation(200,150);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pwd.setText(null);
				userName.setText(null);
			}
		});
		LoginFrame.add(reset);
		
		//set visibility of frame
		LoginFrame.setVisible(true);
		LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {
		verifyLogin();
		new Page();
	}
	
	@SuppressWarnings("deprecation")
	public static void verifyLogin() {
		 String user= userName.getText();
		 String password = pwd.getText();
		 if(user.equals("") || password.equals("")) {
			 text.setText("Please enter valid login details");
			 text.setForeground(Color.RED);
		 }else {
		     connection connect = new connection();
		     Connection con = connect.connect();
		     if(con == null) {
		            text.setText("database not found");
		            text.setForeground(Color.RED);
		     }else {
				try( Statement stmt = con.createStatement()){
					 String query = "SELECT * FROM `login` WHERE binary Id ='"+user+"'AND binary Password ='"+password+"'";
						ResultSet rs = stmt.executeQuery(query);	
						//System.out.println("query run successfully");
						if (rs.next()) {
							Page pg = new Page();
							pg.mainframe( true);
							LoginFrame.setVisible(false);
						}else 
						{
							text.setText("Please enter valid login details");
							text.setForeground(Color.RED);
						}
						con.close();
				} catch (SQLException e1) {
		            text.setText("Please check login details");
		            text.setForeground(Color.RED);
		            e1.printStackTrace();
		            System.out.println("sql fail");
                }
				
		 }
		 }

	}
	
}

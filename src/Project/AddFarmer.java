package Project;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

	@SuppressWarnings("serial")
	public class AddFarmer extends JFrame implements ActionListener {
		protected static JPanel edit;
		public static void main(String[] args) {
			//new AddFarmer();
			AddFarmer gui = new AddFarmer();
			gui.addFarmer();
		
		}
		static JLabel result;
		JLabel result1;
		JButton update;
		JTextField Gid , Gname, Gmobile, Gemail;
		JTextField mobile_no;
		int i = 0;
		public Component addFarmer() {
			//JFrame frame = new JFrame();
			edit = new JPanel();
			edit.setBounds(151,0,1370,1200);
			edit.setLayout(null);
			int a=250;
			int b=50;
			
			JLabel Fname = new JLabel("Farmer's Name");
			Fname.setBounds(a-100,b,110,30);
			edit.add(Fname);
			
			final JTextField name = new JTextField(15);
			name.setBounds(a,b+7,150,20);
			edit.add(name);
			

			JLabel FMobile = new JLabel("Mobile No");
			FMobile.setBounds(a+160,b,110,30);
			edit.add(FMobile);
			
			mobile_no = new JTextField(10);
			mobile_no.setBounds(a+230,b+7,150,20);
			KeyListener keyListener = new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					if (e.getSource() == mobile_no) {
					String key = mobile_no.getText();
					int length = key.length();
					if(length > 9) {
						e.consume();
					}
					}
					if(e.getSource() == Gmobile) {
						String key = Gmobile.getText();
						int length = key.length();
						if(length > 9) {
							e.consume();
						}
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					if(e.getSource() == Gid) {
						String iD = Gid.getText();
						if(iD.length()<1) {
							Gname.setText(null);
							Gmobile.setText(null);
							Gemail.setText(null);
						}else {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
			                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
							try( Statement stmt = con.createStatement()){
								 String query = "SELECT * FROM `farmers_list` WHERE id = '"+iD+"'";
									ResultSet rs = stmt.executeQuery(query);	
									if(rs.next()) {
									String gn = rs.getString("Name");
									Gname.setText(gn);
									long mb=rs.getLong("Mobile_No");
									String mbl = String.valueOf(mb);
									Gmobile.setText(mbl);
									String gm = rs.getString("email");
									Gemail.setText(gm);
									}else {
										Gname.setText(null);
										Gmobile.setText(null);
										Gemail.setText(null);
										result1.setText("please enter valid id");
									}
									con.close();
						}
						}catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("sql fail");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					}

				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
				}

			};
			mobile_no.addKeyListener(keyListener);
			edit.add(mobile_no);
			

			JLabel Femail = new JLabel("email");
			Femail.setBounds(a+420,b,110,30);
			edit.add(Femail);
			
			final JTextField email = new JTextField(20);
			email.setBounds(a+460,b+7,150,20);
			edit.add(email);

			//
			result = new JLabel(" ");
			result.setForeground(Color.green);
			Font font = new Font("courier",Font.BOLD,14);
			result.setFont(font);
			result.setBounds(a-100,b+150,1200,20);
			
			//
			result1 = new JLabel("");
			result1.setFont(font);
			result1.setForeground(Color.black);
			result1.setBounds(a-100,b+190,1200,20);
			edit.add(result1);
			edit.add(result);
			
			JButton add = new JButton("add");
			add.setBounds(a+650,b+7,70,20);
			add.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e) {
					String email0 = email.getText();
					String regex = "^[a-zA-Z0-9_!#$%&*+/=?'{|}~^-]+(/:\\.[a-zA-Z0-9_!#$%&*+/=?'{|}~^-])*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(email0);
					 String name0 = name.getText();
					 String mobile = mobile_no.getText();
					if (matcher.matches() && mobile.length() > 9) {
						if (name0.length() <1) {
							result1.setText("Please enter name");
						}else {
						    connection connect = new connection();
				             Connection con = connect.connect();
				             if(con == null) {
				                    result.setText("database not found");
//				                    result.setForeground(Color.RED);
				             }else {
							 String email01 = email.getText();
							try( Statement stmt = con.createStatement()){
								String query = "INSERT INTO `farmers_list` ( `Name`, `Mobile_No`, `email`) "
										+ "VALUES ('"+name0+"',"+mobile+",'"+email01+"')";
									stmt.executeUpdate(query);	
									System.out.println("query run successfully");
									result.setText("record added successfully ");
									String id1 = Details.getid(name0,mobile,email01);
									result1.setText("Id: " +id1+  " 	farmers name: " + name0+ "  mobile: "  + mobile+"   email: " + email01);
									name.setText(null);
									mobile_no.setText(null);
									email.setText(null);
									i++;
									con.close();
							}
							
						 catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							System.out.println("sql fail");
						} 
						}							
					}
					}else {
						result1.setText("Please enter valid email id and mobile no.");
						
					}
				}
			});	
			edit.add(add);
			
			//update logic
			JLabel massage = new JLabel("FOR UPDATING DETAILS ENTER FARMER'S ID");
			massage.setBounds(a-100,b+35,300,30);
			edit.add(massage);
			
			//
			JLabel id = new JLabel("id");
			id.setBounds(a-100,b+60 ,50,30);
			edit.add(id);
			Gid = new JTextField();
			Gid.setBounds(a-75,b+65,50,20);
			Gid.addKeyListener(keyListener);
			edit.add(Gid);
			
			
			//
			JLabel name1 = new JLabel("name");
			name1.setBounds(a,b+60 ,50,30);
			edit.add(name1);
			Gname = new JTextField();
			Gname.setBounds(a+40,b+65,70,20);
			edit.add(Gname);
			
			//
			JLabel mobile = new JLabel("mobile_no");
			mobile.setBounds(a+120,b+60 ,70,30);
			edit.add(mobile);
			Gmobile = new JTextField();
			Gmobile.setBounds(a+185,b+65,150,20);
			Gmobile.addKeyListener(keyListener);
			edit.add(Gmobile);
			
			//
			JLabel email1 = new JLabel("email");
			email1.setBounds(a+350,b+60 ,50,30);
			edit.add(email1);
			Gemail = new JTextField();
			Gemail.setBounds(a+390,b+65,150,20);
			edit.add(Gemail);
			
			//
			update = new JButton("update");
			update.setBounds(a+550,b+65,100,20);
			edit.add(update);
			
			//adding and returning panel
			update.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String iD = Gid.getText();
					String name = Gname.getText();
					String mobile = Gmobile.getText();
					String email = Gemail.getText();
					String regex = "^[a-zA-Z0-9_!#$%&*+/=?'{|}~^-]+(/:\\.[a-zA-Z0-9_!#$%&*+/=?'{|}~^-])*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(email);
					if(iD.length() < 1 ||name.length() < 1 ||mobile.length()< 1 ||email.length() < 1) {
						result1.setText("Please add all details");
					}else if(matcher.matches()) {
					    connection connect = new connection();
			             Connection con = connect.connect();
			             if(con == null) {
			                    result.setText("database not found");
//			                    result.setForeground(Color.RED);
			             }else {
						try( Statement stmt = con.createStatement()){
							 String query = "UPDATE `farmer's_list` SET `Name`= '"+name+"', `Mobile_No` = '"+mobile+"', `email` =  '"+email+"' WHERE Id = '"+iD+"'";
							 stmt.executeUpdate(query);
							 result.setText("record updated successfully ");
							 result1.setText("farmers name: " + name+"  mobile: " + mobile+"   email: " + email);
							 Gid.setText(null);
							 Gmobile.setText(null);
							 Gname.setText(null);
							 Gemail.setText(null);
					con.close();
					}
					catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("sql fail");
				}
			             }
					
					}else {
						result.setText("Please enter a valid email id");
					}
				
				}
			});
			add(edit);
			return edit;
				}
		
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
}
	}
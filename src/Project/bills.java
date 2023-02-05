package Project;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class bills {
	static JPanel bill;
	static JLabel massage;
	static JTextField id;
	static JLabel gid,Id;
	static JTextField massage1;
	protected java.sql.Date dt2,dt3;
	protected String gh,gh3;
	private static JDatePickerImpl datePicker, datePicker1;
	private static JButton get;
	private static JLabel EndDate;
	private static JLabel StartDate;
	public Component blill() {
		// TODO Auto-generated method stub
		KeyListener listner = new KeyListener() {
			public void keyPressed(KeyEvent listner) {
				// TODO Auto-generated method stub
				
				
			}

			@Override
			public void keyTyped(KeyEvent listner) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent listner) {
				// TODO Auto-generated method stub
				if(listner.getKeyChar() >= '0' && listner.getKeyChar() <= '9'|| listner.getKeyChar() >= '.') {
					if(listner.getSource() == id) {
						String iD = id.getText();
						Details dt = new Details();
						dt.Detail(iD);
						}
					
				}
			}
		};

		
		bill = new JPanel();
		bill.setBounds(151,0,1370,1200);
		bill.setLayout(null);
		
		//
		massage = new JLabel("select start and end date for bills");
		massage.setBounds(250,10,250,20);
		bill.add(massage);
		
		//
		massage1 = new JTextField("");
		bill.add(massage1);
		
		//
		int a = 50;
		int b = 50;
		//
		Id = new JLabel("ID");
		Id.setBounds(a,b,30,20);
		bill.add(Id);
		id = new JTextField();
		id.setBounds(a+20,b,50,20);
		id.addKeyListener(listner);
		bill.add(id);
		
		//
		gid = new JLabel();
		gid.setBounds(a+20,b+50,150,20);
		bill.add(gid);
		
		//
		StartDate = new JLabel("Starting Date");
		StartDate.setBounds(a+100,b,150,20);
		bill.add(StartDate);
		//
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(a+200, b, 130, 20);
		datePicker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gh = datePicker.getJFormattedTextField().getText();
				if (gh.length() <1) {
					massage.setText("Please select starting date of bill");
				}
				}
		});
		bill.add(datePicker);	
		
		

		//
		EndDate = new JLabel("Ending Date");
		EndDate.setBounds(a+350,b,150,20);
		bill.add(EndDate);
		
		//
		UtilDateModel model1 = new UtilDateModel();
		Properties p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1,p);
		datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		datePicker1.setBounds(a+450, b, 130, 20);
		datePicker1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gh3 = datePicker1.getJFormattedTextField().getText();
				if (gh3.length() < 1) {
					massage.setText("please select Ending date of bill");
				}
				}
		});
		bill.add(datePicker1);
		
		get = new JButton("GET");
		get.setBounds(a+650,b,70,30);
		bill.add(get);
		get.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				bills.getdate(id,gh,gh3);
				bill.repaint();
				bill.revalidate();
			}
			
		});
		
		
		return bill;
	}
	protected static void Ui() {
		bill.add(massage);
		bill.add(massage1);
		bill.add(id);
		bill.add(Id);
		bill.add(StartDate);
		bill.add(EndDate);
		bill.add(gid);
		bill.add(get);
		bill.add(datePicker);
		bill.add(datePicker1);
	}
	protected static void getdate(JTextField id, String gh2, String gh3) {
		// TODO Auto-generated method stub
		bill.removeAll();
		bills.Ui();

		try {
			JLabel SNF[] = new JLabel[30];
			JLabel[] FAT = new JLabel[30];
			JLabel[] RATE = new JLabel[30];
			JLabel[] WEIGHT = new JLabel[30];
			JLabel[] TOTAL = new JLabel[30];
			JLabel[]  DATE = new JLabel[30];
			String id1 = id.getText();
			connection connect = new connection();
            Connection con = connect.connect();
            if(con == null) {
//                   text.setText("database not found");
//                   text.setForeground(Color.RED);
            }else {           	
			try( Statement stmt = con.createStatement()){
				String query = "SELECT * FROM `record` WHERE ID = '"+id1+"' AND date between '"+gh2+"' AND '"+gh3+"'";
					ResultSet rs = stmt.executeQuery(query);
					int i = 0;
					int a = 50 ,b =200;
	                float weightt = 0,totalt = 0;
					while(rs.next()) {
						//
						

						//
						int x = 50,y=150;
						JLabel[] heading = new JLabel[6];
						String[] heading1 = new String[] {"DATE","SNF","FAT","RATE","WEIGHT","AMOUNT"};
						for (int j = 0; j < 6; j++) {
							heading[j] = new JLabel(heading1[j]);
							heading[j].setBounds(x,y,70,30);
							bill.add(heading[j]);
							x=x+150;
						}
							
						//
						Date date = rs.getDate("date");
						SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
						DATE[i] = new JLabel( sf.format(date));
						DATE[i].setBounds(a,b,70,30);
						bill.add(DATE[i]);
						
						//snf[i] = rs.getFloat("snf");
		                SNF[i] = new JLabel( Float.toString(rs.getFloat("snf")));
		                SNF[i].setBounds(a+150,b,30,30);
		                bill.add(SNF[i]);
						
						//
						FAT[i] = new JLabel(Float.toString(rs.getFloat("fat")));
						FAT[i].setBounds(a+300,b,30,30);
						bill.add(FAT[i]);
						
						//
						RATE[i] = new JLabel( Float.toString(rs.getFloat("rate")));
						RATE[i].setBounds(a+450,b,30,30);
						bill.add(RATE[i]);
						
						//
						WEIGHT[i] = new JLabel( Float.toString(rs.getFloat("weigth")));
						WEIGHT[i].setBounds(a+600,b,70,30);
						bill.add(WEIGHT[i]);
						
						//
						TOTAL[i] = new JLabel( Float.toString(rs.getFloat("total")));
						TOTAL[i].setBounds(a+750,b,70,30);
						bill.add(TOTAL[i]);
						
						
						i++;
						a=50;
						b=b+50;
		                bill.repaint();
		                
		                //
							weightt = weightt+rs.getFloat("weigth");
							totalt = totalt+rs.getFloat("total");
					}
					
					String h = " Total Weight   " +Float.toString(weightt); 
					JLabel weight = new JLabel(h);
					weight.setBounds(a-20,b,150,30);
					bill.add(weight);
					
					String t = "Total Amount "+Float.toString(totalt);
					JLabel amount = new JLabel(t);
					amount.setBounds(a+150,b,150,30);
					bill.add(amount);
					
					massage1.setVisible(false);
					con.close();
					} catch (SQLException e11) {
			// TODO Auto-generated catch block
			massage1.setVisible(true);
			massage1.setText("Database connection faild!!!!");
			massage1.setSize(1500,30);
			massage1.setForeground(Color.RED);
			e11.printStackTrace();
			System.out.println("sql fail");
		} 
		}
	}finally {
	    System.out.println("Bill printed on screen");
	}
}
}

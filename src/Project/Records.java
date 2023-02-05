package Project;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Records implements ActionListener {

	protected static JPanel record;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Records rd = new Records();
		rd.record();
	}
	static JTextField id,Fat,Snf,weight;
	static JLabel rate, result;
	static String s = new String();
	public Component record() {

		// TODO Auto-generated method stub
		record = new JPanel();
		record.setBounds(151,0,1370,1200);
		record.setLayout(null);
		int x = 250;
		int y = 50;

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
				if(listner.getKeyChar() >= '0' && listner.getKeyChar() <= '9'|| listner.getKeyChar() == '.') {
					result.setText(null);
					if(listner.getSource() == id) {
						String iD = id.getText();
						Details dt = new Details();
						dt.Detail(iD,rate);
						}
					
					if(listner.getSource() == Snf) {
						Records.fatcheck();
					}
					if(listner.getSource() == weight) {
						Records.snfcheck();
					}
					
				}else {
					if(listner.getSource() == id) {
						id.setText(null);
						result.setText("character not allowed");						
					}
					if(listner.getSource() == Fat) {
						Fat.setText(null);
						result.setText("character not allowed");
					}if(listner.getSource() == Snf) {
						Snf.setText(null);
						result.setText("character not allowed");
						
					}if(listner.getSource() == weight) {
						weight.setText(null);
						result.setText("character not allowed");
					}
				}
			}
	};

	

		//
		result = new JLabel();
		result.setBounds(x-100,y+70,700,50);
		result.setFocusable(false);
		record.add(result);
		
		//
		JLabel ID = new JLabel("ID");
		ID.setBounds(x-100,y,70,30);
		record.add(ID);
		
		//
		id = new JTextField();
		id.setBounds(x-70,y+5,50,20);
		id.addKeyListener(listner);
		record.add(id);
		
		//
		JLabel fat = new JLabel("Fat");
		fat.setBounds(x , y ,70,30);
		record.add(fat);
		
		//
		Fat = new JTextField();
		Fat.setBounds(x+25,y+5,50,20);
		Fat.addKeyListener(listner);
		record.add(Fat);
		
		//
		JLabel snf = new JLabel("snf");
		snf.setBounds(x+100,y,70,30);
		record.add(snf);
		
		//
		Snf = new JTextField();
		Snf.setBounds(x+130,y+5,50,20);
		Snf.addKeyListener(listner);
		record.add(Snf);
		
		//
		JLabel Weight = new JLabel("Weight");
		Weight.setBounds(x+190,y,70,30);
		record.add(Weight);
		
		//
		weight = new JTextField();
		weight.setBounds(x+250,y+5,50,20);
		weight.addKeyListener(listner);
		record.add(weight);
		
		//
		rate = new JLabel();
		rate.setBounds(x-20,y+45,200,20);
		rate.setFocusable(false);
		record.add(rate);
		
		
		JButton addRecord = new JButton("Add Record");
		addRecord.setBounds(x+400, y, 130,30);
		addRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ID = id.getText();
				String FAT = Fat.getText();
				String SNF = Snf.getText();
				String WEIGHT = weight.getText();
				if(ID == null ||FAT == null ||SNF == null ||WEIGHT == null) {
					String Error="Please Enter all details";
					result.setText(Error);
				}else {
				    connection connect = new connection();
		             Connection con = connect.connect();
		             if(con == null) {
		                 result.setText("database not found");
//		                 result.setForeground(Color.RED);
		             }else {
					try( Statement stmt = con.createStatement()){
							float rateGot = AddRecords.rate(FAT,SNF);
							float w = Float.parseFloat(WEIGHT);
							float Total = w * rateGot;
							String total = Float.toString(Total);
							System.out.println("total: " + total);
							String query1 = "INSERT INTO `record` (`Id`, `snf`, `fat`, `rate`, `weigth`, `total`, `date`) "
									+ "VALUES ('"+ID+"', '"+SNF+"', '"+FAT+"', '"+rateGot+"', '"+WEIGHT+"', '"+total+"', current_timestamp())";
							stmt.executeUpdate(query1);
							//Sendmail.send(ID,SNF,FAT,rateGot,WEIGHT,total);
							System.out.println("query run successfully");
							id.setText(null);
							weight.setText(null);
							Fat.setText(null);
							Snf.setText(null);
							s= "id: "+ID+"   snf: "+SNF+"  fat:  "+FAT+"  weight: "+WEIGHT+"   rate: "+rateGot+"  total: "+total+"  record added successfully";
							result.setText(s);
							con.close();
					}
				 catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					result.setText("sql run failed");
					result.setForeground(Color.red);
					System.out.println("sql fail");
				}
			}
			}
			}
		});

		record.add(addRecord);
		
		return record;
	}
	protected static void snfcheck() {
		// TODO Auto-generated method stub
		String sNF = Snf.getText();
		float snf1 = Float.parseFloat(sNF);
		if(sNF == null) {
			String s = "it must have to fill";
			result.setText(s);
		}else if(snf1 < 7.9 || snf1 > 10.6) {
			String s ="snf must be between 7.9 and 10.6";
			System.out.println(s);
			result.setText(s);
			Snf.setText(null);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	protected static void fatcheck() {
		// TODO Auto-generated method stub
		String fAT = Fat.getText();
		float fat = Float.parseFloat(fAT);
		if(fAT == null) {
			String s = "it must have to fill";
			result.setText(s);
		}else if(fat < 2.9 || fat > 5.1) {
			String h ="fat must be between 2.9 and 5.0";
			result.setText(h);
			Fat.setText(null);
		}
	}

	
}

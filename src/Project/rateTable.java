package Project;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class rateTable implements ActionListener {

	static int d=135;
	static int a = 100;
	protected static JPanel rtable;
	protected static JButton set;
	protected static JRadioButton increase , decrease;
	protected static JTextField value;
    static JLabel massage;
	int flag = 0;
	private static JLabel text,by;
	public Component table() {
		// TODO Auto-generated method stub
		KeyListener listner = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9'|| e.getKeyChar() == '.') {
					massage.setText(null);
				}else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				    set();
				}
				else {
					value.setText(null);
					massage.setText("value must be integer");
				}
			}
			
		};
		rtable = new JPanel();
		rtable.setBounds(151,0,1370,1200);
		rtable.setLayout(null);
		//
		text = new JLabel("select Price type: ");
		text.setBounds(121,30,120,30);
		rtable.add(text);
		
		//
		increase = new JRadioButton("increase price");
		increase.setBounds(231,30,120,30);
		
		//
		decrease = new JRadioButton("decrease price");
		decrease.setBounds(351,30,120,30);
		ButtonGroup bg = new ButtonGroup();
		bg.add(increase);
		bg.add(decrease);
		rtable.add(increase);
		rtable.add(decrease);
		
		//
		by= new JLabel("BY :");
		by.setBounds(490,30,50,30);
		rtable.add(by);
		
		//
		value = new JTextField();
		value.setBounds(550,40,70,20);
		value.addKeyListener(listner);
		rtable.add(value);
		
		//
		set = new JButton("done");
		set.setBounds(650,30,100,30);
		set.addActionListener(this);
		rtable.add(set);
		
		//
		massage = new JLabel();
		massage.setSize(1500,30);
		rtable.add(massage);
		
		//
		rateTable.printrate();
		
		return rtable;
	}
	public static void additems() {
		rtable.add(text);
		rtable.add(massage);
		rtable.add(increase);
		rtable.add(decrease);
		rtable.add(by);
		rtable.add(value);
		rtable.add(set);
		rtable.repaint();
		rtable.revalidate();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == set) {
			set();
			}else {
				System.out.println("not done");
			}
		rateTable.printrate();
		}
		

	protected static void printrate() {
		int left =d + 30;
		int down =a- 5;
		//
		int x = d + 30;
		int Y = a - 20;
		float snf = 8.0f;
		float fat = 3.0f;
		JLabel[] Snf = new JLabel[26];
		JLabel[] Fat = new JLabel[21];
		for(int i = 0; i < 26; i++) {
			String h = String.format("%.01f",snf);
			String s = new String(h);
			Snf[i] = new JLabel(s);
			Snf[i].setBounds(x, Y,40,30);
			rtable.add(Snf[i]);
			x=x+40;
			snf = snf + 0.1f;
		}
		x= d;
		Y= a;
		for(int j = 0; j < 21; j++) {
			String h = String.format("%.01f",fat);
			String s = new String(h);
			Fat[j] = new JLabel(s);
			Fat[j].setBounds(x, Y, 40 ,30);
			rtable.add(Fat[j]);
			Y=Y+30;
			fat = fat+0.1f;
		}
		connection connect = new connection();
        Connection con = connect.connect();
        if(con == null) {
               text.setText("database not found");
               text.setForeground(Color.RED);
        }else {
				try( Statement stmt = con.createStatement()){
					 String query = "SELECT rate FROM `ratetable`";
					 massage.setText("Retriving data....");
						ResultSet rs = stmt.executeQuery(query);	
						int hr = left, g=1;
							for(int i = 0; i <= 23; i++) {
								for(int j = 1; j <= 26; j++) {
									if(!rs.next()) {
										break;
									}
									String value1 = rs.getString(g);
									System.out.println(value);
									JLabel ValueLabel = new JLabel(value1);
									ValueLabel.setBounds(hr,down,40,40);
									rtable.add(ValueLabel);
									hr = hr + 40;
								}
							    hr = left;
								down = down + 30;
							}
						
							con.close();
							massage.setText(null);
				} catch (SQLException e1) {
				// TODO Auto-generated catch block
				massage.setText("Database connection faild!!!!");
				massage.setForeground(Color.RED);
				e1.printStackTrace();
				System.out.println("sql fail");
			} 
        }

	}
	public static void set() {
	    if(increase.isSelected()) {
            String Value = value.getText();
            System.out.println(Value);
            ChangeRate ch = new ChangeRate();
            ch.increaseRate(Value);
            rtable.removeAll();
            rateTable.additems();
            value.setText(null);
            increase.setSelected(false);
            rateTable.printrate();
        }else if(decrease.isSelected()) {
            String Value = value.getText();
            System.out.println(Value);
            ChangeRate ch = new ChangeRate();
            ch.decreaseRate(Value);
            rtable.removeAll();
            rateTable.additems();
            value.setText(null);
            decrease.setSelected(false);
            rateTable.printrate();

}
	}
	}

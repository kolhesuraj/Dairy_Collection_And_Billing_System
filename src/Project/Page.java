package Project;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException; 

	public class Page extends JFrame implements ActionListener {
		private static final long serialVersionUID = 1L;
		static JPanel buttonsPanel , Label;
		public static void main( String[] arg) {
			// TODO Auto-generated method stub
			Page obj = new Page();
			obj.mainframe(s);
		}
		public static JFrame Frame;
		static boolean s= false;
		public  void mainframe(boolean b) {
			//frame work
			if(b == true) {
				
			final JFrame Frame = new JFrame("main");
			Frame.setSize(1540,829);
			Frame.setLocation(0,0);
			Frame.setLayout(null);
			
			//
			buttonsPanel = new JPanel();
			buttonsPanel.setSize(150,1200);
			buttonsPanel.setBackground(new Color(5,65,90));
			Frame.add(buttonsPanel, BorderLayout.WEST);
			
			//
			Label = new JPanel();
				BufferedImage myPicture;
				try {
//					myPicture = ImageIO.read(new File("C:\\Users\\suraj\\OneDrive\\Desktop\\Java\\DairyCollectionSystem\\src\\Project\\logo\\main-frame.png"));
					myPicture  = ImageIO.read(getClass().getResource("logo/main-frame.png"));
					JLabel picLabel = new JLabel(new ImageIcon(myPicture));				
					Label.add(picLabel);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			Label.setBounds(151,0,1370,1200);
			Label.setBackground(new Color(57,162,227));
			Frame.add(Label);
			
			//
//			Icon icon = new ImageIcon("C:\\Users\\suraj\\OneDrive\\Desktop\\Java\\DairyCollectionSystem\\src\\Project\\logo\\add-record1.jpeg");
			  Image image = null;
              try {
                image = ImageIO.read(getClass().getResource("logo/add-record1.jpeg"));
              } catch (IOException e) {
                  e.printStackTrace();
              }
			JButton Record = new JButton("new record",new ImageIcon( image));
			Record.setSize(100,100);
			Record.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Frame.getContentPane().removeAll();
					Frame.add(buttonsPanel);
					Records rd = new Records();
					Label.setVisible(false);
					Frame.add(rd.record());
					Frame.repaint();
					Frame.validate();
				}
			});
			buttonsPanel.add(Record);
			
			//
			Image icon2 = null;
            try {
              icon2 = ImageIO.read(getClass().getResource("logo/addUser1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            JButton Farmers = new JButton("Add Framers", new ImageIcon(icon2));
			Farmers.setSize(60,30);
			Farmers.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					AddFarmer mp = new AddFarmer();
					Label.setVisible(false);
					Frame.getContentPane().removeAll();
					Frame.add(buttonsPanel);
					Frame.add(mp.addFarmer());
					Frame.repaint();
					Frame.validate();
				}
			});
			buttonsPanel.add(Farmers);
			
			//
			Image icon3 = null;
            try {
              icon3 = ImageIO.read(getClass().getResource("logo/Bill1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
			JButton bills = new JButton("bills", new ImageIcon(icon3));
			bills.setSize(60,30);
			bills.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) {
					Frame.getContentPane().removeAll();
					Frame.add(buttonsPanel);
					bills bl = new bills();
					Label.setVisible(false);
					Frame.add(bl.blill());
					Frame.repaint();
					Frame.validate();
				}
			});
			buttonsPanel.add(bills);
			
			//
			Image icon4 = null;
            try {
              icon4 = ImageIO.read(getClass().getResource("logo/addUser1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
			JButton rateTable = new JButton("rate Table",new ImageIcon(icon4));
			rateTable.setSize(60,30);
			buttonsPanel.add(rateTable);
			rateTable.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) {
					Frame.getContentPane().removeAll();
					Frame.add(buttonsPanel);
					rateTable rt =new rateTable();
					Label.setVisible(false);
					Frame.add(rt.table());
					Frame.repaint();
					Frame.revalidate();
				}
			});
			
			//set visibility of frame
			Frame.setVisible(true);
			Frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			}else {
				Frame = new JFrame();
				Frame.setBounds(550,250,450,250);
				Frame.setLayout(null);
				
				JLabel massage = new JLabel("Please start from login page"); 
				massage.setBounds(120,0,350,150);
				massage.setForeground(Color.red);
				Frame.add(massage);
				Frame.setVisible(true);
				Frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				Timer t = new Timer(2000, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Frame.dispose();
					}
					
				});
				t.start();
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}	
	}


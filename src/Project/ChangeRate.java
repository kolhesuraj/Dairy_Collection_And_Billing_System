package Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangeRate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void increaseRate(String value) {
		// TODO Auto-generated method stub
	    connection connect = new connection();
        Connection con = connect.connect();
        if(con == null) {
        }else {
				try( Statement stmt = con.createStatement()){
							float  fat = 3.0f;
							float snf = 8.0f;
							for(int i = 0; i < 21; i++) {
								snf = 8.0f;
								for(int j = 0; j < 26; j++) {
									String FAT = String.valueOf(fat);
									String SNF = String.valueOf(snf);
									float rateGot = AddRecords.rate(FAT,SNF);
									System.out.println("query run successfully");
									//float value1 = Float.parseFloat(rs.getString(1));
									float value2 = Float.parseFloat(value);
									float value3 = rateGot+value2;
									String query1 = "UPDATE ratetable SET `rate` = '" +value3+ "'WHERE format(snf,2) = format("+snf+" , 2) AND format(fat,2) = format("+fat+" , 2) ";
									stmt.executeUpdate(query1);
									System.out.println("rate change ");
									snf = snf + 0.1f;
								}
								fat = fat + 0.1f;
							}
							con.close();
						}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("sql fail");
			} 
				}
	}

	public void decreaseRate(String value) {
		// TODO Auto-generated method stub
	    connection connect = new connection();
        Connection con = connect.connect();
        if(con == null) {
        }else {
				try( Statement stmt = con.createStatement()){
							float  fat = 3.0f;
							float snf = 8.0f;
							for(int i = 0; i < 21; i++) {
								snf = 8.0f;
								for(int j = 0; j < 26; j++) {
									String FAT = String.valueOf(fat);
									String SNF = String.valueOf(snf);
									float rateGot = AddRecords.rate(FAT,SNF);
									float value2 = Float.parseFloat(value);
									float value3 = rateGot-value2;
									String query1 = "UPDATE `ratetable` SET `rate` = '" +value3+ "'WHERE format(snf,2) = format("+snf+" , 2) AND format(fat,2) = format("+fat+" , 2) ";
									stmt.executeUpdate(query1);
									snf = snf + 0.1f;
								}
								fat = fat + 0.1f;
							}
							con.close();
						}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("sql fail");
			} 
				}
	}
//	public float rateget(float snf , float fat) {
//		float rate = 0;
//	connection connect = new connection();
//    Connection con = connect.connect();
//    if(con == null) {
//    }else {
//				try( Statement stmt = con.createStatement()){
////					String snf1 = String.format("%.1", snf); 
////					String fat1 = String.format("%.1", fat);
//		String quary = "SELECT `rate` FROM ratetable WHERE format(snf,2) = format("+snf+" , 2) AND format(fat,2) = format("+fat+" , 2) ";
//		ResultSet rs = stmt.executeQuery(quary);
//		String rate1 = rs.getString(1);
//		rate = Float.parseFloat(rate1);
//		System.out.println("query run successfully from rate got");
//				}
//		 }catch(SQLException e){
////			 e.printStackTrace();
//			 System.out.println("no get rTE");
//		 }
//	}
//		return rate;
//	}

}

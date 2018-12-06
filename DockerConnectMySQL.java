import java.sql.*;

public class DockerConnectMySQL {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://db:10.0.10.3:3306/baza";
//MATEUSZ ORZEŁ

   static final String USER = "morzel";
   static final String PASS = "12345";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      Class.forName("com.mysql.jdbc.Driver");

      System.out.println("Czekaj na połączenie...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      stmt = conn.createStatement();
      
stmt.executeUpdate("CREATE TABLE Tabela(id int, Dane1 varchar(255), Dane2 varchar(255));");
stmt.executeUpdate("INSERT INTO Tabela (id, Dane1, Dane2) VALUES (3, 'KJaktus', 'Kaktus2');");
stmt.executeUpdate("INSERT INTO Tabela (id, Dane1, Dane2) VALUES (7, 'Kapusniak' , 'Ogorkowa');");
ResultSet result = stmt.executeQuery("SELECT * FROM Tabela");

      while(result.next()){
         int id  = result.getInt("id");
         String dane1 = result.getString("Dane1");
         String dane2 = result.getString("Dane2");
	
		System.out.print("ID: " +id);
		System.out.print(",Dane1:" + dane1);
		System.out.print(",Dane2:" + dane2);

      }
      result.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
 }
}

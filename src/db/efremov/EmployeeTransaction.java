package db.efremov;

import java.sql.*;
import java.util.Random;

class EmployeeTransaction  {
  public static void main(String argv[]) throws SQLException {
   Connection conn=null;
   Statement stmt=null;
   
   try{ //Попытка добавить транзакционно в 2 таблицы стрОки
	conn = DriverManager.getConnection( "jdbc:derby://localhost:1527/Lesson22;create=false");   
	conn.setAutoCommit(false); 
	stmt = conn.createStatement();
	stmt.addBatch("insert into Orders " + "values(123, 'Buy','IBM',"+new Random().nextInt(200)+")"); 
	stmt.addBatch("insert into OrderDetails " + "values('JSmith', 'Broker131','2011-12-"+(new Random().nextInt(11)+1)+"')"); 
	stmt.executeBatch();
	conn.commit();
	// Transaction succeded
	}catch(SQLException e){
	   	conn.rollback();
	   	// Transaction failed
	   	e.printStackTrace();
   } catch( Exception e ) {
      System.out.println(e.getMessage()); 
      e.printStackTrace(); 
   } finally{
       // clean up the system resources
       try{     
	   stmt.close(); 
	   conn.close();  
       } catch(Exception e){
           e.printStackTrace();
       } 
   }
}
}

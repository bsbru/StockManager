/*
 * Program connects to a PEOPLE database, takes name and surname of every 
 * person and creates Person java class with names from the DB.
 * 
 */
package stockmanager;

/**
 *
 * @author Mateusz
 */
 import java.sql.Connection;  
 import java.sql.DriverManager;  
 import java.sql.ResultSet;  
 import java.sql.Statement;  

 public class ConnectSQLite 
 {  
  public static void main(String[] args) 
  {  
     Connection connection = null;  
     ResultSet resultSet = null;  
     Statement statement = null;  

     try 
     {  
         Class.forName("org.sqlite.JDBC");  
         connection = DriverManager.getConnection("jdbc:sqlite:people");  
         statement = connection.createStatement();  
         resultSet = statement.executeQuery("SELECT name, surname FROM people");  
         while (resultSet.next()) 
         {  
             Person p = new Person(resultSet.getString("name"), resultSet.getString("surname"));
             System.out.println(p.getName());
             
         }  
     } 
     catch (Exception e) 
     {  
         e.printStackTrace();  
     }
     finally 
     {  
         try 
         {  
             resultSet.close();  
             statement.close();  
             connection.close();  
         } 
         catch (Exception e) 
         {  
             e.printStackTrace();  
         }  
     }  
 }
 }
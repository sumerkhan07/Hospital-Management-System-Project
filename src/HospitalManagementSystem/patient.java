package HospitalManagementSystem;

import javax.management.Query;
import java.sql.*;
import java.util.Scanner;

public class patient {
   private Connection connection;
   private Scanner scanner;

    public patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }
    public  void addPatients(){
        System.out.println("Enter Patients name");
        String name= scanner.next();
        System.out.println("Enter Patients Age ");
        int age =scanner.nextInt();
        System.out.println("Enter Patients Gender");
         String gender=scanner.next();

         try{
             String Querey="insert into patients(name,age,gender) values(?,?,?)";
             PreparedStatement preparedStatement=connection.prepareStatement(Querey);
             preparedStatement.setString(1,name);
             preparedStatement.setInt(2,age);
             preparedStatement.setString(3,gender);

             int afrows =preparedStatement.executeUpdate();
             if (afrows >0){
                 System.out.println("patient added successfully");
             }
             else {
                 System.out.println("failed to add patient");
             }
         }catch(SQLException e){
             e.printStackTrace();
         }
    }

    public  void  viewPatients(){
        String Query="select * from patients";
        try {
        PreparedStatement preparedStatement=connection.prepareStatement(Query);
        ResultSet resultSet= preparedStatement.executeQuery();
            System.out.println("+------------+--------------------+-------------+---------+");
            System.out.println("| Patient_id | Name                | Age          | Gender ");
            System.out.println("+------------+--------------------+-------------+---------+");
       while (resultSet.next()){
           int id=resultSet.getInt("patients_id");
           String name=resultSet.getString("name");
           int age=resultSet.getInt("age");
           String gender=resultSet.getString("gender");
           System.out.printf("|%-12s | %-21s | %-14s|%-7s \n",id,name,age,gender);
           System.out.println("+------------+--------------------+-------------+---------+");

       }
        }
        catch (SQLException e){
            e.printStackTrace();

        }
    }

    public  boolean checkid(int id) {
        String Query = "select * from patients where id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

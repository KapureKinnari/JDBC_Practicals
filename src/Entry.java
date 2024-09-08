import java.sql.*;
import java.util.Scanner;
import java.util.logging.SocketHandler;

public class Entry {
    public static void main(String[] args){
        //Class.forName("TestClass");//we have to pass the checked exception because if we use throws we will not be able know what the error is.

        //for this to handel the exception we will only use try catch not using throws.
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");//this throws class not found exception will be written in catch.

            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","hr","system"); //this is used to connect with the oracle
            // database.This throws the exception of SQL exception will be handle it in catch.
           // System.out.println(connection);
            //System.out.println(connection.getMetaData().getDriverName());//for reference
    //this connection comes with various information through which we can use it.As above used to get the version.

        //create query -- for qury also we need connection object.To create query we need to write create statement.
       // connection.setAutoCommit(false);//not mandatory to use only for testing i had used..
        Statement statement = connection.createStatement();

        //SELECT QUERY

        //to take user defined using where clause in query we will use scanner class.
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter emp id");
            int eid = scanner.nextInt();

            //for name
            System.out.println("Enter name");
            String ename = scanner.next();

            //for salary
            System.out.println("Enter salary");
            double salary = scanner.nextDouble();

            ResultSet resultSet = statement.executeQuery("select * from employee");
            //ResultSet resultSet = statement.executeQuery("select * from employee where eid = " + eid);//result set always works with select query bcoz it stores multiple data only.
            // ResultSet resultSet = statement.executeQuery("select * from employee where ename = '" + name +"'");//in name as string need single quote so don't miss the single quote.
//
//            //resultset works same as iterator means it iteratoes and gives ans of all the data.
            while(resultSet.next()){//next is of type boolen so if data is readed it will return true if not then false.
                System.out.println(resultSet.getInt(1) + ":" + resultSet.getString(2) + ":" + resultSet.getDouble(3));
            }

//            //INSERT QUERY
//            int rowImpacted = statement.executeUpdate("insert into employee values("+eid+",'"+ename+"',"+salary+")");
//            if(rowImpacted > 0){
//                System.out.println("Inserted succesfully");
//            }

//            //UPDATE QUERY
            int rowImpacted = statement.executeUpdate("update employee set salary = "+salary+" where eid = "+eid);
            if(rowImpacted > 0){
                System.out.println("Updated succesfully");
            }

            //DELETE QUERY
//            int rowImpacted = statement.executeUpdate("delete from employee where eid = "+eid);
//            if(rowImpacted > 0){
//                System.out.println("Deleted succesfully");
//            }

            //connection.commit();//this will commit the query automatically when it will executed.This is
            // optional bcoz JDBC itself is autocommit.

            //connection.rollback();
            resultSet.close();
            statement.close();
            connection.close();

        }
        catch (ClassNotFoundException e){//checked exception
            e.printStackTrace();
        }
        catch (SQLException e){//checked exception
            e.printStackTrace();
        }

    }
}

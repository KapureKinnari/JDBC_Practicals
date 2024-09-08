import java.sql.*;
import java.util.Scanner;

public class PreparedStatement_Entry {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","hr","system");

            //INSERTING DATA using prepared statement
//            preparedStatement  = connection.prepareStatement("insert into employee values(?, ?, ?)");
//            preparedStatement.setInt(1,105);
//            preparedStatement.setString(2,"Kavyaa");
//            preparedStatement.setDouble(3,25000);
//            preparedStatement.executeUpdate();//update bcoz we are inserting

            //System.out.println("successfully inserted");//optional it is written to check wheater it is inserted or not.

            //BATCH PROCCESSING WITH PREPARED STATEMENT. HOW IT WORKS IS WRITTEN IN NOTES.

            preparedStatement  = connection.prepareStatement("insert into employee values(?, ?, ?)");
            Scanner scanner = new Scanner(System.in);
            int eid ;
            String eName;
            double Salary;

            //this loop is written because we want to store the data of this loop inside the batch
            for (int i=0;i<=3;i++){ //this loop will exceute 10 times and then whole 10 records will get inserted in one batch.
                System.out.println("Enter Id");
                eid = scanner.nextInt();
                System.out.println("Enter Name");
                eName = scanner.next();
                System.out.println("Enter Salary");
                Salary = scanner.nextDouble();

                preparedStatement.setInt(1, eid);
                preparedStatement.setString(2,eName);
                preparedStatement.setDouble(3,Salary);

                preparedStatement.addBatch(); //this we made the batch to add the whole data which user will pass. and it can be static also.
            }
            preparedStatement.executeBatch();//here we have written execute batch bcoz we are now not executing query else we have made
            // one batch and that we are executing so "execute batch".



            //PREPARED STATEMENT IS THE PRE COMPILED QUERY EVERY IT IS NOT COMPILED YET IT GETS COMPILED ONCE AND IT GETS EXECUTED MANY TIMES IF WE GIVE LOOP AND ALL.

//            preparedStatement = connection.prepareStatement("select * from employee");
//            preparedStatement = connection.prepareStatement("select * from employee where eid = ?");
//            preparedStatement.setInt(1,101);
//            resultSet = preparedStatement.executeQuery();

//            while (resultSet.next()){
//                System.out.println(resultSet.getInt(1)+ "\t\t" + resultSet.getString(2)+ "\t\t" + resultSet.getDouble(3));
//            }
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(resultSet != null)
                    resultSet.close();
                if(preparedStatement != null)
                    preparedStatement.close();
                if(connection != null)
                    connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}

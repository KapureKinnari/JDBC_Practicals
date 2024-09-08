import java.sql.*;
import java.util.Scanner;

public class CallableStatementEntry {
    public static void main(String[] args) {
        Connection connection = null;
       CallableStatement callableStatement = null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","hr","system");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter eid");
            int eid = scanner.nextInt();

            System.out.println("Enter ename");
            String ename = scanner.next();

            System.out.println("Enter salary");
            double salary = scanner.nextDouble();

            callableStatement = connection.prepareCall("{Call insertintoemployee(?, ?, ?)}");
            callableStatement.setInt(1,eid);
            callableStatement.setString(2,ename);
            callableStatement.setDouble(3,salary);

            callableStatement.execute();//to execute the prepare statement we will use it

        /************************************************************************************/

            callableStatement = connection.prepareCall("{ ? = call getSalary(?)}");
            callableStatement.setInt(2,104);//here we are giving the index and 2nd 104 is the id for which we want ot get the date.
            callableStatement.registerOutParameter(1,Types.DOUBLE);//this will receive the data in parameter i.e out parameter and store
            // init that data will be used in future.Here where we have written 1 i.e the first ? means in that 1st ? the data will get filled.and second is the type of datatypes we need to return.
            //always use registerParameter before execute
            callableStatement.execute();

            System.out.println(callableStatement.getDouble(1));//this will get us the data which is store inside 1st ?.
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(callableStatement != null)
                    callableStatement.close();
                if(connection != null)
                    connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}

import java.sql.*;

public class StatementEntry {
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");

            connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","hr","system");

            //statement = connection.createStatement();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);   //here we will be making resultset scrollable,readonly,updatable.Detailed explained in notebook.

            resultSet = statement.executeQuery("select t.* from employee t");    //here t. means alias whenever we write concur-update we have to give alisa to compiler(JDBC).

            while (resultSet.next()){
                System.out.println(resultSet.getInt(1)+ "\t\t" + resultSet.getString(2)+ "\t\t" + resultSet.getDouble(3));
            }


            //HOW TO USE WITH BATCH PROCCESSING SO SAME LIKE PREPARED STATEMENT ONLY INSIDE LOOP WE CAN WRITE AS BELOW
//            for (int i=0;i<=3;i++){ //this loop will exceute 10 times and then whole 10 records will get inserted in one batch.
//                System.out.println("Enter Id");
//                eid = scanner.nextInt();
//                System.out.println("Enter Name");
//                eName = scanner.next();
//                System.out.println("Enter Salary");
//                Salary = scanner.nextDouble();
//                Statement.addBatch("insert into employee values("+eid+",'"+ename+"',"+salary+")") ;//this we made the batch to add the whole data which user will pass. and it can be static also.
//            }
                //statement.executeBatch();



            //by using resultset type scrollable we will use the previous function.
//            resultSet.previous();
//            System.out.println(resultSet.getInt(1)+ "\t\t" + resultSet.getString(2)+ "\t\t" + resultSet.getDouble(3));

//            resultSet.absolute(1);
//            System.out.println(resultSet.getInt(1)+ "\t\t" + resultSet.getString(2)+ "\t\t" + resultSet.getDouble(3));

              resultSet.absolute(3);
              resultSet.updateObject(3,25000);
              resultSet.updateRow();      //this will be excuted means here the update query will run
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
                if(statement != null)
                    statement.close();
                if(connection != null)
                    connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}

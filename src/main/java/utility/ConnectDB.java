package utility;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {
    /*
   This Java code defines a class called ConnectDB that contains methods to connect
   to a MySQL database and a MongoDB database. The class contains several static variables,
   including a Connection object called connect, a Statement object called statement,
   a PreparedStatement object called ps, and a ResultSet object called resultSet.

  The connectToSqlDatabase() method establishes a connection to a MySQL database using
  the driver, URL, username, and password specified in a properties file accessed via
  the Utility class. The connectToMongoDB() method connects to a MongoDB database using
  the MongoClient class and the specified server and port.

  The getTableColumnData() method takes a SQL query and a column name as input and returns
  a list of values from that column. The main method of the class uses this method to retrieve
  a list of emails from a table called "cred" and prints out the first email in the list.
     */

    public static Connection connect = null;
    public static Statement statement = null;
    public static PreparedStatement ps = null;
    public static ResultSet resultSet = null;

    public static Connection connectToSqlDatabase() {
        String driverClass = Utility.getProperties().getProperty("MYSQLJDBC.driver");
        String url = Utility.getProperties().getProperty("MYSQLJDBC.url");
        String userName = Utility.getProperties().getProperty("MYSQLJDBC.userName");
        String password = Utility.getProperties().getProperty("MYSQLJDBC.password");
        /*
       This code is accessing the properties file via the Utility class, and using
       the getProperties() method to retrieve an instance of the properties file.
       Then it is using the getProperty() method to retrieve the specific property
       values for the MySQL JDBC driver class, the URL of the database, the username,
       and the password. These values are used to establish a connection to the MySQL
       database in the connectToSqlDatabase() method.
         */
        try {
            Class.forName(driverClass);
            connect = DriverManager.getConnection(url,userName,password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Database is connected");
        return connect;
    }

    public static MongoDatabase mongoDatabase = null;

    public MongoDatabase connectToMongoDB() {
        MongoClient mongoClient = new MongoClient("" , 27017);
        mongoDatabase = mongoClient.getDatabase("");
        System.out.println("Database Connected");

        return mongoDatabase;
    }

    public static List<String> getTableColumnData(String query, String columnName) {
        List<String> list = new ArrayList<>();
        try {
            statement = connectToSqlDatabase().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                list.add(resultSet.getString(columnName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    /*
   This code defines the getTableColumnData() method, which takes in a SQL query and a column name as input.
   It creates a new ArrayList called list that will be used to store the column data retrieved from the query.
   The method first calls the connectToSqlDatabase() method to establish a connection to the MySQL database,
   and then creates a new statement object using the connection. Next it use the statement to execute
    the provided query, which returns a ResultSet object containing the query results.
   Then it uses a while loop to iterate through each row of the result set and for each row it adds the value
   of the specified column from that row to the list using the ResultSet.getString() method. Once all rows have
   been processed, the method returns the list containing the column data.
   If any SQLException occurs while executing the query, it will throw a new runtime exception with the caught
   exception as the cause.
     */

    public static void main(String[] args) throws SQLException {
        List<String> emails = getTableColumnData("select * from cred;","email");
        System.out.println(emails.get(0));
    }
    /*
    This code defines the main method of the ConnectDB class. It calls the getTableColumnData() method passing a query
     "select * from cred;" and column name "email", which retrieves a list of all the emails from the cred table.
    Then it prints the first email of the list using the get(0) method on the emails list.
    It also throws SQLException, in case any exception occurs while executing the query in getTableColumnData method.
    It's important to note that this code is not complete and will not run as it is, as it is missing important details
    such as the properties file being accessed, the database and table names, and the MongoDB server and port.
     */
}

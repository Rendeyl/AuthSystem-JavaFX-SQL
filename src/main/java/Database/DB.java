package Database;

import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.Properties;

public class DB {

        private static String url = "jdbc:mysql://localhost:3306/myDB";
        private static String user = "root";
        private static String password = "Soloflow";

        public static Connection createConnection() throws SQLException{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }catch (ClassNotFoundException e){
                System.out.println("Class not found");
                e.printStackTrace();
            }
            return DriverManager.getConnection(url, user, password);
        }

        public static void printAll(){
            String req = "SELECT * FROM players";

            try {
                Connection conn = createConnection();
                Statement statement = conn.createStatement();
                ResultSet data = statement.executeQuery(req);

                while(data.next()){
                    String firstName = data.getString("first_name");

                    System.out.println(firstName);
                }
            }catch (SQLException e){
                System.out.println("SQL error");
                e.printStackTrace();
            }
        }

        public static String deyl(int finding){
            String req = "SELECT * FROM players";

            try{
                Connection conn = createConnection();
                Statement statement = conn.createStatement();
                ResultSet data = statement.executeQuery(req);

                while (data.next()){
                    int current_num = data.getInt("jersey_number");
                    String name = data.getString("first_name");

                    if(current_num == finding){
                        return name;
                    }
                }

            }catch (SQLException e){
                System.out.println("SQL error");
                e.printStackTrace();
            }
            return null;
        }

        public static boolean checkUser(String username){

            String req = "SELECT username FROM users WHERE BINARY username = ?";

            try {
                Connection conn = createConnection();
                PreparedStatement statement = conn.prepareStatement(req);
                statement.setString(1, username);
                ResultSet data = statement.executeQuery();

                return data.next();

            }catch (SQLException e){
                System.out.println("SQL error");
                e.printStackTrace();
            }

            return false;
        }

        public static boolean matchPassword(String user, String pass){

            String req = "SELECT * FROM users WHERE username = ? AND password = ? ";

            try {
                Connection conn = createConnection();
                PreparedStatement statement = conn.prepareStatement(req);
                statement.setString(1, user);
                statement.setString(2, pass);
                ResultSet data = statement.executeQuery();

                return data.next();

            }catch (SQLException e){
                System.out.println("SQL error");
                e.printStackTrace();
            }

            return false;
        }
}


package Database;

import com.mysql.jdbc.Driver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Properties;

public class DB {

        public static String hashPasswords(String password){
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = md.digest(password.getBytes());

                StringBuilder sb = new StringBuilder();
                for (byte b : hashBytes) {
                    sb.append(String.format("%02x", b));
                }
                return sb.toString();

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Hashing algorithm not found", e);
            }
        }

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

                String hashedPassword = hashPasswords(pass);

                statement.setString(1, user);
                statement.setString(2, hashedPassword);
                ResultSet data = statement.executeQuery();

                return data.next();

            }catch (SQLException e){
                System.out.println("SQL error");
                e.printStackTrace();
            }

            return false;
        }

        public static Boolean checkIfUserAlreadyExist(String username){

            String req = "SELECT * FROM users WHERE username = ?";

            try{
                Connection conn = createConnection();
                PreparedStatement statement = conn.prepareStatement(req);
                statement.setString(1, username);
                ResultSet data = statement.executeQuery();

                if(data.next()) return true;

            }catch (SQLException e){
                System.out.println("SQL error");
                e.printStackTrace();
            }

            return false;
        }

        public static void addAccount(String username, String password){
            String req = "INSERT INTO users (username, password) VALUES(?, ?)";
            String hashedPassword = hashPasswords(password);
            try{
                Connection conn = createConnection();
                PreparedStatement statement = conn.prepareStatement(req);
                statement.setString(1, username);
                statement.setString(2, hashedPassword);
                statement.executeUpdate();
            }catch (SQLException e){
                System.out.println("SQL erroraaaa");
                e.printStackTrace();
            }
        }
}


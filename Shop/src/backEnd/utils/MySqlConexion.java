package backEnd.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConexion {
    public static Connection getConectar() {
        Connection cn=null;
        try {
            String url,user,pass;
            //carga de la clase que se encuentra en el jar "mysql-connector-java-8.0.21.jar"
            Class.forName("com.mysql.cj.jdbc.Driver");
            url="jdbc:mysql://localhost:3306/DB_PROYECTO_2022?serverTimezone=UTC";
            user="sirius";
            pass="mysql";
            //establacer la conexión a la BD.
            cn= DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return cn;
    }
}

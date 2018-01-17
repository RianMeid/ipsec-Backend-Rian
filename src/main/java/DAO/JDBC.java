package DAO;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.Getter;
import lombok.extern.java.Log;
import lombok.val;




@Log
public class JDBC {
    @Getter
    private Connection connection;


    public JDBC() {
        try {
            /**
             * Hiermee wordt de PostgreSQL driver geladen.
             */
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","password");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /**Hierin wordt de gebruikersnaam en het wachtwoord van de database opgeslagen.
         * @param gebruikersnaam: De username voor de database connectie
         * @param wachtwoord: het wachtwoord voor de database connectie
         * @param url: de connectie URL voor de database connectie
         */



        /**
         * Hierin wordt de database connectie opgezet en wordt de metadata getoond in de console
         */



    }


}

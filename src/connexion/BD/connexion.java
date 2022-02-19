package connexion.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;







public class connexion {

	
private static Connection cnx;
	
	public static Connection getConnection() throws SQLException{
		try{
			Class.forName(variables.DRIVER);
			cnx = (Connection)DriverManager.getConnection("jdbc:mysql://"+variables.SERVEUR+":"+variables.PORT+"/"+variables.NOMBD+variables.SSL,variables.USER,variables.MOTDEPASS);
	
		}catch(Exception e) {
            SQLException sqlE = new SQLException(e.getMessage());
            sqlE.initCause(e);
            throw sqlE;

		}
	if (cnx!= null) {
				
			} else {
				System.out.println("Failed to make connection!");
			}	
		return cnx;
	
	}
}

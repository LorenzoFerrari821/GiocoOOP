package gioco;
import java.awt.Color;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConnectionDB {
	protected Statement statement;
	protected Connection connection;
	private ResultSet rs;
	Date data;
	private JPanel pnlerror;
	private PreparedStatement prestatement;
	public ConnectionDB() throws ClassNotFoundException, SQLException {        //costruttore che crea la connessione con il database "salvataggi.db"
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection(
				"jdbc:sqlite:Salvataggi.db");  
		statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		statement.setQueryTimeout(30);  
	}
	public void  closeConnection(){
		if (connection != null)
			try {	

				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	   
	}
	public int init(String nome, String tutorial, String difficolta, String mappa, String civilta) throws SQLException {
		try {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Salvataggi (Data VARCHAR(15),NomeGiocatore VARCHAR(30),Tutorial CHAR(2),Difficoltà VARCHAR(10),Mappa VARCHAR(10),Civiltà VARCHAR(20),Turno INTEGER,"
					+ "Epoca VARCHAR(20),Oro INTERGER,Materiali INTEGER,Punti_Ricerca INTEGER)");  //Creiamo la tabella se non esiste
			prestatement = connection.prepareStatement("INSERT INTO Salvataggi VALUES(?,?,?,?,?,?,0,'Classica',0,0,0)");    //Permette di specificare i parametri col punto di domanda

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.now();
			prestatement.setString(1,localDate.toString() );
			prestatement.setString(2,nome);
			prestatement.setString(3,tutorial);
			prestatement.setString(4,difficolta);
			prestatement.setString(5, mappa);
			prestatement.setString(6, civilta);
			prestatement.executeUpdate();        //Adesso esegue
		} catch (SQLException e) {
			e.printStackTrace();
			pnlerror = new JPanel();
			pnlerror.setBackground(Color.WHITE);
			JOptionPane.showMessageDialog(pnlerror, "Si è verificato un errore inaspettato "
					+ "nella creazione della partita.",
					"Errore", JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		return 1;
	}
	public ResultSet executeQuery() throws SQLException {
		return statement.executeQuery("SELECT * FROM Salvataggi ORDER BY Data DESC"); //Ritorna una lista di tutti i salvataggi ordinata per data
	}
	public void elimina(int index) {
		try {
			rs=this.executeQuery();
			while(rs.getRow()!=index)
				rs.next();                              
			//Siamo arrivati alla linea da eliminare, adesso eliminiamo dal database la linea con quei dati
			prestatement = connection.prepareStatement("DELETE FROM Salvataggi WHERE (Data = ? AND NomeGiocatore = ? AND Tutorial = ? AND Difficoltà = ? AND Mappa = ?"
					+ "AND Civiltà = ? AND Turno = ? AND Epoca = ? AND Oro = ? AND Materiali = ? AND Punti_ricerca = ?)"); 
			prestatement.setString(1, rs.getString("Data"));
			prestatement.setString(2,rs.getString("NomeGiocatore"));
			prestatement.setString(3,rs.getString("Tutorial"));
			prestatement.setString(4,rs.getString("Difficoltà"));
			prestatement.setString(5, rs.getString("Mappa"));
			prestatement.setString(6, rs.getString("Civiltà"));
			prestatement.setInt(7, rs.getInt("Turno"));
			prestatement.setString(8, rs.getString("Epoca"));
			prestatement.setInt(9, rs.getInt("Oro"));
			prestatement.setInt(10, rs.getInt("Materiali"));
			prestatement.setInt(11, rs.getInt("Punti_ricerca"));
			prestatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pnlerror = new JPanel();
			pnlerror.setBackground(Color.WHITE);
			JOptionPane.showMessageDialog(pnlerror, "Errore durante la cancellazione del salvataggio",
					"Errore", JOptionPane.ERROR_MESSAGE);
		}

	}
}

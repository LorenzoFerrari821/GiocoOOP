package gioco;
import java.awt.Color;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConnectionDB {
	private Statement statement;
	private Connection connection;
	private ResultSet rs;
	private int codice;
	private JPanel pnlerror;
	private PreparedStatement prestatement;
	public ConnectionDB() throws ClassNotFoundException, SQLException {        //costruttore che crea la connessione con il database "salvataggi.db"
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection(
				"jdbc:sqlite:Salvataggi.db");  
		statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		statement.setQueryTimeout(30);  
	}
	public void  closeConnection(){  //Chiude la connessione al DB
		if (connection != null)
			try {	
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	   
	}
	public void crea() throws SQLException{
		try {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Salvataggi (Data VARCHAR(15),NomeGiocatore VARCHAR(30),Tutorial CHAR(2),Difficoltà VARCHAR(10),Mappa VARCHAR(10),Civiltà VARCHAR(20),Turno INTEGER,"
					+ "Epoca VARCHAR(20),Oro INTERGER,Materiali INTEGER,Punti_Ricerca INTEGER,Codice INTEGER primary key)");  //Creiamo la tabella solo se non esiste
		} catch (SQLException e) {
			e.printStackTrace();
			pnlerror = new JPanel();
			pnlerror.setBackground(Color.WHITE);
			JOptionPane.showMessageDialog(pnlerror,Global.getLabels("e1"),Global.getLabels("e2"), JOptionPane.ERROR_MESSAGE);
		}
	}
	public void creaTabellaPartita() throws SQLException{
		try {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS StringaPartita (Stringa VARCHAR(1000),Codice INTEGER,FOREIGN KEY(Codice) REFERENCES Salvataggi(Codice) ON DELETE CASCADE)");  //Creiamo la tabella solo se non esiste
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int init(String nome, String tutorial, String difficolta, String mappa, String civilta, int codice) throws SQLException {
		try {
			prestatement = connection.prepareStatement("INSERT INTO Salvataggi VALUES(?,?,?,?,?,?,0,'Classica',0,0,0,?)");    //Permette di specificare i parametri col punto di domanda in seguito
			//Settiamo i parametri
			LocalDate localDate = LocalDate.now();
			prestatement.setString(1,localDate.toString() );
			prestatement.setString(2,nome);
			prestatement.setString(3,tutorial);
			prestatement.setString(4,difficolta);
			prestatement.setString(5, mappa);
			prestatement.setString(6, civilta);
			prestatement.setInt(7, codice);
			prestatement.executeUpdate();        //Adesso eseguiamo
		} catch (SQLException e) {
			e.printStackTrace();
			pnlerror = new JPanel();
			pnlerror.setBackground(Color.WHITE);
			JOptionPane.showMessageDialog(pnlerror,Global.getLabels("e1"),Global.getLabels("e2"), JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		return 1;
	}
	public void creaSalvataggioStringa(String stringa,int codice){
		try {
			prestatement = connection.prepareStatement("INSERT INTO StringaPartita VALUES(?,?)"); 
			prestatement.setString(1,stringa);
			prestatement.setInt(2,codice);;
			prestatement.executeUpdate();        //Adesso eseguiamo
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public ResultSet executeQuery() throws SQLException {
		return statement.executeQuery("SELECT * FROM Salvataggi ORDER BY Data DESC"); //Ritorna una lista di tutti i salvataggi ordinata per data
	}
	public ResultSet executeQuery2() throws SQLException {
		return statement.executeQuery("SELECT * FROM StringaPartita"); 
	}
	public ResultSet getCodiceMax() throws SQLException{

		return statement.executeQuery("SELECT ifnull(MAX(Codice),-1) FROM Salvataggi");    //Ritorna il massimo codice utilizzato.Se nullo,ritorniamo -1
	}
	public void elimina(int index) { //Cerchiamo il salvataggio alla linea specificata.Leggiamo il codice di quella linea e poi la eliminiamo
		try {
			rs=this.executeQuery();
			while(rs.getRow()!=index)
				rs.next();                              
			prestatement = connection.prepareStatement("DELETE FROM Salvataggi WHERE (Codice = ?)"); 
			prestatement.setInt(1, rs.getInt("Codice"));
			prestatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pnlerror = new JPanel();
			pnlerror.setBackground(Color.WHITE);
			JOptionPane.showMessageDialog(pnlerror, Global.getLabels("e8"),Global.getLabels("e2"), JOptionPane.ERROR_MESSAGE);
		}
	}
	public String caricaStringa(int index){  //Cerchiamo il salvataggio alla linea specificata,leggiamo il codice, andiamo nella tabella delle stringhe e preleviamo la stringa associata
		try {
			rs=this.executeQuery();
			while(rs.getRow()!=index)
				rs.next();    
			codice=rs.getInt("Codice");
			rs=this.executeQuery2();
			while(rs.getInt("Codice")!=codice)
				rs.next();
			return rs.getString("Stringa");  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;   //In caso di errore
	}

}

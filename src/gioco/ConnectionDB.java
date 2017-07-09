package gioco;
import java.awt.Color;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Classe che gestisce il collegamento fra gioco e SQLite per salvare e caricare le partite.
 * @author Werther e Lorenzo
 *
 */
public class ConnectionDB {
	private Statement statement;
	private Connection connection;
	private ResultSet rs;
	private int codice;
	private JPanel pnlerror;
	private PreparedStatement prestatement;
	
	/**
	 * crea la connessione con il database "salvataggi.db"
	 * @throws ClassNotFoundException eccezione di DB
	 * @throws SQLException eccezione di DB
	 */
	public ConnectionDB() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection(
				"jdbc:sqlite:Salvataggi.db");  
		statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		statement.setQueryTimeout(30);  
	}
	
	/**
	 * Chiude la connessione con il DB
	 */
	public void  closeConnection(){
		if (connection != null)
			try {	
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	   
	}
	
	/**
	 * Crea la tabella salvataggi sul DB se non esiste
	 * @throws SQLException eccezione di DB
	 */
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
	
	/**
	 * Crea tabella partita sul DB se non esiste
	 * @throws SQLException eccezione di DB
	 */
	public void creaTabellaPartita() throws SQLException{
		try {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS StringaPartita (Stringa VARCHAR(1000),Codice INTEGER,FOREIGN KEY(Codice) REFERENCES Salvataggi(Codice) ON DELETE CASCADE)");  //Creiamo la tabella solo se non esiste
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carica i valori presi dalla GUINuovaPartita per inserirli nel DB
	 * @param nome Nome del giocatore
	 * @param tutorial Selettore tutorial si/no
	 * @param difficolta Difficoltà di gioco
	 * @param mappa Mappa di gioco
	 * @param civilta Civiltà giocatore
	 * @param codice Codice
	 * @return Ritorna 1 se l'aggiunta riesce con successo, -1 altrimenti
	 * @throws SQLException eccezione di DB
	 */
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
	
	/**
	 * Crea il salvataggio della stringa
	 * @param stringa Stringa situazione partita
	 * @param codice Codice
	 */
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
	
	/**
	 * Ritorna una lista di tutti i salvataggi ordinata per data
	 * @return Lista di salvataggi
	 * @throws SQLException Eccezione di DB
	 */
	public ResultSet executeQuery() throws SQLException {
		return statement.executeQuery("SELECT * FROM Salvataggi ORDER BY Data DESC");
	}
	
	/**
	 * Ritorna la stringa situazione di gioco
	 * @return Stringa situazione di gioco
	 * @throws SQLException Eccezione di DB
	 */
	public ResultSet executeQuery2() throws SQLException {
		return statement.executeQuery("SELECT * FROM StringaPartita"); 
	}
	
	/**
	 * Preleva il massimo codice utilizzato.Se nullo,ritorniamo -1
	 * @return Ritorna il massimo codice utilizzato.Se nullo,ritorniamo -1
	 * @throws SQLException eccezione di DB
	 */
	public ResultSet getCodiceMax() throws SQLException{

		return statement.executeQuery("SELECT ifnull(MAX(Codice),-1) FROM Salvataggi");
	}
	
	/**
	 * Cerchiamo il salvataggio alla linea specificata.Leggiamo il codice di quella linea e poi la eliminiamo
	 * @param index Indice di linea
	 */
	public void elimina(int index) {
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
	
	/**
	 * Cerchiamo il salvataggio alla linea specificata,leggiamo il codice, 
	 * andiamo nella tabella delle stringhe e preleviamo la stringa associata
	 * @param index Indice di linea
	 * @return Ritorna la stringa associata al codice di indice 'index'
	 */
	public String caricaStringa(int index){
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

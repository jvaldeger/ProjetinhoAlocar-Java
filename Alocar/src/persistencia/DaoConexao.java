package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.Diversos;

public class DaoConexao {
	private static DaoConexao instancia = null;
	private Connection con;
	private String servidor;
	private String bD;
	private String usuario;
	private String senha;
	private String url;

	private DaoConexao() {
		this.con = null;
		this.servidor = "localhost";
		this.bD = "BdConsumidor";
		this.usuario = "root";
		this.senha = "root";
		final String Driver = "com.mysql.jdbc.Driver";

		url = "jdbc:mysql://" + servidor + "/" + bD;
		url += "?createDatabaseIfNotExist=true";
		url += "&user=" + usuario + "&password=" + senha;

		try {
			Class.forName(Driver).newInstance();
			con = DriverManager.getConnection(url);

		} catch (ClassNotFoundException | IllegalAccessException
				| InstantiationException | SQLException e) {

			Diversos.mostrarDados("Erro de conexão" + e.getMessage(),
					"Exemplo com BD", false);

		}
	}

	public synchronized static DaoConexao getInstancia() {
		if (instancia == null)
			instancia = new DaoConexao();
		return instancia;

	}

	public static void setInstancia(DaoConexao instancia) {
		DaoConexao.instancia = instancia;
	}

	public Connection getCon() {
		if (con == null)
			getInstancia();
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

}

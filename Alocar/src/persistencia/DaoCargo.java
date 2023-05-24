package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.Cargo;

public class DaoCargo implements DaoBasico {

	public DaoCargo() {
		String inst = "CREATE TABLE IF NOT EXISTS Cargo"
				+ "(Codigo INT NOT NULL" + ", Descricao VARCHAR(45) NOT NULL"
				+ ", PRIMARY KEY (Codigo)"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.execute();
			}
			DaoConexao.getInstancia().setCon(con);

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public boolean incluir(Object b) {

		boolean result = true;
		Cargo bA = (Cargo) b;
		String inst = "Insert Into Cargo";
		inst += "(Codigo, Descricao) ";
		inst += "values(?, ?)";

		try {

			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {

				pS.setInt(1, bA.getCodigo());
				pS.setString(2, bA.getDescricao());
				pS.execute();
			}

			DaoConexao.getInstancia().setCon(con);
		} catch (SQLException e) {

			result = false;
			throw new RuntimeException(e.getMessage());
		}
		return (result);

	}

	@Override
	public boolean alterar(Object b) {

		boolean result = true;
		Cargo bA = (Cargo) b;
		String inst = "Update Cargo set Descricao = ? ";
		inst += "where Codigo = ?";

		try {

			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {

				pS.setString(1, bA.getDescricao());
				pS.setInt(2, bA.getCodigo());
				pS.execute();
			}

			DaoConexao.getInstancia().setCon(con);
		} catch (SQLException e) {

			result = false;
			throw new RuntimeException(e.getMessage());
		}
		return (result);

	}

	@Override
	public boolean excluir(Object b) {

		boolean result = true;
		Cargo bA = (Cargo) b;
		String inst = "Delete From Cargo where Codigo = ?";
		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.setInt(1, bA.getCodigo());
				pS.execute();
			}
			DaoConexao.getInstancia().setCon(con);
		} catch (SQLException e) {
			result = false;
			throw new RuntimeException(e.getMessage());
		}
		return (result);
	}

	@Override
	public Object busca(int codigo, int nada) {
		String inst = "Select * from Cargo where Codigo = ?";
		Cargo bA = null;
		ResultSet rS;
		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.setInt(1, codigo);
				rS = pS.executeQuery();
				DaoConexao.getInstancia().setCon(con);
				if (rS.next()) {
					bA = new Cargo();
					bA.setCodigo(rS.getInt("Codigo"));
					bA.setDescricao(rS.getString("Descricao"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return (bA);
	}

	@Override
	public List<Object> carrega() {
		String inst = "Select * From Cargo order by Descricao";
		List<Object> lista = new ArrayList<>();
		ResultSet rS;
		Object o;

		try {
			try (PreparedStatement pS = DaoConexao.getInstancia().getCon()
					.prepareStatement(inst)) {
				rS = pS.executeQuery(inst);
				DaoConexao.getInstancia().setCon(
						DaoConexao.getInstancia().getCon());
				if (rS != null)
					while (rS.next()) {
						o = busca(rS.getInt("Codigo"), 0);
						lista.add(o);
					}
				pS.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return (lista);
	}
}
package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.Alocar;

public class DaoAlocar implements DaoBasico {

	public DaoAlocar() {
	}
	
	@Override
	public boolean incluir(Object o) {
		boolean result = true;
		Alocar aL = (Alocar) o;
		String inst = "Insert Into Alocar";
		inst += "(Numero, Contrato, Data, periodo, salario, financiado) ";
		inst += "values(?, ?, ?, ?, ?, ?)";

		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.setInt(1, aL.getNumero());
				pS.setString(2, aL.getContrato() == 'D' ? "Diarista"
						: aL.getContrato() == 'A' ? "Autônomo"
								: "Celetista");
				pS.setDate(3, (Date) aL.getData());
				pS.setInt(4, aL.getPeriodo());
				pS.setFloat(5, aL.getSalario());
				pS.setBoolean(6, aL.isFinanciado());
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
	public boolean alterar(Object o) {
		boolean result = true;
		Alocar aL = (Alocar) o;
		String inst = "Update Alocar set Contrato = ?, Data = ?, ";
		inst += "periodo = ?, salario = ?, financiado = ?";
		inst += "where Numero = ?";
		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.setString(1, aL.getContrato() == 'D' ? "Diarista"
						: aL.getContrato() == 'A' ? "Autônomo"
								: "Celetista");
				pS.setDate(2, (Date) aL.getData());
				pS.setInt(3, aL.getPeriodo());
				pS.setFloat(4, aL.getSalario());
				pS.setBoolean(5, aL.isFinanciado());
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
	public boolean excluir(Object o) {
		boolean result = true;
		Alocar aL = (Alocar) o;
		String inst = "Delete From Alocar where Numero = ?";

		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.setInt(1, aL.getNumero());
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
	public Object busca(int numero, int nada) {
		String inst = "Select * from Alocar where Numero = ?";
		Alocar aL = null;
		ResultSet rS;

		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.setInt(1, numero);
				rS = pS.executeQuery();
				DaoConexao.getInstancia().setCon(con);
				if (rS.next()) {
					aL = new Alocar();
					aL.setNumero(rS.getInt("Numero"));
					aL.setContrato(rS.getString("Contrato").charAt(0));
					aL.setData(rS.getDate("Data"));
					aL.setPeriodo(rS.getInt("Periodo"));
					aL.setSalario(rS.getFloat("Salario"));
					aL.setFinanciado(rS.getBoolean("Financiado"));
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());

		}
		return (aL);
	}
	
	@Override
	public List<Object> carrega() {
		String inst = "Select * From Alocar order by Nome";
		List<Object> lista = new ArrayList<>();
		ResultSet rS;
		Object o;
		try {

			try (PreparedStatement pS = DaoConexao.getInstancia().getCon()
					.prepareStatement(inst)) {
				rS = pS.executeQuery(inst);
				DaoConexao.getInstancia().setCon(
						DaoConexao.getInstancia().getCon());
				if (rS != null) {
					while (rS.next()) {
						o = busca(rS.getInt("Numero"), 0);
						lista.add(o);

					}
					pS.close();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());

		}
		return (lista);
	}
	
}

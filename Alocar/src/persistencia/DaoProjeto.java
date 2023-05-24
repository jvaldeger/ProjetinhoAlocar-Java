package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.Projeto;

public class DaoProjeto implements DaoBasico {

	public DaoProjeto() {
		
		String inst = "CREATE TABLE IF NOT EXISTS Projeto"
				+ "(Identificacao INT NOT NULL" + ", Definicao VARCHAR(100) NOT NULL"
				+ ", PRIMARY KEY (Identificacao)"
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
	public boolean incluir(Object o) {
		boolean result = true;
		Projeto Fn = (Projeto) o;
		String inst = "Insert Into Projeto";
		inst += "(Identificacao, Definicao) ";
		inst += "values(?, ?)";

		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.setInt(1, Fn.getIdentificacao());
				pS.setString(2,Fn.getDefinicao());
				pS.execute();

			}
			DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());

		} catch (SQLException e) {
			result = false;
			throw new RuntimeException(e.getMessage());

		}
		return (result);
	}
	
	@Override
	public boolean alterar(Object o) {
		boolean result = true;
		Projeto Fn = (Projeto) o;
		String inst = "Update Projeto set Definicao = ? where Identificacao = ?";
		try{
			try(PreparedStatement pS
					= DaoConexao.getInstancia().getCon().prepareStatement(inst)){
				pS.setString(1,Fn.getDefinicao());
				pS.setInt(2, Fn.getIdentificacao());

				pS.execute();
			}
			DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
		}catch(SQLException e){
			result = false;
			throw new RuntimeException(e.getMessage());
		}
			return(result);
	}
	
	@Override
	public Object busca(int codigo, int nada) {
		String inst = "Select * from Projeto where Identificacao = ?";
		Projeto Fn = null;
		ResultSet rS; 
		try{
			try(PreparedStatement pS
					= DaoConexao.getInstancia().getCon().prepareStatement(inst)){
				pS.setInt(1, codigo);
				rS = pS.executeQuery();
				DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
				if(rS.next()){
					Fn = new Projeto();
					Fn.setIdentificacao(rS.getInt("Identificacao"));
					Fn.setDefinicao(rS.getString("Definicao"));
				}
			}
		
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		return (Fn);
	}
	
	@Override
	public boolean excluir(Object o) {
		boolean result = true;
		Projeto Fn = (Projeto) o;
		String inst = "Delete From Projeto where Identificacao = ?";
		try{
			try(PreparedStatement pS
					= DaoConexao.getInstancia().getCon().prepareStatement(inst)){
				pS.setInt(1, Fn.getIdentificacao());
				pS.execute();
			}
			DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
		}
		catch(SQLException e){
			result = false;
			throw new RuntimeException(e.getMessage());
		}
		return(result);	
	}
	
	@Override
	public List<Object> carrega() {
		String inst = "Select * from Projeto order by Definicao";
		List<Object> lista = new ArrayList<>();
		ResultSet rS; 
		Object o;
		try{
			try(PreparedStatement pS
					= DaoConexao.getInstancia().getCon().prepareStatement(inst)){
				rS = pS.executeQuery();
				DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
				if(rS != null){
					while (rS.next()){
						o = busca(rS.getInt("Identificacao"), 0);
						lista.add(o);
					}
					pS.close();
				}
			}
		
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
			return (lista);
	}
	
}

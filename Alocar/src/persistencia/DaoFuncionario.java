package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.Funcionario;

public class DaoFuncionario implements DaoBasico  {

	public DaoFuncionario() {
			
			String inst = "CREATE TABLE IF NOT EXISTS Funcionario"
					+ "(Matricula INT NOT NULL" + ", Nome VARCHAR(40) NOT NULL"
					+ ", PRIMARY KEY (Matricula)"
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
			Funcionario Fn = (Funcionario) o;
			String inst = "Insert Into Funcionario";
			inst += "(Matricula, Nome) ";
			inst += "values(?, ?)";
	
			try {
				Connection con = DaoConexao.getInstancia().getCon();
				try (PreparedStatement pS = con.prepareStatement(inst)) {
					pS.setInt(1, Fn.getMatricula());
					pS.setString(2,Fn.getNome());
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
			Funcionario Fn = (Funcionario) o;
			String inst = "Update Funcionario set Nome = ? ";
			inst += "where Matricula = ?";
			try{
				try(PreparedStatement pS
						= DaoConexao.getInstancia().getCon().prepareStatement(inst)){
					pS.setString(1,Fn.getNome());
					pS.setInt(2, Fn.getMatricula());

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
			String inst = "Select * from Funcionario where Matricula = ?";
			Funcionario Fn = null;
			ResultSet rS; 
			try{
				try(PreparedStatement pS
						= DaoConexao.getInstancia().getCon().prepareStatement(inst)){
					pS.setInt(1, codigo);
					rS = pS.executeQuery();
					DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
					if(rS.next()){
						Fn = new Funcionario();
						Fn.setMatricula(rS.getInt("Matricula"));
						Fn.setNome(rS.getString("Nome"));
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
			Funcionario Fn = (Funcionario) o;
			String inst = "Delete From Funcionario where Matricula = ?";
			try{
				try(PreparedStatement pS
						= DaoConexao.getInstancia().getCon().prepareStatement(inst)){
					pS.setInt(1, Fn.getMatricula());
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
			String inst = "Select * from Funcionario order by Nome";
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
							o = busca(rS.getInt("Matricula"), 0);
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

package controle;

import java.util.List;

import persistencia.DaoAlocar;
import persistencia.DaoBasico;
import persistencia.DaoCargo;
import persistencia.DaoFuncionario;
import persistencia.DaoProjeto;

public class ControleGeral implements ControleBasico {
	private DaoBasico dG;

	public ControleGeral(int op) { // construtor
		if(op==1){
			this.dG = new DaoAlocar();
		}
		else if(op==2){
			this.dG = new DaoFuncionario();
		}
		else if(op==3){
			this.dG = new DaoCargo();
		}
		else if(op==4){
			this.dG = new DaoProjeto();
		}

	}

	@Override
	public boolean setManipular(Object o, char tarefa) {
		boolean ok = false;
		if (dG instanceof DaoBasico)
			switch (tarefa) {
			case 'A':
				ok = (dG.alterar(o));
				break;
			case 'E':
				ok = (dG.excluir(o));
				break;
			case 'I':
				ok = (dG.incluir(o));

			}
		return ok;
	}

	@Override
	public Object getBusca(int numero, int nada) {
		Object o = null;
		if (dG instanceof DaoBasico)
			o = dG.busca(numero, 0);
		return (o);
	}

	@Override
	public List<Object> lista() {
	List<Object> lista;	
	lista = null;
	if (dG instanceof DaoBasico)
		lista = dG.carrega();
	
		return lista;
	}

}

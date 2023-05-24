package controle;

import java.util.List;

public interface ControleBasico {
	public boolean setManipular(Object o, char tarefa);

	public Object getBusca(int iD1, int iD2);

	List<Object> lista();

}

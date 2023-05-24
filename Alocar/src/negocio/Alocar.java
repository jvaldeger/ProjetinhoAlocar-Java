package negocio;

import negocio.Cargo;
import java.util.Date;

public class Alocar {

	private int numero;
	private char contrato;
	private Date data;
	private int periodo;
	private float salario;
	private boolean financiado;
	private Cargo cargo;
	private Funcionario funcionario;
	private Projeto projeto;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public char getContrato() {
		return contrato;
	}

	public void setContrato(char contrato) {
		this.contrato = contrato;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public boolean isFinanciado() {
		return financiado;
	}

	public void setFinanciado(boolean financiado) {
		this.financiado = financiado;
	}

	public float salarioReal() {

		float salario = getSalario();

		switch (contrato) {

		case 'D':

			if (isFinanciado()) {
				if (periodo > 6) {
					salario += salario * 1.22 / 100;
				}
				if (periodo >= 4) {
					salario += salario * 1.05 / 100;
				}
			} else {
				if (periodo >= 10) {
					salario += salario * 1.11 / 100;
				}
			}
			break;

		case 'A':

			if (isFinanciado()) {
				if (periodo >= 15) {
					salario += salario * 2.55 / 100;
				}
			} else {
				if (periodo > 20) {
					salario -= salario * 1.88 / 100;
				}
			}
			break;

		case 'C':

			if (cargo.equals("Gerente") || cargo.equals("Engenheiro(a)")) {
				salario += salario * 1.01 / 100;
			}

		}

		return salario;
	}

}

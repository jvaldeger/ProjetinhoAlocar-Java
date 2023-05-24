package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;







import negocio.Funcionario;
import util.Diversos;
import controle.ControleBasico;
import controle.ControleGeral;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JFrmFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField jTxtMatricula;
	private JLabel lblMatricula;
	private JLabel lblNome;
	private JTextField jTxtNome;
	private ControleBasico bF;
	private final String titulo;
	private JButton jBtnIncluir;
	private JButton jBtnAlterar;
	private JButton jBtnExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrmFuncionario frame = new JFrmFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public JFrmFuncionario() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
		        limpar();
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				jTxtNome.requestFocusInWindow();
			}
		});
		titulo = "Cadastrar Funcionarios";
		this.bF = new ControleGeral(2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jTxtMatricula = new JTextField();
		jTxtMatricula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				 pesquisa();
			}
		});
		jTxtMatricula.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtMatricula
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtMatricula.setColumns(10);
		jTxtMatricula.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		jTxtMatricula.setBounds(98, 29, 326, 25);
		contentPane.add(jTxtMatricula);

		lblMatricula = new JLabel("Matricula:");
		lblMatricula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMatricula.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblMatricula.setBounds(10, 34, 78, 15);
		contentPane.add(lblMatricula);

		lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblNome.setBounds(10, 71, 77, 15);
		contentPane.add(lblNome);

		jTxtNome = new JTextField();
		jTxtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTxtNome.setText(jTxtNome.getText().toUpperCase());
			}
		});
		jTxtNome.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtNome.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC,
				13));
		jTxtNome.setColumns(10);
		jTxtNome.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		jTxtNome.setBounds(98, 65, 326, 25);
		contentPane.add(jTxtNome);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(10, 112, 414, 124);
		contentPane.add(panel);

		jBtnIncluir = new JButton("Incluir");
		jBtnIncluir.setForeground(new Color(0, 0, 0));
		jBtnIncluir.setBackground(new Color(255, 255, 255));
		jBtnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrarDados('I');
			}
		});
		jBtnIncluir
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnIncluir.setBounds(12, 23, 84, 25);
		panel.add(jBtnIncluir);

		JButton jBtnLimpar = new JButton("Limpar");
		jBtnLimpar.setBackground(new Color(255, 255, 255));
		jBtnLimpar.setForeground(new Color(0, 0, 0));
		jBtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		jBtnLimpar
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnLimpar.setBounds(81, 72, 90, 25);
		panel.add(jBtnLimpar);

		JButton jBtnSair = new JButton("Sair");
		jBtnSair.setBackground(new Color(255, 255, 255));
		jBtnSair.setForeground(new Color(0, 0, 0));
		jBtnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja finalizar", titulo))
					dispose();
			}
		});
		jBtnSair.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnSair.setBounds(225, 72, 90, 25);
		panel.add(jBtnSair);

		jBtnAlterar = new JButton("Alterar");
		jBtnAlterar.setBackground(new Color(255, 255, 255));
		jBtnAlterar.setForeground(new Color(0, 0, 0));
		jBtnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja alterar?", titulo))
				cadastrarDados('A');
			}
		});
		jBtnAlterar
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnAlterar.setBounds(164, 23, 84, 25);
		panel.add(jBtnAlterar);

		jBtnExcluir = new JButton("Excluir");
		jBtnExcluir.setBackground(new Color(255, 255, 255));
		jBtnExcluir.setForeground(new Color(0, 0, 0));
		jBtnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja excluir?", titulo))
					cadastrarDados('E');
			}
		});
		jBtnExcluir
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnExcluir.setBounds(294, 23, 84, 25);
		panel.add(jBtnExcluir);
	}

	private void cadastrarDados(char opcao) {
		String resp = "";
		if (jTxtMatricula.getText().isEmpty() || jTxtNome.getText().isEmpty())
			resp = "Favor digitar os dados";
		else {
		Funcionario o = new Funcionario();
			o.setMatricula(Integer.parseInt(jTxtMatricula.getText()));
			o.setNome(jTxtNome.getText());

			if (!bF.setManipular(o, opcao)) {
				resp = "Problemas ao "
						+ (opcao == 'A' ? "atualizar"
								: opcao == 'E' ? "remover" : " inserir")
						+ " os dados do funcionario " + o.getMatricula();
				jBtnAlterar.setEnabled(false);
				jBtnExcluir.setEnabled(false);
				jBtnIncluir.setEnabled(false);
			} else {
				resp = "O Funcionario " + o.getMatricula();
				switch (opcao) {
				case 'A':
					resp += "\nfoi atualizado(a)";
					break;
				case 'E':
					resp += "\nfoi removido(a)";
					limpar();
					jBtnAlterar.setEnabled(false);
					jBtnExcluir.setEnabled(false);
					break;
				case 'I':
					resp += "\nfoi inserido(a)";
					jBtnAlterar.setEnabled(true);
					jBtnExcluir.setEnabled(true);
					jBtnIncluir.setEnabled(false);
				}
				resp += " com sucesso";

			}
		}
		Diversos.mostrarDados(resp, titulo,
				(resp.charAt(0) != 'F' && resp.charAt(0) != 'P'));
	}

	private void limpar() {
		JTextField txt[] = { jTxtMatricula, jTxtNome };
		for (JTextField t : txt)
			t.setText("");
		jTxtMatricula.setEditable(true);
		JButton jBtn[] = { jBtnAlterar, jBtnExcluir, jBtnIncluir };
		for (JButton btn : jBtn)
			btn.setEnabled(false);
		jTxtNome.requestFocusInWindow();
	}
	
	private void carregaObjetos(Funcionario b) {
		jTxtMatricula.setText(String.valueOf(b.getMatricula()));
        jTxtNome.setText(b.getNome());  
	}
	
	private void pesquisa() {
		int codigo;
        Funcionario b;
        if (!Diversos.testaNum(jTxtMatricula.getText(), titulo))
            jTxtMatricula.setText(""); // converter texto para numero
        else if (!Diversos.intervalo(Integer.parseInt(jTxtMatricula.getText()), 0 ,0 , titulo))
                 jTxtMatricula.setText(""); //testar se é maior que zero
             else {
                   codigo = Integer.parseInt(jTxtMatricula.getText());
                   Object o = bF.getBusca(codigo,0);
                   if (o == null) {
                      jBtnIncluir.setEnabled(true);
                      Diversos.mostrarDados("Funcionario " + codigo + " inexistente", titulo, true);
                   }    
                   else {
                        b = (Funcionario) o;
                        carregaObjetos(b); 
                        jBtnAlterar.setEnabled(true);
                        jBtnExcluir.setEnabled(true);
                  } 
                  jTxtMatricula.setEditable(false);
            }
	}
	
}

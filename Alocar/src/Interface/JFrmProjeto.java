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

import negocio.Projeto;
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

public class JFrmProjeto extends JFrame {

	private JPanel contentPane;
	private JTextField jTxtIdentificacao;
	private JLabel lbliDentificacao;
	private JLabel lblDefinicao;
	private JTextField jTxtDefinicao;
	private ControleBasico bP;
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
					JFrmProjeto frame = new JFrmProjeto();
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
	public JFrmProjeto() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
		        limpar();
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				jTxtDefinicao.requestFocusInWindow();
			}
		});
		titulo = "Cadastrar Projetos";
		this.bP = new ControleGeral(4);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jTxtIdentificacao = new JTextField();
		jTxtIdentificacao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				 pesquisa();
			}
		});
		jTxtIdentificacao.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtIdentificacao
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtIdentificacao.setColumns(10);
		jTxtIdentificacao.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		jTxtIdentificacao.setBounds(106, 29, 318, 25);
		contentPane.add(jTxtIdentificacao);

		lbliDentificacao = new JLabel("identifica\u00E7\u00E3o:");
		lbliDentificacao.setHorizontalAlignment(SwingConstants.RIGHT);
		lbliDentificacao.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lbliDentificacao.setBounds(0, 34, 99, 15);
		contentPane.add(lbliDentificacao);

		lblDefinicao = new JLabel("Defini\u00E7\u00E3o:");
		lblDefinicao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDefinicao
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblDefinicao.setBounds(10, 71, 77, 15);
		contentPane.add(lblDefinicao);

		jTxtDefinicao = new JTextField();
		jTxtDefinicao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTxtDefinicao.setText(jTxtDefinicao.getText().toUpperCase());
			}
		});
		jTxtDefinicao.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtDefinicao.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC,
				13));
		jTxtDefinicao.setColumns(10);
		jTxtDefinicao.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		jTxtDefinicao.setBounds(106, 65, 318, 25);
		contentPane.add(jTxtDefinicao);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel.setBackground(new Color(135, 206, 235));
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
		jBtnLimpar.setForeground(new Color(0, 0, 0));
		jBtnLimpar.setBackground(new Color(255, 255, 255));
		jBtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		jBtnLimpar
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnLimpar.setBounds(84, 72, 90, 25);
		panel.add(jBtnLimpar);

		JButton jBtnSair = new JButton("Sair");
		jBtnSair.setForeground(new Color(0, 0, 0));
		jBtnSair.setBackground(new Color(255, 255, 255));
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
		jBtnAlterar.setForeground(new Color(0, 0, 0));
		jBtnAlterar.setBackground(new Color(255, 255, 255));
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
		jBtnExcluir.setForeground(new Color(0, 0, 0));
		jBtnExcluir.setBackground(new Color(255, 255, 255));
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
		if (jTxtIdentificacao.getText().isEmpty() || jTxtDefinicao.getText().isEmpty())
			resp = "Favor digitar os dados";
		else {
			Projeto b = new Projeto();
			b.setIdentificacao(Integer.parseInt(jTxtIdentificacao.getText()));
			b.setDefinicao(jTxtDefinicao.getText());

			if (!bP.setManipular(b, opcao)) {
				resp = "Problemas ao "
						+ (opcao == 'A' ? "atualizar"
								: opcao == 'E' ? "remover" : " inserir")
						+ " os dados do projeto " + b.getDefinicao();
				jBtnAlterar.setEnabled(false);
				jBtnExcluir.setEnabled(false);
				jBtnIncluir.setEnabled(false);
			} else {
				resp = "O Projeto " + b.getDefinicao();
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
		JTextField txt[] = { jTxtIdentificacao, jTxtDefinicao };
		for (JTextField t : txt)
			t.setText("");
		jTxtIdentificacao.setEditable(true);
		JButton jBtn[] = { jBtnAlterar, jBtnExcluir, jBtnIncluir };
		for (JButton btn : jBtn)
			btn.setEnabled(false);
		jTxtDefinicao.requestFocusInWindow();
	}
	
	private void carregaObjetos(Projeto b) {
		jTxtIdentificacao.setText(String.valueOf(b.getIdentificacao()));
        jTxtDefinicao.setText(b.getDefinicao());  
	}
	
	private void pesquisa() {
		int codigo;
        Projeto b;
        if (!Diversos.testaNum(jTxtIdentificacao.getText(), titulo))
            jTxtIdentificacao.setText(""); // converter texto para numero
        else if (!Diversos.intervalo(Integer.parseInt(jTxtIdentificacao.getText()), 0 ,0 , titulo))
                 jTxtIdentificacao.setText(""); //testar se é maior que zero
             else {
                   codigo = Integer.parseInt(jTxtIdentificacao.getText());
                   Object o = bP.getBusca(codigo,0);
                   if (o == null) {
                      jBtnIncluir.setEnabled(true);
                      Diversos.mostrarDados("Projeto " + codigo + " inexistente", titulo, true);
                   }    
                   else {
                        b = (Projeto) o;
                        carregaObjetos(b); 
                        jBtnAlterar.setEnabled(true);
                        jBtnExcluir.setEnabled(true);
                  } 
                  jTxtIdentificacao.setEditable(false);
            }
	}
	
}

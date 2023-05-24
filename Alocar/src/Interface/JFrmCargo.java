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







import negocio.Cargo;
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

public class JFrmCargo extends JFrame {

	private JPanel contentPane;
	private JTextField jTxtCodigo;
	private JLabel lblCodigo;
	private JLabel lblDescricao;
	private JTextField jTxtDescricao;
	private ControleBasico bC;
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
					JFrmCargo frame = new JFrmCargo();
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
	public JFrmCargo() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
		        limpar();
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				jTxtDescricao.requestFocusInWindow();
			}
		});
		titulo = "Cadastrar Cargos";
		this.bC = new ControleGeral(3);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jTxtCodigo = new JTextField();
		jTxtCodigo.setBackground(new Color(255, 255, 255));
		jTxtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				 pesquisa();
			}
		});
		jTxtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtCodigo
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtCodigo.setColumns(10);
		jTxtCodigo.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		jTxtCodigo.setBounds(98, 29, 326, 25);
		contentPane.add(jTxtCodigo);

		lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setForeground(new Color(0, 0, 0));
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblCodigo.setBounds(10, 34, 68, 15);
		contentPane.add(lblCodigo);

		lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescricao
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblDescricao.setBounds(10, 71, 77, 15);
		contentPane.add(lblDescricao);

		jTxtDescricao = new JTextField();
		jTxtDescricao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTxtDescricao.setText(jTxtDescricao.getText().toUpperCase());
			}
		});
		jTxtDescricao.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtDescricao.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC,
				13));
		jTxtDescricao.setColumns(10);
		jTxtDescricao.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		jTxtDescricao.setBounds(98, 65, 326, 25);
		contentPane.add(jTxtDescricao);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel.setBackground(new Color(135, 206, 235));
		panel.setBounds(10, 112, 414, 124);
		contentPane.add(panel);

		jBtnIncluir = new JButton("Incluir");
		jBtnIncluir.setBackground(new Color(255, 255, 255));
		jBtnIncluir.setForeground(new Color(0, 0, 0));
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
		if (jTxtCodigo.getText().isEmpty() || jTxtDescricao.getText().isEmpty())
			resp = "Favor digitar os dados";
		else {
			Cargo b = new Cargo();
			b.setCodigo(Integer.parseInt(jTxtCodigo.getText()));
			b.setDescricao(jTxtDescricao.getText());

			if (!bC.setManipular(b, opcao)) {
				resp = "Problemas ao "
						+ (opcao == 'A' ? "atualizar"
								: opcao == 'E' ? "remover" : " inserir")
						+ " os dados do cargo " + b.getDescricao();
				jBtnAlterar.setEnabled(false);
				jBtnExcluir.setEnabled(false);
				jBtnIncluir.setEnabled(false);
			} else {
				resp = "O Cargo " + b.getDescricao();
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
		JTextField txt[] = { jTxtCodigo, jTxtDescricao };
		for (JTextField t : txt)
			t.setText("");
		jTxtCodigo.setEditable(true);
		JButton jBtn[] = { jBtnAlterar, jBtnExcluir, jBtnIncluir };
		for (JButton btn : jBtn)
			btn.setEnabled(false);
		jTxtDescricao.requestFocusInWindow();
	}
	
	private void carregaObjetos(Cargo b) {
		jTxtCodigo.setText(String.valueOf(b.getCodigo()));
        jTxtDescricao.setText(b.getDescricao());  
	}
	
	private void pesquisa() {
		int codigo;
        Cargo b;
        if (!Diversos.testaNum(jTxtCodigo.getText(), titulo))
            jTxtCodigo.setText(""); // converter texto para numero
        else if (!Diversos.intervalo(Integer.parseInt(jTxtCodigo.getText()), 0 ,0 , titulo))
                 jTxtCodigo.setText(""); //testar se é maior que zero
             else {
                   codigo = Integer.parseInt(jTxtCodigo.getText());
                   Object o = bC.getBusca(codigo,0);
                   if (o == null) {
                      jBtnIncluir.setEnabled(true);
                      Diversos.mostrarDados("Cargo  " + codigo + " inexistente", titulo, true);
                   }    
                   else {
                        b = (Cargo) o;
                        carregaObjetos(b); 
                        jBtnAlterar.setEnabled(true);
                        jBtnExcluir.setEnabled(true);
                  } 
                  jTxtCodigo.setEditable(false);
            }
	}
	
}

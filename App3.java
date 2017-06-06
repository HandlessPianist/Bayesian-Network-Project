package Projecto;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({ "serial", "unused" })
public class App3 extends JFrame {

	public JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public App3() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setDefaultCloseOperation(App2.HIDE_ON_CLOSE);
		
		JTextField projnameField = new JTextField(10);
		projnameField.setBounds(0, 14, 450, 20);
		JTextField nField = new JTextField(5);
		nField.setBounds(0, 48, 450, 20);

		JFileChooser inputfile = new JFileChooser();
		inputfile.setBounds(0, 184, 450, 245);
		inputfile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		inputfile.setControlButtonsAreShown(false);
		
		
		//Butoes
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

		JLabel lblIdDoPaciente = new JLabel("Vector Tempo Actual");
		lblIdDoPaciente.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblIdDoPaciente.setBounds(0, 0, 102, 14);
		myPanel.add(lblIdDoPaciente);
		myPanel.add(projnameField);

		JLabel lblValo = new JLabel("Variável medida");
		lblValo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblValo.setBounds(0, 34, 102, 14);
		myPanel.add(lblValo);
		myPanel.add(nField);

		JLabel lblSelecioneARede = new JLabel("Valor Variável Medida");
		lblSelecioneARede.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSelecioneARede.setBounds(120, 170, 194, 14);
		myPanel.add(lblSelecioneARede);
		
		JTextField valor = new JTextField(5);
		myPanel.add(valor);
		
		JLabel label_1 = new JLabel("Selecione a Rede de Bayes pretendida:");
		label_1.setAlignmentX(0.5f);
		myPanel.add(label_1);
		myPanel.add(inputfile);
		

		int result = JOptionPane.showConfirmDialog(
		    null, myPanel, "Inserir Dados", JOptionPane.OK_CANCEL_OPTION);
		
		
		if(result == JOptionPane.OK_OPTION){

		//Variáveis que a malta faz input
		int varprev = Integer.parseInt(nField.getText());
		int valorvar = Integer.parseInt(valor.getText());
		String actual = (projnameField.getText());
		
		//Converter o vector de string para int[]
		String[] items = actual.replaceAll("\\[", "").replaceAll("\\]", "").split(",");

		int[] results = new int[items.length];

		for (int i = 0; i < items.length; i++) {
		    try {
		        results[i] = Integer.parseInt(items[i]);
		    } catch (NumberFormatException nfe) {};
		}
		
		File file = inputfile.getSelectedFile();
		
		DBN net;
		try {
			net = SerializationUtil.deserialize(file.getName());
			double lol = DBN.prob(net, results, varprev, valorvar);
	        FileChooser.playSound();
	        JOptionPane.showMessageDialog(null, "A probabilidade de a variável "+Integer.toString(varprev)+" ter o valor "+ Integer.toString(valorvar)+" é: " +Double.toString(lol), "Operação Concluída", JOptionPane.PLAIN_MESSAGE);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				UIManager.put("swing.boldMetal", Boolean.FALSE);
					App2 frame = new App2();
					frame.setVisible(true);
			        frame.setDefaultCloseOperation(App2.EXIT_ON_CLOSE);
				}
				
		});
	}*/
}

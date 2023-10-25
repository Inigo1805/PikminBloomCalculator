package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import clases.Champinon.TipoChampi;
import clases.Champinon;
import clases.Champinon.Tamanho;
import clases.Champinon.champinonInexistente;
import clases.Champinon.estrellasIncorrectas;

import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Ventana principal de la aplicacion, donde se 
 * introducen los datos
 * @author Inigo Aramburu Valdepeñas iav839@alumnos.unican.es
 */
public class VentanaPrincipal {

	private JFrame cuadroPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.cuadroPrincipal.setVisible(true);
				} catch (Exception excepcion) {
					excepcion.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		cuadroPrincipal = new JFrame();
		cuadroPrincipal.setResizable(false);
		cuadroPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/Resources/icon.png")));
		cuadroPrincipal.setTitle("Pikmin Bloom Shroom Battle Calculator");
		cuadroPrincipal.setBounds(100, 100, 600, 400);
		cuadroPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		cuadroPrincipal.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JComboBox<TipoChampi> cbTipoChampi = new JComboBox<TipoChampi>();
		cbTipoChampi.setModel(new DefaultComboBoxModel<TipoChampi>(TipoChampi.values()));
		cbTipoChampi.setBounds(71, 79, 100, 22);
		panel.add(cbTipoChampi);
		
		JLabel lblTipoChampi = new JLabel("Tipo de champi\u00F1\u00F3n:");
		lblTipoChampi.setBounds(68, 54, 100, 14);
		panel.add(lblTipoChampi);
		
		JLabel lblTamanhoChampi = new JLabel("Tama\u00F1o de champi\u00F1\u00F3n:");
		lblTamanhoChampi.setBounds(236, 54, 111, 14);
		panel.add(lblTamanhoChampi);
		
		JComboBox<Tamanho> cbTamanho = new JComboBox<Tamanho>();
		cbTamanho.setModel(new DefaultComboBoxModel<Tamanho>(Tamanho.values()));
		cbTamanho.setBounds(242, 79, 100, 22);
		panel.add(cbTamanho);
		
		JLabel lblEstrellas = new JLabel("Estrellas:");
		lblEstrellas.setBounds(415, 54, 100, 14);
		panel.add(lblEstrellas);
		
		JComboBox<String> cbEstrellas = new JComboBox<String>();
		cbEstrellas.setModel(new DefaultComboBoxModel<String>(new String[] {"2 estrellas", "3 estrellas", "4 estrellas"}));
		cbEstrellas.setBounds(413, 79, 100, 22);
		panel.add(cbEstrellas);
		
		JLabel lblTituloDatos = new JLabel("Datos del champi\u00F1\u00F3n");
		lblTituloDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloDatos.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblTituloDatos.setVerticalAlignment(SwingConstants.TOP);
		lblTituloDatos.setBounds(193, 11, 197, 32);
		panel.add(lblTituloDatos);
		
		JLabel lblPoderNecesarioText = new JLabel("Poder necesario:");
		lblPoderNecesarioText.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblPoderNecesarioText.setBounds(213, 198, 156, 22);
		panel.add(lblPoderNecesarioText);
		
		JLabel lblResultado = new JLabel("---");
		lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setBounds(193, 231, 197, 32);
		panel.add(lblResultado);
		
		JButton btnCalcular = new JButton("Calcular poder");
		btnCalcular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnCalculoPoderClicked(cbTipoChampi, cbTamanho, cbEstrellas, lblResultado);
			}
		});
		btnCalcular.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCalcular.setBounds(213, 144, 156, 43);
		panel.add(btnCalcular);
		
		
		JButton btnCalculoPikmin = new JButton("Calcular Pikmin");
		btnCalculoPikmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnCalculoPikminMouseClicked(cbTipoChampi, cbTamanho, cbEstrellas);
			}
		});
		btnCalculoPikmin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCalculoPikmin.setBounds(213, 274, 156, 43);
		panel.add(btnCalculoPikmin);
	}
	
	/**
	 * Calcula el poder necesario para conseguir el objetivo
	 * propuesto y lo muestra en un panel.
	 * @param cbTipo tipo del champinon atacado
	 * @param cbTamanho tamanho del champinon atacado
	 * @param cbEstrellas estrellas a conseguir en el ataque
	 * @param panel por el que mostrar el resultado del calculo
	 */
	private void btnCalculoPoderClicked(JComboBox<TipoChampi> cbTipo,
			JComboBox<Tamanho> cbTamanho, JComboBox<String> cbEstrellas, JLabel panel) {
		try {
			TipoChampi tipo = (TipoChampi) cbTipo.getSelectedItem();
			Tamanho tamanho = (Tamanho) cbTamanho.getSelectedItem();
			int estrellas = cbEstrellas.getSelectedIndex() + 2;
			Champinon champinon = new Champinon(tipo , tamanho, estrellas);
			if (champinon.getMeta() <= 0) {
				panel.setText("Meta inalcanzable");
			} else {
				panel.setText("" + champinon.getMeta() + " (" + champinon.getMetaReal() + ")");
			}
    	} catch (champinonInexistente e1) {
			panel.setText("Champiñon inexistente");
		} catch (estrellasIncorrectas e1) {
			System.out.println("Error al añadir la dificultad al champiñon para el cálculo de pikmin");
		}
	}
	
	/**
	 * Crea un objeto Champinon con los atributos introducidos, luego,
	 * crea una ventana para mostrar los cálculos de combinaciones
	 * de pikmins necesarios para afrontar el champinon.
	 * @param cbTipo tipo del champinon atacado
	 * @param cbTamanho tamanho del champinon atacado
	 * @param cbEstrellas estrellas a conseguir en el ataque
	 */
	private void btnCalculoPikminMouseClicked(JComboBox<TipoChampi> cbTipo,
			JComboBox<Tamanho> cbTamanho, JComboBox<String> cbEstrellas) {
		try {
			TipoChampi TipoChampi = (TipoChampi) cbTipo.getSelectedItem();
			Tamanho tamanho = (Tamanho) cbTamanho.getSelectedItem();
			int estrellas = cbEstrellas.getSelectedIndex() + 2;
			Champinon champinon = new Champinon(TipoChampi , tamanho, estrellas);
			if (champinon.getMetaReal() > 0) {
				VentanaCalculo ventana = new VentanaCalculo(champinon);
				ventana.setVisible(true);
			}
//			System.out.println("Calculo pikmin abierto");
		} catch (champinonInexistente e1) {
			System.out.println("Error al crear el champiñon para el cálculo de pikmin");
		} catch (estrellasIncorrectas e1) {
			System.out.println("Error al añadir la dificultad al champiñon para el cálculo de pikmin");
		}
	}
	
}

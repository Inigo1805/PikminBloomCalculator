package ventanas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.BatallaChampinon;
import clases.BatallaChampinon.numPikminErroneo;
import clases.Champinon;
import clases.Pikmin;
import clases.Pikmin.valorCorazonesErroneo;
import clases.Pikmin.valorHojaErroneo;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * Ventana de cálculo de pelotones
 * @author Inigo Aramburu Valdepeñas iav839@alumnos.unican.es
 *
 */
public class VentanaCalculo extends JFrame {
	/**
	 * Esto no se que es, solo se que quita un warning que había
	 */
	private static final long serialVersionUID = 1L;
	private static final int MAX_PLAYERS = 5;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Champinon champi = new Champinon(Champinon.TipoChampi.Marron, Champinon.Tamanho.Normal, 3);
					VentanaCalculo frame = new VentanaCalculo(champi);
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
	public VentanaCalculo(Champinon champinon) {

		setIconImage(Toolkit.getDefaultToolkit().getImage(
					VentanaCalculo.class.getResource("/Resources/"
					+champinon.getColor().toString()+".png")));
		setResizable(false);
		setTitle("Champiñón " + champinon.getColor() 
				+ " " + champinon.getTamanho() +" | Objetivo: " 
				+ champinon.getMeta() + " (" + champinon.getMetaReal() 
				+ ") puntos.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAceptar = new JButton("Ok");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cerrar();
			}
		});
		btnAceptar.setBounds(376, 458, 89, 23);
		contentPane.add(btnAceptar);
		
		JLabel lblPikminImg = new JLabel("New label");
		try {
		lblPikminImg.setIcon(new ImageIcon(VentanaCalculo.class.getResource(
				"/Resources/"+champinon.getPikminPreferido().getColor().toString()+".jpg")));
		} catch (NullPointerException e) {
			System.out.println("/Resources/"+champinon.getPikminPreferido().getColor()+".jpg");
		}
		lblPikminImg.setBounds(15, 143, 200, 200);
		contentPane.add(lblPikminImg);

		//COLUMNAS

		JLabel lblJugador[] = new JLabel[MAX_PLAYERS];
		JLabel lblMultiplicador1[] = new JLabel[MAX_PLAYERS];
		JLabel lblFlor1[] = new JLabel[MAX_PLAYERS];
		JLabel lblCorazones1[] = new JLabel[MAX_PLAYERS];
		JLabel lblMultiplicador2[] = new JLabel[MAX_PLAYERS];
		JLabel lblFlor2[] = new JLabel[MAX_PLAYERS];
		JLabel lblCorazones2[] = new JLabel[MAX_PLAYERS];

		for (int i = 0; i < MAX_PLAYERS; i++) {

			lblJugador[i] = new JLabel("Jugadores: "+(i+1));
			lblJugador[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblJugador[i].setBounds((240 + i * 120), 32, 100, 14);
			contentPane.add(lblJugador[i]);

			try {
				BatallaChampinon batalla = new BatallaChampinon(champinon, i+1, 40);
				System.out.println(batalla.muestraPeloton());
				HashMap<Pikmin, Integer> mapa = batalla.creaPeloton();
				boolean primero = true;
				for (Pikmin pikmin : mapa.keySet()) {
					if (mapa.size()!=0) {
						int multiplicador;
						Pikmin.CabezaPikmin flor;
						int corazones;
						if (primero) {
							multiplicador = mapa.get(pikmin);
							flor = pikmin.getFlor();
							corazones = pikmin.getAmistad();

							lblMultiplicador1[i] = new JLabel("x" + multiplicador);
							lblMultiplicador1[i].setHorizontalAlignment(SwingConstants.CENTER);
							lblMultiplicador1[i].setBounds((240 + i * 120), 57, 100, 30);
							contentPane.add(lblMultiplicador1[i]);

							lblFlor1[i] = new JLabel("");
							try {
								lblFlor1[i].setIcon(new ImageIcon(VentanaCalculo.class.getResource(
										"/Resources/"+flor.toString()+".png")));
							} catch (java.lang.NullPointerException e) {
								System.out.println("/Resources/"+flor.toString()+".png");
							}
							lblFlor1[i].setHorizontalAlignment(SwingConstants.CENTER);
							lblFlor1[i].setBounds((240 + i * 120), 98, 100, 100);
							contentPane.add(lblFlor1[i]);

							lblCorazones1[i] = new JLabel("");
							try {
								lblCorazones1[i].setIcon(new ImageIcon(VentanaCalculo.class.getResource(
										"/Resources/"+corazones+".png")));
							} catch (java.lang.NullPointerException e) {
								System.out.println("/Resources/"+corazones+".png");
							}
							lblCorazones1[i].setHorizontalAlignment(SwingConstants.CENTER);
							lblCorazones1[i].setBounds((240 + i * 120), 209, 100, 30);
							contentPane.add(lblCorazones1[i]);
							primero = false;
						} else {
							multiplicador = mapa.get(pikmin);
							flor = pikmin.getFlor();
							corazones = pikmin.getAmistad();

							lblMultiplicador2[i] = new JLabel("x" + multiplicador);
							lblMultiplicador2[i].setHorizontalAlignment(SwingConstants.CENTER);
							lblMultiplicador2[i].setBounds((240 + i * 120), 265, 100, 30);
							contentPane.add(lblMultiplicador2[i]);

							lblFlor2[i] = new JLabel("");
							try {
								lblFlor2[i].setIcon(new ImageIcon(VentanaCalculo.class.getResource(
										"/Resources/"+flor.toString()+".png")));
							} catch (java.lang.NullPointerException e) {
								System.out.println("/Resources/"+flor.toString()+".png");
							}
							lblFlor2[i].setHorizontalAlignment(SwingConstants.CENTER);
							lblFlor2[i].setBounds((240 + i * 120), 306, 100, 100);
							contentPane.add(lblFlor2[i]);

							lblCorazones2[i] = new JLabel("");
							try {
								lblCorazones2[i].setIcon(new ImageIcon(VentanaCalculo.class.getResource(
										"/Resources/"+corazones+".png")));
							} catch (java.lang.NullPointerException e) {
								System.out.println("/Resources/"+corazones+".png");
							}
							lblCorazones2[i].setHorizontalAlignment(SwingConstants.CENTER);
							lblCorazones2[i].setBounds((240 + i * 120), 417, 100, 30);
							contentPane.add(lblCorazones2[i]);
						}
					} else {
						System.out.println("No se puede alzancar la meta para "+i+"jugador(es)");
					}
				}

			} catch (numPikminErroneo e1) {
				System.out.println("Error con el numero de pikmins en la iteracion" + i);
				e1.printStackTrace();
			} catch (valorCorazonesErroneo e1) {
				System.out.println("Error con el numero de corazones en la iteracion" + i);
				e1.printStackTrace();
			} catch (valorHojaErroneo e1) {
				System.out.println("Error con el valor de hoja en la iteracion" + i);
				e1.printStackTrace();
			}
		}
	}
	
	private void cerrar() {
		this.dispose();
	}
}


package clases;

import java.util.ArrayDeque;
import java.util.HashMap;

import clases.Pikmin.valorCorazonesErroneo;
import clases.Pikmin.valorHojaErroneo;

/**
 * Objeto clase BatallaChampinon
 * @author Inigo Aramburu Valdepeñas iav839@alumnos.unican.es
 *
 */
public class BatallaChampinon {
	
	@SuppressWarnings("serial")
	public class numPikminErroneo extends Exception {
	}
	
	@SuppressWarnings("serial")
	public class numDecorErroneo extends Exception {
	}
	
	private Champinon champinon;
	private int numPikmin;
	private ArrayDeque<Pikmin> peloton = new ArrayDeque<>();
	private HashMap<Pikmin, Integer> tiposPikmin = new HashMap<>();
	private int jugadores;
	private int poder = 0;
	
	/**
	 * Constructor de batalla de champinon.
	 * @param champinon champinon atacado
	 * @param jugadores numero de jugadores que participaran
	 * @param numPikmin numero de pikmins disponibles del tipo introducido
	 * @throws numPikminErroneo si el numero de pikmin no sirve para un pelotón
	 */
	public BatallaChampinon(Champinon champinon, int jugadores, int numPikmin) 
			throws numPikminErroneo {
		if (numPikmin <= 0 || numPikmin > 40) throw new numPikminErroneo();
		this.numPikmin = numPikmin;
		this.champinon = champinon;
		this.jugadores = jugadores;
	}
	
	/**
	 * Anhade un pikmin al final de la cola del pelotón y actualiza 
	 * el poder del pelotón. Si el pelotón estaba lleno, elimina al 
	 * pikmin de la cabeza de la cola y actualiza el poder del pelotón.
	 * @param entrada
	 */
	public void anhadePikmin (Pikmin entrada) {
		peloton.add(entrada);
		if (tiposPikmin.get(entrada) != null) {
			tiposPikmin.put(entrada, tiposPikmin.get(entrada) + 1);
		} else {
			tiposPikmin.put(entrada, 1);
		}
		poder += entrada.calculaPoder();
		while (peloton.size() > numPikmin) {
			Pikmin eliminado = peloton.remove();
			poder -= eliminado.getPoder();
			if (tiposPikmin.get(eliminado) != null) {
				tiposPikmin.put(eliminado, tiposPikmin.get(eliminado) - 1);
				tiposPikmin.remove(eliminado, 0);
			}
		}
	}

	/**
	 * Crea un pelotón de pikmins para llegar al objetivo deseado.
	 * @param objetivo poder a conseguir con el pelotón
	 * @return el pelotón creado, null si no se llega al objetivo
	 * @throws valorCorazonesErroneo 
	 * @throws valorHojaErroneo
	 */
	public HashMap<Pikmin, Integer> creaPeloton () throws valorCorazonesErroneo, valorHojaErroneo {
		for (int corazones = 0; corazones <= 8; corazones++) {
			for (int flor = 1; flor <= 4; flor++) {
				Pikmin entrada = new Pikmin(this.champinon);
				entrada.setAmistad(corazones);
				entrada.setFlor(flor);
				entrada.calculaPoder();
				for (int i = 0; i < numPikmin; i++) {
					anhadePikmin(entrada);
					if (poder > champinon.getMetaReal()/jugadores) {
						return tiposPikmin;
					}
				}
			}
		}
		tiposPikmin = new HashMap<>();;
		return tiposPikmin;
	}
	
	public String muestraPeloton() throws valorCorazonesErroneo, valorHojaErroneo {
		this.creaPeloton();
		String str = "Tamaño: " + peloton.size();
		str +=  "\nPelotón de cada jugador: \n";
		if (tiposPikmin != null) {
			for (Pikmin pikmin : tiposPikmin.keySet()) {
				str += "#" + tiposPikmin.get(pikmin) + " pikmin con estas características:\n" +
						pikmin.toString() + "\n-------------\n";
			}
		} else {
			str = "\nNo se puede conseguir con " + jugadores + " jugador";
		}
		return str;
	}
	
	
	public void setNumPikmin(int a) throws numPikminErroneo {
		if (numPikmin <= 0 || numPikmin > 40) throw new numPikminErroneo();
		this.numPikmin = a;
	}	
}

package clases;

/**
 * Objeto clase champinon
 * @author Inigo Aramburu Valdepeñas iav839@alumnos.unican.es
 *
 */
public class Champinon {
	
	@SuppressWarnings("serial")
	public class estrellasIncorrectas extends Exception {
	}
	@SuppressWarnings("serial")
	public class champinonInexistente extends Exception {
	}
	
	public static enum TipoChampi {Marron, Rojo, Amarillo, Azul, Morado, 
							Blanco, Rosa, Gris, Fuego, Agua, Cristal};
	public static enum Tamanho {Pequeño, Normal, Grande, Gigante};
	
	private static int[] tipoValorArray = {-7, 4, 3, 3, 6, 2, 2, 5, 92, 91, 93};

	private TipoChampi color;
	private Tamanho tamanho;
	private int meta;
	private int metaReal;
	private Pikmin pikmin;
	
	/**
	 * Construye el objeto de tipo Champinon con los parámetros introducidos.
	 * @param a Tipo del champinon
	 * @param b Tamanho del champinon
	 * @param estrellas estrellas objetivo a destruir. Se puede calcular de nuevo
	 * @throws champinonInexistente Si los datos introducidos no representan ningun champinon posible
	 * @throws estrellasIncorrectas estrellasIncorrectas si el número de estrellas introducido
	 * no entra dentro de los parámetros del juego ( de 1 a 4)
	 */
	public Champinon(TipoChampi a, Tamanho b, int estrellas) throws champinonInexistente, estrellasIncorrectas {
		if ((a.equals(TipoChampi.Cristal) || a.equals(TipoChampi.Fuego) || a.equals(TipoChampi.Agua)) && 
				(b.equals(Tamanho.Pequeño) || b.equals(Tamanho.Gigante))) {
			throw new champinonInexistente();
		}
		this.color = a;
		this.tamanho = b;
		calculaMeta(estrellas);
		this.pikmin = new Pikmin (this);
	}
	
	/**
	 * Calcula el poder y el poderReal de un champinon
	 * dependiendo del número de estrellas que se 
	 * quieran conseguir.
	 * @param estrellas estrellas que se quieren conseugir.
	 * @throws estrellasIncorrectas si el número de estrellas introducido
	 * no entra dentro de los parámetros del juego ( de 1 a 4)
	 */
	private void calculaMeta (int estrellas) throws estrellasIncorrectas {
		if (estrellas < 0 || estrellas > 4) {
			throw new estrellasIncorrectas();
		}
		int shift = 0;
		int factor = 0;
		if (estrellas == 1) {
			shift = 0;
			factor = 0;
		} else if (estrellas == 2) {
			shift = 17;
			if (getTamanho().equals(Tamanho.Pequeño)) {
				factor = 25;
			} else if (getTamanho().equals(Tamanho.Normal)) {
				factor = 90;
			} else if (getTamanho().equals(Tamanho.Grande)) {
				factor = 120;
			} else {
				factor = -1;
			}
		} else if (estrellas == 3) {
			shift = 23;
			if (getTamanho().equals(Tamanho.Pequeño)) {
				factor = 40;
			} else if (getTamanho().equals(Tamanho.Normal)) {
				factor = 120;
			} else if (getTamanho().equals(Tamanho.Grande)) {
				factor = 150;
			} else {
				factor = -1;
			}
		} else if (estrellas == 4) {
			shift = 28;
			if (getTamanho().equals(Tamanho.Pequeño)) {
				factor = 70;
			} else if (getTamanho().equals(Tamanho.Normal)) {
				factor = 140;
			} else if (getTamanho().equals(Tamanho.Grande)) {
				factor = 175;
			} else {
				factor = -1;
			}
		}
		int vida = tipoValorArray[this.color.ordinal()];
		this.meta = (vida + shift) * factor;
		this.metaReal = (int) Math.ceil(meta / (1 + (estrellas - 1) * 0.05));
//		System.out.println("("+vida + "+" + shift + ")*"+factor+"="+meta);
//		System.out.println(meta + "/ (1 + (" + estrellas + "-1) * 0.05) = " + metaReal);
	}
	
	/**
	 * Muestra el poder necesario para afrontar
	 * el desafío. NO tiene en cuenta el bono de tiempo.
	 * @return meta del champinon
	 */
	public int getMeta() {
		return this.meta;
	}
	
	/**
	 * Muestra el poder necesario para afrontar
	 * el desafío. SI tiene en cuenta el bono de tiempo.
	 * @return meta del champinon
	 */
	public int getMetaReal() {
		return this.metaReal;
	}
	
	/**
	 * Muestra el tipo de champinon.
	 * @return tipo de champinon
	 */
	public TipoChampi getColor() {
		return this.color;
	}
	
	/**
	 * Devuelve el objeto Pikmin mas
	 * favorable para afrontar el desafio.
	 * @return pikmin mas favorable para el desafio
	 */
	public Pikmin getPikminPreferido() {
		return this.pikmin;
	}
	
	/**
	 * @return the tamanho
	 */
	public Tamanho getTamanho() {
		return tamanho;
	}

	@Override
	public String toString() {
		String str = "Champiñon:\n ~ Color: " + this.color
					+ "\n ~ Tamaño: " + this.getTamanho()
					+ "\n ~ Meta: " + this.meta
					+ "\n ~ Meta Real: " + this.metaReal
					+ "\n" + this.pikmin.toString();
		return str;
	}
	
}

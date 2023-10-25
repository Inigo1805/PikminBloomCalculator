package clases;

import java.util.Objects;

/**
 * Objeto clase Pikmin
 * @author Inigo Aramburu Valdepeñas iav839@alumnos.unican.es
 *
 */
public class Pikmin {
	
	@SuppressWarnings("serial")
	public class valorCorazonesErroneo extends Exception {
	}
	
	@SuppressWarnings("serial")
	public class valorHojaErroneo extends Exception {
	}
	
	public static enum ColorPikmin {Rojo, Amarillo, Azul, Morado, 
									Blanco, Rosa, Gris, Roca, Cualquiera};
	private int[] poderColor = {4, 3, 3, 6, 2, 2, 5, 3};
	public static enum CabezaPikmin {Nada, Hoja, Bulbo, Normal, Rara};
	private int[] valoresAmistad = {0, 1, 2, 3, 4, 8, 12, 16, 20};

	private ColorPikmin color;
	private int poder;
	private int poderMinimo;
	private CabezaPikmin cabeza = CabezaPikmin.Nada;
	boolean decor = false;
	private int amistad = 0;
	
	/**
	 * Crea el pikmin mas favorable para un determinado champinon
	 * @param colorChampi
	 */
	public Pikmin(Champinon champinon) {
		int valor = champinon.getColor().ordinal();
//		System.out.println(valor);
		switch (valor) {
		case 1:
			this.color = ColorPikmin.Rojo;
			this.poderMinimo = poderColor[this.getColor().ordinal()] + 12;
			break;
		case 2:
			this.color = ColorPikmin.Amarillo;
			this.poderMinimo = poderColor[this.getColor().ordinal()] + 12;
			break;
		case 3:
			this.color = ColorPikmin.Azul;
			this.poderMinimo = poderColor[this.getColor().ordinal()] + 12;
			break;
		case 4:
			this.color = ColorPikmin.Morado;
			this.poderMinimo = poderColor[this.getColor().ordinal()] + 12;
			break;
		case 5:
			this.color = ColorPikmin.Blanco;
			this.poderMinimo = poderColor[this.getColor().ordinal()] + 12;
			break;
		case 6:
			this.color = ColorPikmin.Rosa;
			this.poderMinimo = poderColor[this.getColor().ordinal()] + 12;
			break;
		case 7:
			this.color = ColorPikmin.Gris;
			this.poderMinimo = poderColor[this.getColor().ordinal()] + 12;
			break;
		case 8:
			this.color = ColorPikmin.Rojo;
			this.poderMinimo = poderColor[ColorPikmin.Rojo.ordinal()] + 100;
			break;
		case 9:
			this.color = ColorPikmin.Azul;
			this.poderMinimo = poderColor[ColorPikmin.Azul.ordinal()] + 100;
			break;
		case 10:
			this.color = ColorPikmin.Roca;
			this.poderMinimo = poderColor[ColorPikmin.Roca.ordinal()] + 100;
			break;
		default:
			this.color = ColorPikmin.Morado;
			this.poderMinimo = poderColor[this.getColor().ordinal()] + 3;
			break;
		}
		this.calculaPoder();
	}

	/**
	 * Calcula el poder de un pikmin, en funcion de sus atributos.
	 * @return el poder del pikmin.
	 */
	public int calculaPoder() {
		this.poder = poderMinimo + getFlor().ordinal() + amistad;
		if (decor) poder += 4;
		return this.poder;
	}
	
	/**
	 * Establece la decoracion de un pikmin.
	 * @param decor true si lleva, false si no lleva
	 */
	public void setDecor(boolean decor) {
		this.decor = decor;
	}
	
	/**
	 * Establece la flor que lleva el pikmin en la cabeza.
	 * @param flor flor que lleva el pikmin en la cabeza
	 */
	public void setFlor(CabezaPikmin flor) {
		this.cabeza = flor;
	}
	
	public void setFlor(int poder) throws valorHojaErroneo {
		if (poder == 0) this.cabeza = CabezaPikmin.Nada;
		else if (poder == 1) this.cabeza = CabezaPikmin.Hoja;
		else if (poder == 2) this.cabeza = CabezaPikmin.Bulbo;
		else if (poder == 3) this.cabeza = CabezaPikmin.Normal;
		else if (poder == 4) this.cabeza = CabezaPikmin.Rara;
		else throw new valorHojaErroneo();
	}
	
	/**
	 * Establece el número de corazones que tiene el pikmin.
	 * @param corazones número de corazones del pikmin
	 */
	public void setAmistad(int corazones) throws valorCorazonesErroneo {
		if (corazones < 0 || corazones > 8) throw new valorCorazonesErroneo();
		this.amistad = valoresAmistad[corazones];
		if (amistad >= 4) decor = true;
	}
	
	/**
	 * Devuelve el ColorPikmin correspondiente al pikmin.
	 * @return el ColorPikmin correspondiente al pikmin
	 */
	public ColorPikmin getColor() {
		return color;
	}

	/**
	 * Devuelve el poder del pikmin.
	 * @return el poder del pikmin
	 */
	public int getPoder() {
		return poder;
	}
	

	public CabezaPikmin getFlor() {
		return cabeza;
	}

	public int getAmistad() {
		return amistad;
	}
	
	@Override
	public String toString() {
		String decorStr = "false";
		if (decor) decorStr = "true";
		String str = "Pikmin "+ this.color.toString() + ":" +
					"\n ~ Cabeza: " + this.getFlor().toString() +
					"\n ~ Decor: " + decorStr +
					"\n ~ Amistad: " + this.amistad +
					"\n ~ Poder: " + this.poder;
		return str;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pikmin pikmin = (Pikmin) o;
        return poder == pikmin.poder &&
               poderMinimo == pikmin.poderMinimo &&
               decor == pikmin.decor &&
               amistad == pikmin.amistad &&
               color == pikmin.color &&
               getFlor() == pikmin.cabeza;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, poder, poderMinimo, cabeza, decor, amistad);
    }

	
}

package dad.javafx.cambioDivisa;

public class Divisa {

	private String divisa;
	private Double tasa;
	
	public Divisa(String divisa, Double tasa) {
		
		super();
		this.divisa = divisa;
		this.tasa = tasa;
		
	}

	public String getDivisa() {
		return divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public Double getTasa() {
		return tasa;
	}

	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}
	
	public Double fromEuros (Double euros) {
		
		return this.tasa * euros;
		
	}
	
	public Double toEuros (Double moneda) {
		
		return moneda / this.tasa;
		
	}
	
	@Override
	public String toString() {
		return divisa;
	}

	public static Double fromTo(Divisa monedaOrigen, Divisa monedaDestino, Double cantidad) {
		
		return monedaDestino.fromEuros(monedaOrigen.toEuros(cantidad));
		
	}
	
}

package unlam.compiler.entities;

import unlam.compiler.utils.StringFormatter;

import java.util.Objects;

public class SymbolTableEntry {

	private String nombre;
	private String tipoDato;
	private Object valor;
	private int longitud;

	public SymbolTableEntry(String nombre, String tipoDato, Object valor, int longitud) {
		this.nombre = nombre;
		this.tipoDato = tipoDato;
		this.valor = valor;
		this.longitud = longitud;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SymbolTableEntry that = (SymbolTableEntry) o;
		return longitud == that.longitud && Objects.equals(nombre, that.nombre) && Objects.equals(tipoDato, that.tipoDato) && Objects.equals(valor, that.valor);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, tipoDato, valor, longitud);
	}

	@Override
	public String toString() {
		return StringFormatter.centerText(nombre) +
				StringFormatter.centerText(tipoDato) +
				StringFormatter.centerText(valor != null ? valor.toString() : "") +
				StringFormatter.centerText(longitud > 0 ? String.valueOf(longitud) : "");
	}
}

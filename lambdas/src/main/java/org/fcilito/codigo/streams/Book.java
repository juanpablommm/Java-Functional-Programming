package org.fcilito.codigo.streams;

public class Book {

	private String tittle;

	private Integer copy;

	public Book(String tittle, Integer copy) {
		this.tittle = tittle;
		this.copy = copy;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public Integer getCopy() {
		return copy;
	}

	public void setCopy(Integer copy) {
		this.copy = copy;
	}

	@Override
	public String toString() {
		return "Book{" + "tittle='" + tittle + '\'' + ", copy=" + copy + '}';
	}
}

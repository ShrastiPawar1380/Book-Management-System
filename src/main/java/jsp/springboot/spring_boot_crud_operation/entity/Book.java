package jsp.springboot.spring_boot_crud_operation.entity;

import jakarta.persistence.*;

@Entity
public class Book 
{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String author;
	private double price;
	private int publisedYear;
	private String genre;
	private boolean availablity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getPublisedYear() {
		return publisedYear;
	}
	public void setPublisedYear(int publisedYear) {
		this.publisedYear = publisedYear;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public boolean isAvailablity() {
		return availablity;
	}
	public void setAvailablity(boolean availablity) {
		this.availablity = availablity;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + ", publisedYear="
				+ publisedYear + ", genre=" + genre + ", availablity=" + availablity + "]";
	}
	
}

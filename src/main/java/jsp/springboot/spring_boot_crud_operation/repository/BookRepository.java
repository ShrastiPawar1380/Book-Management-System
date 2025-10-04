package jsp.springboot.spring_boot_crud_operation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jsp.springboot.spring_boot_crud_operation.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	//
	List<Book> findByAuthor(String author);
	
	List<Book> findByPriceGreaterThan(double price);
	
	List<Book> findByPriceBetween(double startingRange, double endingRange);
	
	@Query("select b from Book b where b.publisedYear=2017")
	List<Book> getBookByPublisedYear();
	
	@Query("select b from Book b where b.availablity = ?1")
	List<Book> getBookByAvailablity(boolean availablity);
	
	@Query("select b from Book b where b.genre=:genre")
	List<Book> getBookByGenre(String genre);
}

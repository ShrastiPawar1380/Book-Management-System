package jsp.springboot.spring_boot_crud_operation.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jsp.springboot.spring_boot_crud_operation.entity.Book;
import jsp.springboot.spring_boot_crud_operation.repository.BookRepository;

@Repository
public class BookDao 
{
	@Autowired
	private BookRepository bookRepository;
	
	//Save Book Record
	public Book saveBook(Book book)
	{
		return bookRepository.save(book);
	}
	
	// Get All Book
	public List<Book> getAllBooks()
	{
		List<Book> books = bookRepository.findAll();

		return books;
	}
	
	//Get Book By Id
	public Optional<Book> getBookById(int id) {
	    return bookRepository.findById(id);
	}
	
	
	//Delete Book By Id
	public void deleteBookById(int id) {
	    bookRepository.deleteById(id);
	}

	// get book by author
	public List<Book> getBookByAuthor(String author)
	{
		 List<Book> books = bookRepository.findByAuthor(author);
		 return books;
	}
	
	public List<Book> getBookByPrice(double price)
	{
		 List<Book> books = bookRepository.findByPriceGreaterThan(price);
		 return books;
	}
	
	public List<Book> getBookByPriceRange(double strPrice, double endPrice)
	{
		 List<Book> books = bookRepository.findByPriceBetween(strPrice, endPrice);
		 return books;
	}
	
	
	//get record by publish year
	public List<Book> getBookByPublisedyear(int year)
	{
		List<Book> books = bookRepository.getBookByPublisedYear();
		return books;
	}
	
	
	//get record by Availability
	public List<Book> getBookByAvailablity(boolean availablity)
	{
		List<Book> books = bookRepository.getBookByAvailablity(availablity);
		return books;
	}
		
	//get record by Genre
	public List<Book> getBookByGenre(String genre)
	{
		List<Book> books = bookRepository.getBookByGenre(genre);
		return books;
	}
				
	//Pagination
	public Page<Book> getBookByPagination(int pageNumber, int pageSize)
	{
		return bookRepository.findAll(PageRequest.of(pageNumber, pageSize));
	}
	
	
	//Sorting
	public List<Book> getBooBySorting(String field)
	{
		return bookRepository.findAll(Sort.by(field).ascending());
	}
	
	//Pagination & Sorting
	public Page<Book> getBookByPaginationAndSorting(int pageNumber, int pageSize, String field)
	{
		return bookRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).descending()));
	}
	
}

package jsp.springboot.spring_boot_crud_operation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jsp.springboot.spring_boot_crud_operation.dao.BookDao;
import jsp.springboot.spring_boot_crud_operation.dto.ResponseStructure;
import jsp.springboot.spring_boot_crud_operation.entity.Book;
import jsp.springboot.spring_boot_crud_operation.exception.NoRecordAvailableException;

@Service
public class BookService {
	@Autowired
	private BookDao bookDao;
	
	public ResponseEntity<ResponseStructure<Book>> saveBook(Book book)
	{
		ResponseStructure<Book> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Book recored saved");
		response.setData(bookDao.saveBook(book));
		return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBook(){
		ResponseStructure<List<Book>> response = new ResponseStructure<>();
	    List<Book> books = bookDao.getAllBooks();

	    if (!books.isEmpty()) {
	    	response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("All records found");
	        response.setData(books);
	        return  new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
	        
	    } else {
	        response.setStatusCode(HttpStatus.NOT_FOUND.value());
	        response.setMessage("No records found");
	        response.setData(null);
	        return  new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.NOT_FOUND);
	    }
	}
	
	public ResponseEntity<ResponseStructure<Book>> getBookById(int id) {
	    ResponseStructure<Book> response = new ResponseStructure<>();
	    Optional<Book> opt = bookDao.getBookById(id);

	    if (opt.isPresent()) {
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Record Found");
	        response.setData(opt.get());
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        response.setStatusCode(HttpStatus.NOT_FOUND.value());
	        response.setMessage("Record Not Found");
	        response.setData(null);
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	}
	

	public ResponseEntity<ResponseStructure<Book>> updateBook(Book book) {
	    ResponseStructure<Book> response = new ResponseStructure<>();

	    Optional<Book> opt = bookDao.getBookById(book.getId());

	    if (opt.isPresent()) {
	        Book updatedBook = bookDao.saveBook(book); 
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Record Updated Successfully");
	        response.setData(updatedBook);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        response.setStatusCode(HttpStatus.NOT_FOUND.value());
	        response.setMessage("Book ID Not Found");
	        response.setData(null);
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	}

	public ResponseEntity<ResponseStructure<String>> deleteBook(Integer id) {
	    ResponseStructure<String> response = new ResponseStructure<>();
	    Optional<Book> opt = bookDao.getBookById(id);

	    if (opt.isPresent()) {
	        bookDao.deleteBookById(id);
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Successful Deletion");
	        response.setData("Record with id : " + id + " has been deleted");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        response.setStatusCode(HttpStatus.NOT_FOUND.value());
	        response.setMessage("Id is Invalid");
	        response.setData("Record with id : " + id + " is not available in database");
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	}

	//get record by author
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAuthor(String author){
		List<Book> books = bookDao.getBookByAuthor(author);
		
		ResponseStructure<List<Book>> response = new ResponseStructure<>();
		
		if(!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book records recieved");
			response.setData(books);
			
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}
		else
			throw new NoRecordAvailableException("No record Found Since Author is Invalid");
	}
	
	//get book by price
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPrice(double price){
		List<Book> books = bookDao.getBookByPrice(price);
		
		ResponseStructure<List<Book>> response = new ResponseStructure<>();
		
		if(!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book records recieved");
			response.setData(books);
			
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}
		else
			throw new NoRecordAvailableException("No record Found Since Price is Invalid");
	}
	
	//get book by price range
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPriceRange(double strPrice, double endPrice){
		List<Book> books = bookDao.getBookByPriceRange(strPrice, endPrice);
		
		ResponseStructure<List<Book>> response = new ResponseStructure<>();
		
		if(!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book records recieved");
			response.setData(books);
			
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}
		else
			throw new NoRecordAvailableException("No record Found Since Price Range is Invalid");
	}
	
	
	
	//get record by publish year
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPublisedyear(int year) {
	    List<Book> books = bookDao.getBookByPublisedyear(year);

	    ResponseStructure<List<Book>> response = new ResponseStructure<>();

	    if (!books.isEmpty()) {
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Book records received");
	        response.setData(books);
	        return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
	    }
	    else 
	    {
	    	throw new NoRecordAvailableException("No record Found Since Year is Invalid");
	    }
	}

	// get book by availability
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAvailibility(boolean availablity) {
	    List<Book> books = bookDao.getBookByAvailablity(availablity);

	    ResponseStructure<List<Book>> response = new ResponseStructure<>();

	    if (!books.isEmpty()) {
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Book records received");
	        response.setData(books);
	        return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
	    }
	    else 
	    {
	    	throw new NoRecordAvailableException("No record Found Since Yaer is Invalid");
	    }
	}
	
	// get book by genre
		public ResponseEntity<ResponseStructure<List<Book>>> getBookByGnere(String genre) {
		    List<Book> books = bookDao.getBookByGenre(genre);

		    ResponseStructure<List<Book>> response = new ResponseStructure<>();

		    if (!books.isEmpty()) {
		        response.setStatusCode(HttpStatus.OK.value());
		        response.setMessage("Book records received");
		        response.setData(books);
		        return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		    }
		    else 
		    {
		    	throw new NoRecordAvailableException("No record Found Since Yaer is Invalid");
		    }
		}

	//Pagination
	public ResponseEntity<ResponseStructure<Page<Book>>> getBookByPagination(int pageNumber, int pageSize){
		Page<Book> pages = bookDao.getBookByPagination(pageNumber, pageSize);
		ResponseStructure<Page<Book>> response = new ResponseStructure<>();
		
		if(!pages.isEmpty())
		{
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book record retrived");
			response.setData(pages);
			
			return new ResponseEntity<ResponseStructure<Page<Book>>>(response, HttpStatus.OK);
		}
		else 
			throw new NoRecordAvailableException("No record");
		
	}
	
	//sorting
	public ResponseEntity<ResponseStructure<List<Book>>> getBookBySorting(String field){
		List<Book> books = bookDao.getBooBySorting(field);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();
		
		if(!books.isEmpty())
		{
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book record retrived");
			response.setData(books);
			
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}
		else 
			throw new NoRecordAvailableException("No record");
		
	}

	//Pagination and sorting
	public ResponseEntity<ResponseStructure<Page<Book>>> getBookByPaginationAndSorting(int pageNumber, int pageSize, String field){
		Page<Book> pages = bookDao.getBookByPaginationAndSorting(pageNumber, pageSize, field);
		ResponseStructure<Page<Book>> response = new ResponseStructure<>();
		
		if(!pages.isEmpty())
		{
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book record retrived");
			response.setData(pages);
			
			return new ResponseEntity<ResponseStructure<Page<Book>>>(response, HttpStatus.OK);
		}
		else 
			throw new NoRecordAvailableException("No record");
		
	}
}

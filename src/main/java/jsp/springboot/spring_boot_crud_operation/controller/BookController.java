 package jsp.springboot.spring_boot_crud_operation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.springboot.spring_boot_crud_operation.dto.ResponseStructure;
import jsp.springboot.spring_boot_crud_operation.entity.Book;
import jsp.springboot.spring_boot_crud_operation.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	
	@Autowired
	private BookService bookService;
	
//	Save The book
	@PostMapping
	public ResponseEntity<ResponseStructure<Book>> SaveBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}
	
//	Fetch All Record
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks(){
		return bookService.getAllBook();
	}
	
//	Fetch By Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable Integer id) {
		return bookService.getBookById(id);	
	} 
	
//	Update the Record
	@PutMapping
	public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}
	
//	Delete the record
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBook(@PathVariable Integer id) {
		
		return bookService.deleteBook(id);
	}
	

	// get book by author
	@GetMapping("/author/{author}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAuthor(@PathVariable String author){
		return bookService.getBookByAuthor(author);
	}
	
	
	//get book by price
	@GetMapping("/price/{price}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPrice(@PathVariable double price){
		return bookService.getBookByPrice(price);
	}
	
	
	//get book by price Range
		@GetMapping("/range/{strPrice}/{endPrice}")
		public ResponseEntity<ResponseStructure<List<Book>>> getBookByPriceRange(@PathVariable double strPrice, @PathVariable double endPrice){
			return bookService.getBookByPriceRange(strPrice, endPrice);
		}
		
	//Get record by publish year
	@GetMapping("/publisedYear/{year}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPublisedyear(@PathVariable int year) {
	    return bookService.getBookByPublisedyear(year);
	}
	
	
	//Get record by Availability
	@GetMapping("/availablity/{availablity}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAvailability(@PathVariable boolean availablity) {
	    return bookService.getBookByAvailibility(availablity);
	}
	
	//Get record by publish year
	@GetMapping("/genre/{genre}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByGenre(@PathVariable String genre) {
	    return bookService.getBookByGnere(genre);
	}


	//Pagination
	@GetMapping("/pagination/{pageNumber}/{pageSize}")
	public ResponseEntity<ResponseStructure<Page<Book>>> getBookByPagination(@PathVariable int pageNumber, @PathVariable int pageSize){
		return bookService.getBookByPagination(pageNumber, pageSize);
	}
	
	//Sorting
	@GetMapping("/sorting/{filed}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookBySorting(@PathVariable String filed){
		return bookService.getBookBySorting(filed);
	}
		
	//pagination and Sorting
	@GetMapping("/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Book>>> getBookByPaginationAndSorting(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field){
		return bookService.getBookByPaginationAndSorting(pageNumber,pageSize,field);
	}
}

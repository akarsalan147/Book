package com.example.startProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.LookAndFeel;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.startProject.model.Book;

@RestController
public class BookController {
	
	private HashMap<Integer, Book> bookHashMap = new HashMap<>();
	
	private static Logger logger = LoggerFactory.getLogger(BookController.class);
	
	//insertBook - post - RequestBody
	//updateBook - put - RequestBody
	//deleteBook - Delete - PathVariable(BookID)
	//getBookDetails - Get - PathVariable
	//getAllBooks - Get - Return Book List
	
	@PostMapping("insertBook")
	public ResponseEntity insertBook(@RequestBody Book book) {
		logger.info("Book coming for insertion : {}", book);
		if(bookHashMap.containsKey(book.getId())) {
			logger.error("Book is already present");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		bookHashMap.put(book.getId(), book);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@PutMapping("updateBook")
	public Book updateBook(@RequestBody Book book) {
		bookHashMap.put(book.getId(), book);
		return bookHashMap.get(book.getId());
	}
	
	@DeleteMapping("/deleteBook/{id}")
	public String deleteBookByID(@PathVariable("id") int bookID) {
		bookHashMap.remove(bookID);
		return "Book deleted successfully.";
	}
	
	@GetMapping("/book/{bookID}")
	public Book getBookByBookID(@PathVariable("bookID") int bookID) {
		logger.info("BookID revcieved : ", bookID);
		return bookHashMap.get(bookID);
	}
	
	@GetMapping("/books")
	public List<Book> getBooks(){
		return bookHashMap.values().stream().collect(Collectors.toList());
	}
	
}

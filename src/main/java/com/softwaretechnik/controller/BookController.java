package com.softwaretechnik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softwaretechnik.datamodel.Book;
import com.softwaretechnik.service.BookService;

@RequestMapping("/bookStore")
@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping()
	public ResponseEntity<List<Book>> getAllBooks(){
		return new ResponseEntity(bookService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id){
		return new ResponseEntity(bookService.fetchBook(id).get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		bookService.addBook(book);
		return new ResponseEntity(book, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Book> removeBookById(@PathVariable int id){
		Book book = bookService.fetchBook(id).get();
		
		if(bookService.deleteBook(id)) {
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getOldestAndLatestBook")
	public ResponseEntity<List<Book>> getOldestAndLatestBook(){
		return new ResponseEntity(bookService.getOldestAndLatestBook(), HttpStatus.OK);
	}
	
	

}

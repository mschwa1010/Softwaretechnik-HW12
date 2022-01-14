package com.softwaretechnik.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softwaretechnik.datamodel.Book;
import com.softwaretechnik.repository.BookRepo;

@Service
public class BookService {
	
	@Autowired
	private BookRepo bookRepo;
	
	public List<Book> findAll(){
		return bookRepo.findAll();
	}
	
	public Optional<Book> fetchBook(int id) {
		return bookRepo.findById(id);
	}
	
	public Book addBook(Book book) {
		return bookRepo.save(book);
	}
	
	public boolean deleteBook(int id) {
		
		boolean status;
		try {
			bookRepo.deleteById(id);
			status= true;
		} catch(Exception e) {
			status =false;
		}
		
		return status;
	}
	
	
	public List<Book> getOldestAndLatestBook(){
		List<Book> books = bookRepo.findAll();
		List<Book> oldestAndLastest = new ArrayList<Book>();
		
		Book oldestBook = books.get(0); 
		Book latestBook = books.get(0);
		
		for(Book book : books) {
			if(book.getYear()< oldestBook.getYear()) {
				oldestBook = book;
			}
			if(book.getYear() > latestBook.getYear()) {
				latestBook = book;
			}
		}
		
		oldestAndLastest.add(latestBook);
		oldestAndLastest.add(oldestBook);
		
		return oldestAndLastest;
		
	}

}

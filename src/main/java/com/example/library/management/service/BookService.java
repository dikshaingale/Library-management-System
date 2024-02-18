package com.example.library.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.management.entity.Book;
import com.example.library.management.repository.BookRepository;

@Service   //class belong to service
public class BookService {
	
	@Autowired        //this annotation imports auto annotation package
	private BookRepository bRepo;  //to call save method we need BookRepository object
	public void save(Book b) {  //save method to save object into our database
		bRepo.save(b);
	}
	
	//to fetch data( which user has added) to table
	public List<Book> getAllBook(){
		return bRepo.findAll();
	}
	
	public Book getBookById(int id) {
		return bRepo.findById(id).get();
	}
	public void deleteById(int id) {
		bRepo.deleteById(id);
	}
}

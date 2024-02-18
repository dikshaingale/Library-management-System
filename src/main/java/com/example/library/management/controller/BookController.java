package com.example.library.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import com.example.library.management.entity.Book;
import com.example.library.management.entity.MyBookList;
import com.example.library.management.service.BookService;
import com.example.library.management.service.MyBookListService;


@Controller  //it show that this class belong to the controller
public class BookController {
	
	@Autowired
	private BookService service;  //BookService Obj
	
	@Autowired
	private MyBookListService myBookService;
	
	@GetMapping("/") // to get request
	 public String home() {
		return"home" ;
	}
	
	@GetMapping("/book_register")
	public String bookRegister() {
      return "bookRegister";
    }
	
	@GetMapping("/available_books")  //path for available books
	public ModelAndView getAllBook() {
      List<Book>list=service.getAllBook();  //return bookList page
//      ModelAndView m =new ModelAndView();
//      m.setViewName("bookList");
//      m.addObject("book", list);
//      return m;   //return that obj
      return new ModelAndView("bookList","book",list);
	}
	
	@PostMapping("/save") // postMapping to handle our post method
	public String addBook(@ModelAttribute Book b) { //modelAttribute will return form data in our book obj 
		                                            //this will send data to view to controller 
		
		//to save this book obj in database , we need call save method which is present in bookService class
		//to call save method we need bookService obj , so we creat obj at top
		service.save(b);
		return "redirect:/available_books";
	}
	
	@GetMapping("/my_books")
	public String getMyBooks(Model model) {  //to get my books
		 List<MyBookList>list=myBookService.getAllMyBooks(); 
		 model.addAttribute("book",list);
		 return "myBooks";
	}
	
	@RequestMapping("/myList/{id}")
		public String getMyList(@PathVariable("id")int id ) {
		Book b= service.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books";
		
	}
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id")int id, Model model) {
		Book b = service.getBookById(id);
		 model.addAttribute("book",b);
		return "bookEdit";
	}

	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		service.deleteById(id);
		return "redirect:/available_books";
	}
	
}

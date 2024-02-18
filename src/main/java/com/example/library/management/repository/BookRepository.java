package com.example.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.management.entity.Book;

public interface BookRepository extends JpaRepository<Book,Integer>{
	//this bookzRepository extend jpaRepository interface which provide more functionality it take two things(datatype and id of datatype )
	//this jpaRepository interface extends crud repository which provide basic functionality like save,update,delete
	

}

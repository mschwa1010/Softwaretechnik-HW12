package com.softwaretechnik.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.softwaretechnik.datamodel.Book;
public interface BookRepo extends JpaRepository<Book, Integer>{

}

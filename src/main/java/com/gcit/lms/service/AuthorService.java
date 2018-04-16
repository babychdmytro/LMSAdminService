package com.gcit.lms.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Author;
import com.gcit.lms.repository.AuthorRepository;

@RestController
public class AuthorService{

	@Autowired
	AuthorRepository authorRepository;
	
	
	//Getting all authors
	@RequestMapping(value="/authors", method=RequestMethod.GET)
	public List<Author> readAllAuthors(){
		return authorRepository.findAll();
	}
	
	//Getting author by Id
	@RequestMapping(value = "authors/{authorId}", method = RequestMethod.GET, produces = "application/json")
	public Author getAuthorByPk(@PathVariable("authorId") Integer authorId, HttpServletResponse response) throws SQLException, IOException {
		
		Author author =  authorRepository.findByAuthorId(authorId);
		
		return author;
	}
	
	//Saving author
	@RequestMapping(value = "author", method = RequestMethod.POST, consumes = "application/json")
	public void saveAuthor(@RequestBody Author author) throws SQLException {
		authorRepository.save(author);
	}
	
	
	//Deleting author
	@RequestMapping(value = "authors/{authorId}", method = RequestMethod.DELETE)
	public void deleteAuthor(@PathVariable("authorId") Integer authorId, HttpServletResponse response) throws SQLException {
		
		authorRepository.deleteById(authorId);

	}
	
	
	//Updating author
	@RequestMapping(value = "authors/{authorId}", method = RequestMethod.PUT, consumes = "application/json")
	public void updateAuthor(@RequestBody Author author, @PathVariable Integer authorId) throws SQLException {
		
		author.setAuthorId(authorId);
		authorRepository.save(author);
		
	}
	
}

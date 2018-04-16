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

import com.gcit.lms.entity.Publisher;
import com.gcit.lms.repository.PublisherRepository;

@RestController
public class PublisherService {

	@Autowired
	PublisherRepository publisherRepository;
	
	
	//Getting all publishers
	@RequestMapping(value="/publishers", method=RequestMethod.GET)
	public List<Publisher> readAllPublishers(){
		return publisherRepository.findAll();
	}
	
	//Getting publisher by Id
	@RequestMapping(value = "publishers/{publisherId}", method = RequestMethod.GET, produces = "application/json")
	public Publisher getPublisherByPk(@PathVariable("publisherId") Integer publisherId, HttpServletResponse response) throws SQLException, IOException {
		
		Publisher publisher =  publisherRepository.findByPublisherId(publisherId);
		
		return publisher;
	}
	
	//Saving publisher
	@RequestMapping(value = "publisher", method = RequestMethod.POST, consumes = "application/json")
	public void savePublisher(@RequestBody Publisher publisher) throws SQLException {
		publisherRepository.save(publisher);
	}
	
	
	//Deleting publisher
	@RequestMapping(value = "publishers/{publisherId}", method = RequestMethod.DELETE)
	public void deletePublisher(@PathVariable("publisherId") Integer publisherId, HttpServletResponse response) throws SQLException {
		
		publisherRepository.deleteById(publisherId);

	}
	
	
	//Updating publisher
	@RequestMapping(value = "publishers/{publisherId}", method = RequestMethod.PUT, consumes = "application/json")
	public void updatePublisher(@RequestBody Publisher publisher, @PathVariable Integer publisherId) throws SQLException {
		
		publisher.setPublisherId(publisherId);
		publisherRepository.save(publisher);
		
	}
	
}

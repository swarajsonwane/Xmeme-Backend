package com.swaraj.xmeme.memeController;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.swaraj.xmeme.model.idDetails;
import com.swaraj.xmeme.model.memeDetails;
import com.swaraj.xmeme.service.memeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class memesControlller {

	@Autowired
	private memeService service;
	
	  @RequestMapping("/")
	     public String home(){
	         return "Hello World! This is Swaraj Sonwane. Learning Spring :)";
	     }
	
	@PostMapping(value = "/memes",produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	
	public @ResponseBody idDetails add(@RequestBody memeDetails details) {
		service.save(details);
		idDetails retId = new idDetails(service.count());
		 return retId;
		
	}
	
	@GetMapping("/memes/all")
	public List<memeDetails> listAll() {
	    return service.listAll();
	}
	@GetMapping("/memes")
	public List<memeDetails> list() {
	    return service.listLatestRecords();
	}
	
	@GetMapping("/memes/{id}")
	public ResponseEntity<memeDetails> get(@PathVariable Integer id) {
	    try {
	       memeDetails memes = service.get(id);
	        return new ResponseEntity<memeDetails>(memes, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<memeDetails>(HttpStatus.NOT_FOUND);
	    }      
	}
	@PutMapping("/memes/{id}")
	public ResponseEntity<memeDetails> update(@RequestBody memeDetails meme, @PathVariable Integer id) {
	    try {
	        memeDetails existMeme =service.get(id);
	        existMeme.setCaption(meme.getCaption());
	        existMeme.setUrl(meme.getUrl());
	        service.save(existMeme);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	@DeleteMapping("/memes/{id}")
	public void deleteMeme(@PathVariable Integer id) {
	    service.delete(id);
	}
	
	
}

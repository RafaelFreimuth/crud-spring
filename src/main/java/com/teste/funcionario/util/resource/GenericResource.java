package com.teste.funcionario.util.resource;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.teste.funcionario.util.entity.AbstractEntity;

public interface GenericResource<ENT extends AbstractEntity> {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
				 produces = MediaType.APPLICATION_JSON_VALUE)
	default ENT save(@RequestBody ENT entity) { return null; };
	
	@GetMapping(path = "/{id}", 
				produces = MediaType.APPLICATION_JSON_VALUE)
	default ENT findById(@PathVariable Long id) {return null;}
	
	@PutMapping(path = "/{id}", 
				consumes = MediaType.APPLICATION_JSON_VALUE)
	default ENT update(@PathVariable Long id, @RequestBody ENT entity) { return null; };
	
	@DeleteMapping(path = "/{id}")
	default void delete(@PathVariable Long id) {};
	
	@GetMapping(path = "/paged",
				produces = MediaType.APPLICATION_JSON_VALUE)
	default Page<ENT> getPaged(@RequestParam int pagina, 
					   		   @RequestParam int size) { return Page.empty(); };
}

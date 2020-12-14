package com.jb.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.mongodb.client.result.UpdateResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

import com.jb.dao.PersonnelRepository;
import com.jb.model.Personnel;

//@CrossOrigin(origins = "hhtp://localhost:8081")
@RestController
@RequestMapping("/api")

public class PersonnelController {
	
	@Autowired
	PersonnelRepository repo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/helo")
	public String sayHello() {
		return "hello To You all!";
	}
	
	@GetMapping("/personnels")
	@ResponseStatus(HttpStatus.OK)
	public Collection<Personnel> getAllPersonnels() {
		log.debug("Getting all users");
		return repo.findAll();
	}
	
	/**
	 * Method to retreive a Personnel by id
	 * @param id
	 * @return 
	 */
	@GetMapping("/{id}")
	public Optional<Personnel> getPersonnelById(@PathVariable(value="id") String id){
		log.debug("Getting Personnel with id : {}",id);
		return repo.findById(id);
	}
	
	/**
	 * Method to update a Personnel by id
	 * @param id
	 * @return 
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<Personnel> updatePersonnel(@PathVariable("id") String id, @RequestBody Personnel personnel){
		log.debug("Updating Personnel with id : {}",id);
		
		Optional<Personnel> personnelData = repo.findById(id);
		repo.deleteById(id);
		if (personnelData.isPresent()) {
			Personnel p = personnelData.get();
			p.setDepartement(personnel.getDepartement());
			p.setFonction(personnel.getFonction());
			p.setId(personnel.getId());
			return new ResponseEntity<>(repo.save(p), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Method to update a Personnel by id
	 * @param id
	 * @return 
	 */
/*	@PutMapping("/update/{id}")
	public String updatePersonnel(@PathVariable("id") String id, @RequestBody Personnel updatePersonnel){
		log.debug("Updating Personnel with id : {}",id);
				
		Query q = new Query(Criteria.where(id).is(repo.findById(id).get().getId()));
		
		Update update = new Update();
		update.set("id", updatePersonnel.getId());
		update.set("fonction", updatePersonnel.getFonction());
		update.set("departement", updatePersonnel.getDepartement());
		
		UpdateResult result = mongoTemplate.updateFirst(q, update, Personnel.class);
		
		
		if (result != null) {
			return "The update has been made successfully!";
		}else {
			return "Error, no datas updated";
		}
		
	}*/
	
	
	/**
	 * Method to delete a Personnel by id
	 * @param id
	 * @return 
	 */
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		log.debug("Deleting personnel with id: {}",id);
		repo.deleteById(id);
		return "Personnel with id: "+id+" deleted successfully !";
	}
	
	
	
	/**
	 * Method to delete all the {@link Personnel}
	 * @return 
	 */
	@DeleteMapping("/deleteAll")
	public String deleteAll() {
		log.debug("Deleting all personnels from the database");
		
		repo.deleteAll();
		
		return "All Personnel successfully deleted !";
	}

	
	
	/**
	 * Method to save many personnel at a time
	 * @param list
	 * @return 
	 */
	@PostMapping("/saveAll")
	public String saveAll(Collection<Personnel> col) {
		log.debug("Saving many Personnels to the databasee");
		
		repo.saveAll(col);
		
		return "All Personnels sucessfully save to the Database";
	}
	
	
	
	/**
	 * Method to save a personnel into the database
	 * @param personnel
	 * @return 
	 */
	@PostMapping("/save")
	public ResponseEntity<Personnel> save(@RequestBody Personnel personnel) {
	
		try{
			log.debug("Try to Save One Personnel to the database!!!");
			Personnel p = repo.save(new Personnel(personnel.getId(), personnel.getFonction(),
					personnel.getDepartement()));
			return new ResponseEntity<>(p, HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}







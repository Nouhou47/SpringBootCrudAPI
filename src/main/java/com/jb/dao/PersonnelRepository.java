package com.jb.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.jb.model.Personnel;

//@RepositoryRestResource
@Repository
public interface PersonnelRepository extends MongoRepository<Personnel, String> {
	//List<Personnello> findByName(String ommoom);
	//List<Personnello> findByDepartement(String dep);
}

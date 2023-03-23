package com.springTest.getMeterData;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoLoadRepo extends CrudRepository<NoLoadTest,
Long> {
	Optional<NoLoadTest> findById(Long hjid);
	List<NoLoadTest> findAll();
}

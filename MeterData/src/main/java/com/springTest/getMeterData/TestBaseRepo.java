package com.springTest.getMeterData;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestBaseRepo extends CrudRepository<TestBase,
Long> {
	Optional<TestBase> findById(Long hjid);
	List<TestBase> findAll();
}
package com.springTest.getMeterData;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatingCurrentRepo extends CrudRepository<OperatingCurrentTest,
Long> {
	Optional<OperatingCurrentTest> findById(Long hjid);
	List<OperatingCurrentTest> findAll();
}

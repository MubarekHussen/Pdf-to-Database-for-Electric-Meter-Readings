package com.springTest.getMeterData;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TestFileRepo extends CrudRepository<TestFile,
String> {
	Optional<TestFile> findById(String fileName);
	List<TestFile> findAll();
}

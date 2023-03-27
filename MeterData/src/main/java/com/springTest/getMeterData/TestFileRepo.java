package com.springTest.getMeterData;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TestFileRepo extends CrudRepository<TestFile,
String> {
	Optional<TestFile> findByfileId(String fileId);
	List<TestFile> findAll();
}

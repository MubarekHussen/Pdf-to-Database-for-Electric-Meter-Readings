package com.springTest.getMeterData;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface StartingCurrentRepo extends CrudRepository<StartingCurrentTest,
Long> {
	Optional<StartingCurrentTest> findByhjid(Long hjid);
	List<StartingCurrentTest> findAll();
}

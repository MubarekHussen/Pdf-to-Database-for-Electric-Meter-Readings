package com.springTest.getMeterData;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalibrationTestRepo extends CrudRepository<CalibrationTest,
Long> {
	Optional<CalibrationTest> findByhjid(Long hjid);
	List<CalibrationTest> findAll();
}

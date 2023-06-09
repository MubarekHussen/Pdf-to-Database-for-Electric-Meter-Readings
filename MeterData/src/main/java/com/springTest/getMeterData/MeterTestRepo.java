package com.springTest.getMeterData;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterTestRepo extends CrudRepository<MeterTest,
Long> {
	Optional<MeterTest> findByhjid(Long hjid);
	List<MeterTest> findAll();
}

package com.springTest.getMeterData;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoLoadRepo extends CrudRepository<NoLoadTest,
Long> {
	Optional<NoLoadTest> findByhjid(Long hjid);
	List<NoLoadTest> findAll();
}

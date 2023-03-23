package com.springTest.getMeterData;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScAdjustmentRepo extends CrudRepository<ScAdjustment,
Long> {
	Optional<ScAdjustment> findById(Long hjid);
	List<ScAdjustment> findAll();
}

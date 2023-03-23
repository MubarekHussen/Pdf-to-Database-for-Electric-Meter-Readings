package com.springTest.getMeterData;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyRegisterRepo extends CrudRepository<EnergyRegisterTest,
Long> {
	Optional<EnergyRegisterTest> findById(Long hjid);
	List<EnergyRegisterTest> findAll();
}

package com.app.repo;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Data;

public interface DataRepository extends JpaRepository<Data, Integer> {
	
	// get list of id's only
	@Query(value = "select id from data", nativeQuery = true )
	List<Integer> Id();
	
	//get the count of data from the particular period
	@Query(value = "select * from data where date(time_stamp) between ?1 and ?2", nativeQuery = true)
	List<Data> filterByDate(String start, String end);
	
	// filter data by the Result
	@Query(value = "select * from data where result=?1", nativeQuery = true)
	List<Data> filterByResult(String result);
	
}

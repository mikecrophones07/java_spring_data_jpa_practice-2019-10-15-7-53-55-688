package com.tw.apistackbase.repository;

import com.tw.apistackbase.core.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
    @Query("SELECT c FROM Company c WHERE c.name = :name")
    Company findOneByName(@Param("name") String name);

    List<Company> findByNameContaining(String name);
}

package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository repository;

    @GetMapping(path = "/all", produces = {"application/json"})
    public Iterable<Company> list() {
        return repository.findAll();
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<Company> list(@RequestParam String name) {
        return repository.findByNameContaining(name);
    }

    @GetMapping(value = "/{name}", produces = {"application/json"})
    public Company getCompanyByName(@PathVariable String name) {
        return repository.findOneByName(name);
    }

    @PatchMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<Company> updateCompanyInfo(@PathVariable Long id, @RequestBody Company company) {
        Optional<Company> fetchedCompany = repository.findById(id);
        if(fetchedCompany.isPresent()){
            Company modifiedCompany = fetchedCompany.get();
            modifiedCompany.setName(company.getName());
            Company savedCompany = repository.save(modifiedCompany);
            return new ResponseEntity<>(savedCompany, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping( value = "/{id}", produces = {"application/json"})
    public ResponseEntity<Company> deleteCompany(@PathVariable Long id) {
        Optional<Company> fetchedCompany = repository.findById(id);
        if(fetchedCompany.isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>(fetchedCompany.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping(produces = {"application/json"})
    public Company add(@RequestBody Company company) {
        return repository.save(company);
    }
}

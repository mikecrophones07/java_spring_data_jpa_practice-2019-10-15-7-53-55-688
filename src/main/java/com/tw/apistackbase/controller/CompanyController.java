package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository repository;

    @GetMapping(produces = {"application/json"})
    public Iterable<Company> list() {
        return repository.findAll();
    }

    @GetMapping(value = "/{name}", produces = {"application/json"})
    public Company getCompanyByName(@PathVariable String name) {
        return repository.findOneByName(name);
    }

    @PutMapping(produces = {"application/json"})
    public Company updateCompanyInfo(@RequestBody Company company) {
        Company company1 = repository.getOne(company.getId());
        company1.setName(company.getName());
        company1.setEmployees(company.getEmployees());
        company1.setProfile(company.getProfile());
        return repository.save(company1);
    }

    @DeleteMapping( value = "/{id}", produces = {"application/json"})
    public List<Company> deleteCompanyInfo(@PathVariable Long id) {
        repository.deleteById(id);
        return repository.findAll();
    }


    @PostMapping(produces = {"application/json"})
    public Company add(@RequestBody Company company) {
        return repository.save(company);
    }
}

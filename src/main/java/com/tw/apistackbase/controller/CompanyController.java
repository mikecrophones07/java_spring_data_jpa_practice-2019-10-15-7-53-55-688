package com.tw.apistackbase.controller;

import com.tw.apistackbase.Service.CompanyService;
import com.tw.apistackbase.core.Company;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    public static final String COMPANY_NOT_FOUND = "Company Not found!";
    @Autowired
    CompanyService service;

    @GetMapping(path = "/all", produces = {"application/json"})
    public Iterable<Company> getAll(@RequestParam (required = false, defaultValue = "1") Integer page,
                                  @RequestParam (required = false, defaultValue = "5") Integer pageSize) {
        return service.getAllCompany(page, pageSize);
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<Company> getAll(@RequestParam(required = false, defaultValue = "") String name) {
       return service.getAllCompanyContainingName(name);
    }

    @GetMapping(value = "/{name}", produces = {"application/json"})
    public Company getCompanyByName(@PathVariable String name) {
        return service.getCompanyByName(name);
    }

    @PatchMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<Company> updateCompanyInfo(@PathVariable Long id, @RequestBody Company company) throws NotFoundException {
        Company updatedCompany = service.updateCompanyInfo(id, company);
        if(Objects.nonNull(updatedCompany)){
            return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
        }
        throw new NotFoundException(COMPANY_NOT_FOUND);
    }

    @DeleteMapping( value = "/{id}", produces = {"application/json"})
    public ResponseEntity<Company> deleteCompany(@PathVariable Long id) throws NotFoundException {
        Optional<Company> fetchedCompany = service.deleteCompany(id);
        if(Objects.nonNull(fetchedCompany)){
            return new ResponseEntity<>(fetchedCompany.get(), HttpStatus.OK);
        }
        throw new NotFoundException(COMPANY_NOT_FOUND);
    }


    @PostMapping(produces = {"application/json"})
    public Company add(@RequestBody Company company) {
        return service.saveCompany(company);
    }
}

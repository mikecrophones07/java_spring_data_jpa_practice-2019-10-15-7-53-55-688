package com.tw.apistackbase.Service;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository repository;

    public Iterable<Company> getAllCompany(Integer page, Integer pageSize){
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    public Iterable<Company> getAllCompanyContainingName(String name){
        return repository.findByNameContaining(name);
    }

    public Company getCompanyByName(String name){
        return repository.findOneByName(name);
    }

    public Company updateCompanyInfo(Long id, Company company){
        Optional<Company> fetchedCompany = repository.findById(id);
        if(fetchedCompany.isPresent()){
            Company modifiedCompany = fetchedCompany.get();
            modifiedCompany.setName(company.getName());
            return repository.save(modifiedCompany);
        }
        return null;
    }

    public Optional<Company> deleteCompany(Long id){
        Optional<Company> fetchedCompany = repository.findById(id);
        if(fetchedCompany.isPresent()){
            repository.deleteById(id);
            return fetchedCompany;
        }
        return null;
    }

    public Company saveCompany(Company company){
        return repository.save(company);
    }
}

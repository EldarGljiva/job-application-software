package com.eldar.firstjobapp.company.impl;

import com.eldar.firstjobapp.company.Company;
import com.eldar.firstjobapp.company.CompanyRepository;
import com.eldar.firstjobapp.company.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id){
        return companyRepository.findById(id)
                .map(existingCompany -> {
                    company.setId(id); // Ensure the ID remains the same
                    companyRepository.save(company); // Save the updated company
                    return true;
                })
                .orElse(false);
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id){
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + id + " not found"));
    }

}

package com.eldar.firstjobapp.review.impl;

import com.eldar.firstjobapp.company.Company;
import com.eldar.firstjobapp.company.CompanyService;
import com.eldar.firstjobapp.review.Review;
import com.eldar.firstjobapp.review.ReviewRepository;
import com.eldar.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Review getReviewById(Long companyId, Long id) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long id, Review updatedReview) {
        if(companyService.getCompanyById(companyId) != null ){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(id);
            reviewRepository.save(updatedReview);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long id) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(id)){
            Review review = reviewRepository.findById(id).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(company, companyId);
            reviewRepository.deleteById(id);
            return true;
        }else{
            return false;
        }

    }

}

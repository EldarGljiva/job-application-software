package com.eldar.firstjobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId, Review review);
    Review getReviewById(Long companyId, Long id);
    boolean updateReview(Long companyId, Long id, Review review);
    boolean deleteReview(Long companyId, Long id);
}

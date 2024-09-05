package com.eldar.firstjobapp.review;

import com.eldar.firstjobapp.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);

    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean isValid = reviewService.addReview(companyId, review);
        if(isValid){
            return new ResponseEntity<>("Review added succesfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review has not been added", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long id){
        return new ResponseEntity<>(reviewService.getReviewById(companyId, id),HttpStatus.OK);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long id, @RequestBody Review review){
        boolean isUpdated = reviewService.updateReview(companyId, id, review);
        if(isUpdated){
            return new ResponseEntity<>("Review updated Succesfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review NOT UPDATED", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long id){
        boolean isDeleted = reviewService.deleteReview(companyId, id);
        if(isDeleted){
            return new ResponseEntity<>("Review Deleted Succesfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review NOT DELETED", HttpStatus.NOT_FOUND);
        }
    }

}

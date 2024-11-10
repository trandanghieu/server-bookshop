package com.tdh.bookstore.controller;

import com.tdh.bookstore.model.Review;
import com.tdh.bookstore.model.User;
import com.tdh.bookstore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review, @RequestParam Long userId){
        User user = new User();
        user.setId(userId);
        return ResponseEntity.ok(reviewService.addReview(review, user));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Review>> getReviewsByBookId(@PathVariable Long bookId){
        return ResponseEntity.ok(reviewService.getReviewsByBookId(bookId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

}

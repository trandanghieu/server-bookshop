package com.tdh.bookstore.controller;

import com.tdh.bookstore.model.Review;
import com.tdh.bookstore.model.User;
import com.tdh.bookstore.request.ReviewRequestDTO;
import com.tdh.bookstore.response.ReviewResponseDTO;
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
    public ResponseEntity<ReviewResponseDTO> addReview(@RequestBody ReviewRequestDTO reviewRequest) {
        ReviewResponseDTO reviewResponse = reviewService.addReview(reviewRequest);
        return ResponseEntity.ok(reviewResponse);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByBookId(@PathVariable Long bookId) {
        return ResponseEntity.ok(reviewService.getReviewsByBookId(bookId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

}

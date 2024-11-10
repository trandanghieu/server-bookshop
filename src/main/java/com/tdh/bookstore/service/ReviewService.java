package com.tdh.bookstore.service;

import com.tdh.bookstore.model.Review;
import com.tdh.bookstore.model.User;
import com.tdh.bookstore.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Review review, User user){
        review.setUser(user);
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByBookId(Long bookId){
        return reviewRepository.findByBookId(bookId);
    }

    public void deleteReview(Long id){
        reviewRepository.deleteById(id);
    }
}

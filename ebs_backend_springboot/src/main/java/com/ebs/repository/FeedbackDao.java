package com.ebs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebs.model.Feedback;

public interface FeedbackDao extends JpaRepository<Feedback, Long>{

}

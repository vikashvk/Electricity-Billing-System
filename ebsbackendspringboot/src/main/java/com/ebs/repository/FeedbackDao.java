package com.ebs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebs.model.Feedback;
@Repository
public interface FeedbackDao extends JpaRepository<Feedback, Long>{

}

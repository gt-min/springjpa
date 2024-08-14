package com.min.springjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.min.springjpa.entity.Meeting;
import com.min.springjpa.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
  List<Reply> findByRefid(Meeting meeting);
  List<Reply> findByRefidId(int id);
}
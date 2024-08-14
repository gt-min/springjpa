package com.min.springjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.min.springjpa.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {
  List<Meeting> findByTitleContains(String keyword);
  List<Meeting> findByName(String name);
}
package com.min.springjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.min.springjpa.entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
  List<Visitor> findByMemoContains(String keyword);
}

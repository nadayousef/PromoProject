package com.example.Repositories;

import com.example.Models.Segmentation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepo extends CrudRepository <Segmentation,String> {
}

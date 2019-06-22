package com.swallow.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swallow.bean.sqlBean.ListingFacility;

import java.util.List;

public interface ListingFacilityJpa extends JpaRepository<ListingFacility, Integer>{
    List<ListingFacility> findByPid(Integer pid);
}

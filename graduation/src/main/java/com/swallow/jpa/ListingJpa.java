package com.swallow.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swallow.bean.sqlBean.Listing;

public interface ListingJpa extends JpaRepository<Listing, Integer> {
}




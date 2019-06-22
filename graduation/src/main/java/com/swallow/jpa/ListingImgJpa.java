package com.swallow.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swallow.bean.sqlBean.ListingImg;

import java.util.List;

public interface ListingImgJpa extends JpaRepository<ListingImg, Integer>{
    List<ListingImg> findByPid(Integer pid);
}

package com.swallow.service;

import java.util.List;

import com.swallow.bean.sqlBean.Listing;
import com.swallow.bean.sqlBean.ListingFacility;
import com.swallow.bean.sqlBean.ListingImg;

public interface ListingService {

    void save(Listing listing, List<ListingFacility> facility, List<ListingImg> img);
}

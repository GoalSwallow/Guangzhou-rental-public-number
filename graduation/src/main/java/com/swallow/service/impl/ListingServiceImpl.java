package com.swallow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swallow.bean.sqlBean.Listing;
import com.swallow.bean.sqlBean.ListingFacility;
import com.swallow.bean.sqlBean.ListingImg;
import com.swallow.jpa.ListingFacilityJpa;
import com.swallow.jpa.ListingImgJpa;
import com.swallow.jpa.ListingJpa;
import com.swallow.service.ListingService;

@Service("listing")
public class ListingServiceImpl implements ListingService {
	
	@Autowired
	ListingJpa listingJpa;
	@Autowired
	ListingFacilityJpa facilityJpa;
	@Autowired
	ListingImgJpa imgJpa;

	@Transactional
	@Override
	public void save(Listing listing, List<ListingFacility> facilities, List<ListingImg> imgs) {
		if(listing != null){
			listingJpa.save(listing);
		}
		if(facilities != null && facilities.size() > 0){
			for (ListingFacility facility : facilities) {
				facility.setPid(listing.getId());
			}
			facilityJpa.saveAll(facilities);
		}
		if(imgs != null && imgs.size() > 0) {
			for (ListingImg img : imgs) {
				img.setPid(listing.getId());
			}
			imgJpa.saveAll(imgs);
		}
	}

}

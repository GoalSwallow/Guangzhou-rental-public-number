package com.swallow.mapper;

import com.swallow.bean.sqlBean.ListingFacility;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ListingFacilityMapper {

    @Select("select f.id," +
            "(select value from dictionary d where d.type='facility' and d.key=f.facility) facility " +
            "from listing_facility f " +
            "where f.pid=#{pid}" +
            "order by f.id, f.create_time desc, f.update_time desc")
    List<Map<String, String>> getFacilityByPid(String pid);
}

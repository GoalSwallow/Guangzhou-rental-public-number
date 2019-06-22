package com.swallow.mapper;

import com.swallow.bean.sqlBean.ListingImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ListingImgMapper {

    @Select("select * from listingImg where pid = #{pid} order by id,createtime desc")
    List<ListingImg> getImgByPid(String pid);
}

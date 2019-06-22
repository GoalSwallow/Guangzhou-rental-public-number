package com.swallow.bean.sqlBean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
public class ListingFacility {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    //配套设施value
    private String facility;
    //listing的id为外键
    private Integer pid;
    //创建时间
    @CreationTimestamp
    private Timestamp createTime;
    //最近更新时间
    @UpdateTimestamp
    private Timestamp updateTime;
	
}


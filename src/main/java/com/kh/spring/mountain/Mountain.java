package com.kh.spring.mountain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Mountain {

	@Id
   private Long mntilistno; //산코드
	
   private String mntiname; //산이름
   private String mntiadd; //산위치
   private String mntihigh; //산높이
   private String mntiadmin; //산관리주체
   private String mntiadminnum; //산관리자전화번호
   
	@Column(columnDefinition = "VARCHAR2(2000 CHAR)")
   private String mntidetails; //산정보
}

package com.kh.spring.mountain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.kh.spring.common.util.file.FileInfo;
import com.kh.spring.member.Member;

import lombok.Data;
@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class MountainImg {

	@Id
    private Long mntidx; //산코드
	
    private String imgfilename; //이미지
   
	@ManyToOne
	@JoinColumn(name = "mntilistno")
	private Mountain mountain; //산idx
	
}

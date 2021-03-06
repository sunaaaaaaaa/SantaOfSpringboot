package com.kh.spring.mountain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.board.BoardService;
import com.kh.spring.common.validator.ValidateResult;
import com.kh.spring.member.validator.JoinForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("mountain")
public class MountainController {

	private MountainService mountainService;
	
	public MountainController(MountainService mountainService) {
		super();
		this.mountainService = mountainService;
	}
	
	/* Model 속성명 자동 생성 규칙
	 *   com.myapp.Product becomes "product"
		 com.myapp.MyProduct becomes "myProduct"
		 com.myapp.UKProduct becomes "UKProduct"
	 */
	
	@GetMapping("mountain-list")
	public void mountainList(Model model,@RequestParam(required = false, defaultValue = "1") int page) {
		 model.addAllAttributes(mountainService.findMountainsByPage(page));
		//String result = mountainService.getData(); 데이터 insert
	}
	
}

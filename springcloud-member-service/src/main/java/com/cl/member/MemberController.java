package com.cl.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MemberController {
		
	@RequestMapping(value="/getAll")
	public List<String> getAll(){
		List<String> list = new ArrayList<String>();
		list.add("chenling");
		list.add("yaoxia");
		list.add("yanshan");
		return list;
	}
}

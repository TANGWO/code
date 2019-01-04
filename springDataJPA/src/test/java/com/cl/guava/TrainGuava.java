 package com.cl.guava;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;

/**
 * @author chenling
 * @date 2019/01/03
 */
public class TrainGuava {
	
	public static void main(String[] args) {
		
	}
	
	
	@Test
	public  void isPresent() {
		String [] str= {"1","2","3"};
		String [] str1= {"1","2","3"};
		List<String> list = Lists.newArrayList(str);
		//list= Lists.newArrayList();
		boolean present = Optional.of(list).isPresent();
		Set<List<String>> asSet = Optional.of(list).asSet();
		System.out.println(list);
		System.out.println(present);
		System.out.println(asSet);
		boolean equal = Objects.equal(str, str);
		System.out.println(equal);
	}
	
	

}

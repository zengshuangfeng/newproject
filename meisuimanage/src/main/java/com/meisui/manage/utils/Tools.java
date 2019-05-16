package com.meisui.manage.utils;

import java.util.ArrayList;
import java.util.List;

public class Tools {

	public static <T> List<T> compare(List<T> t1, List<T> t2) {    
	      List<T> list2 = new ArrayList<T>();    
	      for (T t : t2) {    
	          if (!t1.contains(t)) {    
	              list2.add(t);    
	          }    
	      }    
	      return list2;    
	  } 
}

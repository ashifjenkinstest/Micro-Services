package com.coronavirus.garbage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
 
@Component
public interface CanvasjsChartDao {
 
	List<List<Map<Object, Object>>> getCanvasjsChartData();
 
}
 
 

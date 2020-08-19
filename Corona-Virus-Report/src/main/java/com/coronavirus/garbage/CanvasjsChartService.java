package com.coronavirus.garbage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
 
@Service
public interface CanvasjsChartService {
 
	List<List<Map<Object, Object>>> getCanvasjsChartData();
 
}
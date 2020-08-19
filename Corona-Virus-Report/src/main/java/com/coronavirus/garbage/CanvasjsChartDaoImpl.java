package com.coronavirus.garbage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
 

@Service
public class CanvasjsChartDaoImpl implements CanvasjsChartDao {
 
	@Override
	public List<List<Map<Object, Object>>> getCanvasjsChartData() {
		return CanvasjsChartData.getCanvasjsDataList();
	}
 
}
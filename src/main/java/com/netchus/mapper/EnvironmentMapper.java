package com.netchus.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.netchus.domain.EnvironmentVO;

public interface EnvironmentMapper {

	public List<EnvironmentVO> getList();

	public ArrayList<HashMap<String, Object>> getTest();
	
	public int insert(List<Map<String,Object>> data);
	
}

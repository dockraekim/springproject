package com.netchus.mapper;


import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netchus.domain.EnvironmentVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MapperTests {

	
	
	public EnvironmentMapper getMapper() {
		return mapper;
	}

	public void setMapper(EnvironmentMapper mapper) {
		this.mapper = mapper;
	}

	@Autowired
	private EnvironmentMapper mapper;
	
	@Test
	public void getEnvList() {
		
		List<EnvironmentVO> list = mapper.getList();
//		List<Map<Object,Object>> list = mapper.getTest();
		for (EnvironmentVO e : list) {
			System.out.println(e.getId());
		}
		
	}
	// db 연결 완료
	
	// select 해온 값들을 page 하나 더 만들어서 가져온 값 뿌려주기,
	
	// api 콜해서 insert 해서 test table 에 넣는 메소드 하니
	
	
}
 
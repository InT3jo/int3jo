package project3.yakdo.repository.mybatis;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project3.yakdo.repository.BBSRepository;

@Repository
@RequiredArgsConstructor
public class BBSMybatisRepository implements BBSRepository{

	private final BBSMapper bbsMapper;
	
}

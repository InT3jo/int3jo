package project3.yakdo.repository.mybatis;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project3.yakdo.repository.DrugsRepository;

@Repository
@RequiredArgsConstructor
public class DrugsMybatisRepository implements DrugsRepository{

	private final DrugsMapper drugsMapper;
	
}

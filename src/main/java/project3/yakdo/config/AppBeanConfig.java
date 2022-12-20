package project3.yakdo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import project3.yakdo.repository.BBSRepository;
import project3.yakdo.repository.mybatis.BBSMapper;
import project3.yakdo.repository.mybatis.BBSMybatisRepository;

@Configuration
@RequiredArgsConstructor
public class AppBeanConfig {

	private final BBSMapper BBSMapper;


	@Bean
	public BBSRepository BBSRepository() {
		return new BBSMybatisRepository(BBSMapper);
		// MemberMapper 같이 주입
//		return new ListMemberRepository();
	}


}

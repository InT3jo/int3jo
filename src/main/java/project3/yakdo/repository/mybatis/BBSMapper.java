package project3.yakdo.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.BBSInfo;

@Mapper
public interface BBSMapper {
	
	public Integer insert(BBS BBS); // 게시글 생성 Create

	public BBS selectByBBS_no(int BBS_no); // 게시글 읽기 Read

	public List<BBS> selectByBBS_show_0(); // 게시글 목록 출력
	
	public List<BBSInfo> selectBBSInfoByBBS_show_0();

	public void update(int BBS_no, BBS BBS); // 게시글 수정 Update

	public void updateBBS_show_1_ByBBS_no(int BBS_no); // 본인삭제 / 게시글 전체목록에서 숨기기

	public void updateBBS_show_2_ByBBS_no(int BBS_no); // 관리자삭제 / 게시글 전체목록에서 숨기기

}

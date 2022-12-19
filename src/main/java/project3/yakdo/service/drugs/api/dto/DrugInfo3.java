package project3.yakdo.service.drugs.api.dto;

import lombok.Data;

@Data
public class DrugInfo3 {
	private String itemSeq; // 품목일련번호
	private String itemEngName; // 제품명(영)
	private String classNo; // 분류번호
	private String className; // 분류명
	private String ediCode; // 보험코드
}

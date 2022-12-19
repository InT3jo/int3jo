package project3.yakdo.service.drugs.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class DrugInfo2 {
	private String itemSeq; // 품목일련번호
	private String itemName; // 아이템 이름
	private String chart; // 성상
	private String etcOtcName; // 전문/일반
	private String entpName; // 제조사명
	private String efcyQesitm; // 효능 
	private String useMethodQesitm; // 용법
	private String atpnQesitm; // 주의사항
	private List<String> ingrNameList; // 성분명
}

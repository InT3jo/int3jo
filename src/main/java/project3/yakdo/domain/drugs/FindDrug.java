package project3.yakdo.domain.drugs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindDrug {
	private String itemSeq; // 품목일련번호
	private String printFront; // 표시(앞)
	private String printBack; // 표시(뒤)
	private String DrugShape; // 의약품모양
	private String colorClass1; // 색깔(앞)
	private String colorClass2; // 색깔(뒤)
	private String lineFront; // 분할선(앞)
	private String lineBack; // 분할선(뒤)
	private String lengLong; // 크기(장축)
	private String lengShort; // 크기(단축)
	private String thick; // 크기(두께)
	private String markCodeFrontAnal; // 마크내용(앞)
	private String markCodeBackAnal; // 마크내용(뒤)
	private String markCodeFront; // 마크코드(앞)
	private String markCodeBack; // 마크코드(뒤)
	private String markCodeFrontImg; // 마크이미지(앞)
	private String markCodeBackImg; // 마크이미지(뒤)
	
	public FindDrug() {} // 기본생성자
}

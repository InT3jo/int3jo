package project3.yakdo.domain.drugs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugMark {
	private String markCode; // 마크설명
	private String markAnal; // 코드
	private String markImg; // 마크그림
}

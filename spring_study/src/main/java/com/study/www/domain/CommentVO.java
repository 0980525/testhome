package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {

	private long cno;
	private long bno;
	private String content;
	private String writer;
	private String regAt;
	private String modAt;
	
}

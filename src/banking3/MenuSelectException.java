package banking3;

/*
 개발자가 직접 정의한 예외처리 클래스
 */

public class MenuSelectException  extends Exception {

	public MenuSelectException() {
		super("1 ~ 5 사이의 정수를 입력해주세요.");
	}
}

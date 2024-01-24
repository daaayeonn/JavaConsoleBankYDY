package banking4;

/*
 개발자가 직접 정의한 예외처리 클래스
 */

public class MenuSelectException  extends Exception {

	public MenuSelectException() {
		super("\n잘못 입력하셨습니다.\n1 ~ 5 사이의 숫자를 입력해주세요.");
	}
}

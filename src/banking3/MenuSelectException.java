package banking3;

/*
 개발자가 직접 정의한 예외처리 클래스
 */

public class MenuSelectException  extends Exception {

	public MenuSelectException() {
		super();
		System.out.println("\n잘못 입력하셨습니다.");
		System.out.println("1 ~ 5 사이의 숫자를 입력해주세요.");
	}
}

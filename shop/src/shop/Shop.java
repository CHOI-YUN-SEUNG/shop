package shop;

import java.util.Random;

public class Shop {
	private UserManager userManager = UserManager.getInstance();
	private ItemManager itemManger = ItemManager.getInstance();

	// 유저 -
	// ㄴ 회원가입 [X]
	// ㄴ 탈퇴 [X]
	// ㄴ 로그인 [X]
	// ㄴ 로그아웃 [X]
	// ㄴ 쇼핑하기 [X]
	// ㄴ 마이페이지
	// ㄴ 내장바구니 [X]
	// ㄴ 항목삭제 [X]
	// ㄴ 수량수정 [X]
	// ㄴ 결제 [X]
	// 파일
	// ㄴ 자동저장 [X]
	// ㄴ 자동로드 [X]
	// 관리자 -
	// ㄴ 아이템
	// ㄴ 등록 [X]
	// ㄴ 삭제 [X]
	// ㄴ 수정 [X]
	// ㄴ 조회(총 매출) [X]

	// 유저
	// 회원가입/탈퇴
	// 로그인/로그아웃
	// 쇼핑하기
	// 마이프로젝트(내장바구니,항목삭제,수량수정,결제(영수증 출력))//있던 아이템을 털고, 개수 빼기 처리하고 매출액+?//결제할때
	// 판매여부(갯수차이) 다시 따져보면 되곘다.
	// 저동저장/로드// 유저정보, 아이템 정보,관리자정보

	// 관리자 //code를 하나 따로 관리자라고 지정 shop에 넣지 뭐?
	// 아이템 등록,삭제,수정
	// 조회(총 매출)

	public void run() {

	}
}

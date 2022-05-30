package session;

import java.util.HashMap;
import java.util.Map;

public class Session {

	private String sessionId;
	private Map<String,Object> attributes; //장바구니

	public Session() {}
	public Session(String sessionId) {
		this.sessionId = sessionId;
		attributes = new HashMap<>();
	}
	public String getSessionId() {
		return sessionId;
		//return toString();
	}

	//장바구니에 추가 .. 상품 목록 뜰때 밑에 띄워서 입력된 값 저장한다
	public void setAttribute(String name, Object value) {//cart , Map<Goods, Integer>
		attributes.put(name,value);
	}

	//조회(Map에 key에 해당하는 value 찾기)
	public Object getAttribute(String name) {//cart
		return attributes.get(name);
	}

	//제거(장바구니를 비울때 사용한다)
	public void removeAttribute(String name) {//cart
		attributes.remove(name);
	}


	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}


	@Override
	public String toString() {
		return "《" + sessionId +"》" + " 님의 장바구니 " + "[" + attributes + "]"+"\n";
	}

	/*
	@Override
	public int hashCode() {
		return sessionId.hashCode(); //hash코드: 배열의 index
	}/*

	/**
	 * 같은 객체라는 뜻은 hashCode가 같아야하고,
	 * equals의 결과가 true여야한다.
	 *
	 *  hash코드가 다르면 무조건 다른 객체
	 *  hash코드가 같으면 같은 객체일수도, 다른 객체일수도 있다.
	 * */
	/*
	@Override
	public boolean equals(Object obj) {
		Session other = (Session) obj;
		if(sessionId.equals(other.sessionId)) {
			return true;
		}else {
			return false;
		}

	}*/

	/*
	//테스트용 메인 메소드
	public static void main(String[] args) {
		System.out.println("---테스트중입니다....---");
		System.out.println(new Session().toString());
		
	}*/
}////////////////////

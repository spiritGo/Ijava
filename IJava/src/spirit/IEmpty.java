package spirit;

public class IEmpty {
	
	public static void main(String[] args) {		
		System.out.println(isEmptuy(""));
	}

	/**
	 * ��stringΪnullʱ��NullPointerException����string==����ʱ������true
	 * @param string
	 * @return
	 */
	private static boolean isEmptuy(String string) {
		return string.isEmpty();
	}
}

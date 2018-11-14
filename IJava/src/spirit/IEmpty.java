package spirit;

public class IEmpty {
	
	public static void main(String[] args) {		
		System.out.println(isEmptuy(""));
	}

	/**
	 * 当string为null时报NullPointerException，当string==“”时，返回true
	 * @param string
	 * @return
	 */
	private static boolean isEmptuy(String string) {
		return string.isEmpty();
	}
}

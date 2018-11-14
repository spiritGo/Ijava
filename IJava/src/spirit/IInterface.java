package spirit;

public interface IInterface {
	void one();
	void two();
	void three();
	
	default void four() {
		System.out.println("four");
	}
	
	default void five() {
		System.out.println("five");
	}
}

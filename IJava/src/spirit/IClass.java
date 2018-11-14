package spirit;

public class IClass implements IInterface {

	@Override
	public void one() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void two() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void three() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void four() {
		// TODO Auto-generated method stub
		IInterface.super.four();
	}
	
	@Override
	public void five() {
		System.out.println("ok");
	}
	
	public static void main(String[] args) {
		IClass iClass = new IClass();
		iClass.five();
		iClass.four();
	}

}

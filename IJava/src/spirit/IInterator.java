package spirit;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 迭代器的使用，用于数组迭代
 * @author sprite
 *
 */
public class IInterator {
	static String[] strings = {"1","2"};
	public static void main(String[] args) {
		java.util.List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		
		for(Iterator<String> i= list.iterator();i.hasNext();) {
			String next = i.next();
			System.out.println(next);
		}
		
	}
}

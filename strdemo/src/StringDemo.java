import java.util.Arrays;
import java.util.LinkedList;


public class StringDemo {
	public static void main(String[] args) {
		String cmd = "4";
		LinkedList<String> someList = new LinkedList<String>(Arrays.asList(cmd.split("-")));
		someList.removeAll(Arrays.asList(new String[]{"4"}));
		someList.push("4");
		if (someList.size() > 4) someList.removeLast();
		Object[] someArray = someList.toArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < someArray.length; i++) {
			if (i > 0) sb.append("-");
			sb.append(someArray[i]);
		}
		System.out.println(sb.toString());
		
		
	}
}

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class StringDemo {
	public static void main(String[] args) {
		String cmd = "4-3-2-1";
		LinkedList<String> someList = new LinkedList<String>(Arrays.asList(cmd.split("-")));
		someList.push("5");
		someList.push("6");
		someList.push("7");
		someList.push("8");
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

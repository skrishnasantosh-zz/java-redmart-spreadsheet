package main.java.com.redmart.interview;

public class ConsoleOutputSource implements IOutputSource {

	@Override
	public void writeLine(String line) {
		System.out.println(line);
	}

}

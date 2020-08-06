package water;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class watercut {

	public static void main(String[] args) throws IOException {
		String line;
		int pointer = 0;
		boolean flag = true;
		String view = "";
		StringBuilder sb = new StringBuilder();
		ArrayList<String> viewgroup = new ArrayList<String>();
		InputStreamReader isr = new InputStreamReader(new FileInputStream("./630-765.txt"), "UTF-16");

		BufferedReader read = new BufferedReader(isr);
		while ((line = read.readLine())!=null) {
			//System.out.println(line);
			String[] token = line.split("　");

			for (int i = 0; i < token.length; i++) {
				if (token[i].contains("(Na)") || token[i].contains("(Nb)") || token[i].contains("(Nc)")) {
					pointer++;
				} else {
					flag = false;
				}
				if (pointer >= 2 && flag == false) {
					while (pointer >= 1) {
						view = view + token[i - pointer];
						pointer--;
					}
					System.out.println(view);
					sb.append(view);
					sb.append("\n");
					flag = true;
				} else if (pointer >= 2 && token.length - 1 == i) {
					while (pointer >= 1) {
						view = view + token[i - pointer+1];
						pointer--;
					}
					System.out.println(view);
					sb.append(view);
					sb.append("\n");
					flag = true;
				} else if (flag == false) {
					pointer = 0;
				}
				flag = true;
				view = "";
			}
		}
		read.close();
		PrintWriter pw = new PrintWriter(new File("./test.csv"));
		pw.write(sb.toString());
		pw.close();
	}

}

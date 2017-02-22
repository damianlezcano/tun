package ar.com.q3s.shell;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ShellCommandBean {

	public static void main(String[] args) throws IOException, InterruptedException {
	    String password = LinuxCommand.getPasswdForRoot();
	    System.out.println("stdout of 'id':");
	    Process p = LinuxCommand.runFromRoot("id",password);
	    System.out.print(streamToString(p.getInputStream()));
//	    System.out.println("stdout of 'fdisk -l':");
//	    p = LinuxCommand.runFromRoot("fdisk -l",password);
//	    System.out.print(streamToString(p.getInputStream()));
	}

	private static String streamToString(InputStream inputStream) {
		Scanner s = new Scanner(inputStream).useDelimiter("\\A");
		String result = s.hasNext() ? s.next() : "";
		return result;
	}
	
}
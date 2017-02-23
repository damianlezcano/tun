package ar.com.q3s.shell;

import java.io.IOException;

public interface ShellCommand {

	void kill(String command) throws IOException;
	void open(String command) throws IOException;
	String pid(String command) throws IOException;
	
}
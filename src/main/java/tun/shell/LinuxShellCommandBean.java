package tun.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinuxShellCommandBean implements ShellCommand {

	public void kill(String command) throws IOException {
		Runtime.getRuntime().exec(new String[]{"kill","-9",command});
	}

	public void open(String command) throws IOException {
		Runtime.getRuntime().exec(command);
	}

	public String pid(String command) throws IOException {
		Process p;
		String pid = null;
		try {
			p = Runtime.getRuntime().exec(new String[]{"ps","-eo","pid,args"});
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
			while ((line = reader.readLine())!= null) {
				if(line.indexOf(command) != -1){
					String[] row = line.trim().split(" ");
					pid = row[0]; 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pid;
	}
	
}
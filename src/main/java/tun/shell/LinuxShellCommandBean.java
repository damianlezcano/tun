package tun.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinuxShellCommandBean implements ShellCommand {

	@Override
	public void kill(String command) throws IOException {
		Runtime.getRuntime().exec(new String[]{"kill","-9",command});
	}

	@Override
	public void open(String command) throws IOException {
		Runtime.getRuntime().exec("x-terminal-emulator -e " + command);
	}

	@Override
	public String pid(String command) throws IOException {
		Process p;
		String pid = null;
		try {
			p = Runtime.getRuntime().exec(new String[]{"ps","-eo","pid,args"});
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
			while (((line = reader.readLine())!= null) && pid == null) {
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
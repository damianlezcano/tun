package tun.shell;

public class ShellCommandFactory {

	public static ShellCommand get(){
		ShellCommand shell;
		String soname = System.getProperty("os.name");
		if("Linux".equalsIgnoreCase(soname)){
			shell = new LinuxShellCommandBean();
		}else{
			shell = new DefaultShellCommandBean();
		}
		return shell;
	}
	
}

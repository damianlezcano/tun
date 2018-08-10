package tun.shell;

import java.io.IOException;

import javax.swing.JOptionPane;

public class DefaultShellCommandBean implements ShellCommand {

	public void kill(String command) throws IOException {
		JOptionPane.showMessageDialog(null, "Función no implementada para tu sistema operativo");
	}

	public void open(String command) throws IOException {
		JOptionPane.showMessageDialog(null, "Función no implementada para tu sistema operativo");
	}

	public String pid(String command) throws IOException {
		return null;
	}

}

package tun.shell;

import java.io.IOException;

import javax.swing.JOptionPane;

public class DefaultShellCommandBean implements ShellCommand {

	@Override
	public void kill(String command) throws IOException {
		JOptionPane.showMessageDialog(null, "Función no implementada para tu sistema operativo");
	}

	@Override
	public void open(String command) throws IOException {
		JOptionPane.showMessageDialog(null, "Función no implementada para tu sistema operativo");
	}

	@Override
	public String pid(String command) throws IOException {
		return null;
	}

}

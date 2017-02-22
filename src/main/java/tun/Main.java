/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tun;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.UIManager;

/**
 *
 * @author damian
 */
public class Main {

	private static final String FILE_PID = "tun.pid";
	
	private View view = new View();
	
	private Map<String, Set<String>> hosts;
	private Map<String, String> pids;

	public void init(String filename) throws IOException {

		//Proceso el archivo pasado como parametro
		hosts = readHosts(filename);
		//Procesamos el arhichivo que se relaciona el comando con el pid
		pids = readPIDs(FILE_PID);

		Iterator itKey = hosts.entrySet().iterator();
		while (itKey.hasNext()) {
			Entry<String,Set<String>> entryKey = (Entry<String,Set<String>>)itKey.next();
			Iterator itHost = entryKey.getValue().iterator();
			while (itHost.hasNext()) {
				String entryHost = (String)itHost.next();
				Item item = build(entryHost,entryKey.getKey());
				view.getPnlContainer().add(item);
				if(itKey.hasNext() || itHost.hasNext()){
					view.getPnlContainer().add(new JSeparator());					
				}
			}
		}

		view.setVisible(true);
		view.pack();
		setLookAndField();
	}

	public Item build(String title, String description){
		Item item = new Item();
		item.getLblTitle().setText(title);
		item.getLblDescription().setText(description);
		setIcon(item.getLblIcon(),pids.get(title));
		return item;
	}
	
	private void setIcon(JLabel lblIcon, String pid) {
		if(pid == null){
			lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("error.png")));
		}else{
			lblIcon.setToolTipText(String.format("PID: %s", pid));
			lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("ok.png")));			
		}
	}

	private Map<String, String> readPIDs(String filename) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		FileInputStream fstream = new FileInputStream(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			String[] row = strLine.split("=");
			map.put(row[0],row[1]);
			System.out.println(strLine);
		}
		br.close();
		return map;
	}

	private Map<String, Set<String>> readHosts(String filename) throws IOException {
		Map<String, Set<String>> map = new TreeMap<String, Set<String>>();
		FileInputStream fstream = new FileInputStream(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		String key = "Undefined";
		while ((strLine = br.readLine()) != null) {
			if(!strLine.isEmpty()){
				if (strLine.startsWith("#")) {
					key = strLine;
				} else {
					Set<String> collection = map.containsKey(key) ? map.get(key) : new HashSet<String>();
					if(!collection.contains(strLine)){
						collection.add(strLine);
					}
					map.put(key, collection);
				}
			}
		}
		br.close();
		return map;
	}

	private void setLookAndField() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		if (args.length == 0) {
			System.err.println("Falta pasar como parametro el archivo de host");
			return;
		}

		Main controller = new Main();
		controller.init(args[0]);
	}

}

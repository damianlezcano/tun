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
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JSeparator;
import javax.swing.UIManager;

/**
 *
 * @author damian
 */
public class Controller {

	private static final String FILE_PID = "tun.pid";
	
	private View view = new View();
	
	private Map<String, String> hosts;
	private Map<String, String> pids;

	public void init(String filename) throws IOException {

		//Proceso el archivo pasado como parametro
		hosts = readHosts(filename);
		//Procesamos el arhichivo que se relaciona el comando con el pid
		pids = readPIDs(FILE_PID);

	    Iterator it = hosts.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<String,String> entry = (Entry<String,String>)it.next();
	        Item item = new Item();
	        item.getLblTitle().setText(entry.getValue());
	        item.getLblDescription().setText(entry.getKey());
	        view.getContentPane().add(item);
	        if(it.hasNext())
	        	view.getContentPane().add(new JSeparator());
	    }
		
		for (Entry<String,String> entry : hosts.entrySet()) {
		}

		view.setVisible(true);
		view.pack();
		setLookAndField();
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

	private Map<String, String> readHosts(String filename) throws IOException {
		Map<String, String> map = new TreeMap<String, String>();
		FileInputStream fstream = new FileInputStream(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		String key = "Undefined";
		while ((strLine = br.readLine()) != null) {
			if(!strLine.isEmpty()){
				if (strLine.startsWith("#")) {
					key = strLine;
				} else {
					map.put(strLine, key);
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

		Controller controller = new Controller();
		controller.init(args[0]);
	}

}

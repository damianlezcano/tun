/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tun;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JCheckBox;
import javax.swing.JSeparator;

import tun.shell.ShellCommand;
import tun.shell.ShellCommandFactory;

/**
 *
 * @author damian
 */
public class Main {

	private static final long SCANNER_SLEEP = 500;

	private View view = new View();
	
	private ShellCommand shell;
	
	private Map<String, Set<String>> hosts;

	public void init(String[] files) throws IOException {

		shell = ShellCommandFactory.get();
		//Proceso el archivo pasado como parametro
		hosts = readHosts(files);
		
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
		
		(new Thread() {
			  public void run() {
	            	do {
	            		try {
		            		for(Component component : view.getPnlContainer().getComponents()){
		            			if(component instanceof Item){
		            				((Item)component).evaluatePid(shell);
		            			}
		            		}
		            		Thread.sleep(SCANNER_SLEEP);
	            		} catch (Exception e) {
	            			e.printStackTrace();
	            		}
					} while (true);
			  }
			 }).start();
		
	}

	public Item build(String title, String description) throws IOException{
		Item item = new Item();
		item.getLblTitle().setText(title);
		item.getLblDescription().setText(description);
		item.evaluatePid(shell);
		
        item.getCheckbox().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		JCheckBox combobox = (JCheckBox) evt.getSource();
            		Item item = (Item)combobox.getParent();
            		if(combobox.isSelected()){
            			shell.open(item.getLblTitle().getText());
            		}else{
            			shell.kill(shell.pid(item.getLblTitle().getText()));
            		}
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
        
        item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                for(Component component : view.getPnlContainer().getComponents()){
        			if(component instanceof Item){
        				Item item = (Item)component;
        				item.bgNotSelect();
        			}
        		}
                Item current  = (Item)evt.getSource();
                current.bgSelect();
            }
        });

        view.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                for(Component component : view.getPnlContainer().getComponents()){
        			if(component instanceof Item){
        				Item item = (Item)component;
        				item.bgNotSelect();
        			}
        		}
            }
        });
        
		return item;
	}

	private Map<String, Set<String>> readHosts(String[] files) throws IOException {
		Map<String, Set<String>> map = new TreeMap<String, Set<String>>();
		for (String filename : files) {
			map.putAll(readHosts(filename));
		}
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
	
	/**
	 * @param args
	 *            the command line arguments
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		final String[] files = processPath(args);
		
		if (files.length == 0) {
			System.err.println("Falta pasar como parametro el archivo de host valido");
			return;
		}

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	try {
            		Main controller = new Main();
            		controller.init(files);					
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
        
	}

	private static String[] processPath(String[] args) {
		List<String> files = new ArrayList<String>(); 
		for(String filename : args){
			if(new File(filename).exists()){
				files.add(filename);
			}else if(new File(buildPath(filename)).exists()){
				files.add(buildPath(filename));
			}
		}
		return files.toArray(new String[]{});
	}
	
	private static String buildPath() {
		String spath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		if(spath.indexOf(".jar") > 0){
			spath = String.format("%s%s%s",(spath.substring(0,spath.lastIndexOf(File.separator))),File.separator,spath);
		}
		return spath;
	}
	
	private static  String buildPath(String name) {
		return String.format("%s%s%s", buildPath(),File.separator,name);
	}

}

package 메모장구현;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class MemoApplication_OnlyCode extends JFrame implements ActionListener {
	
	JFrame main_fr = new JFrame("메모장 만들기");
	FileDialog saveOpen;
	FileDialog readOpen;
	JTextArea area;
	
	public MemoApplication_OnlyCode() {
		JMenuBar main_br = new JMenuBar();
		JMenu file = new JMenu("파일");
		JMenuItem file_load = new JMenuItem("열기");
		JMenuItem file_save = new JMenuItem("저장");
		area = new JTextArea();
		
		file_load.addActionListener(this);
		file_save.addActionListener(this);
		
		file.add(file_load); file.add(file_save);
		main_br.add(file);
		main_fr.setJMenuBar(main_br);
		main_fr.add(area);
		
		main_fr.setBounds(300, 300, 500, 400);
		main_fr.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String itemPressed = e.getActionCommand();
		
		if(itemPressed.equals("저장")) {
			String name = readName();
			
			try {
				save(name);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		if(itemPressed.equals("열기")) {
			String name = readName();
			
			try {
				read(name);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	} // end actionPerformed()
	
	public String readName() {
		readOpen = new FileDialog(main_fr, "문서열기", FileDialog.LOAD);
		readOpen.setVisible(true);
		String fileDir = readOpen.getDirectory();
		String fileName = readOpen.getFile();
		String readfilename;
		readfilename = fileDir + "//" + fileName;
		return readfilename;
	}
	
	public void read(String readfile) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(readfile));
		area.setText("");
		String line = read.readLine();
		
		while(line != null) {
			area.append(line + "\n");
			line = read.readLine();
		}
		read.close();
	}
	
	public String saveName() {
		saveOpen = new FileDialog(main_fr, "문서저장", FileDialog.SAVE);
		saveOpen.setVisible(true);
		String fileDir = saveOpen.getDirectory();
		String fileName = saveOpen.getFile();
		String savefilename;
		savefilename = fileDir + "//" + fileName + ".txt";
		return savefilename;
	}
	
	public void save(String savefile) throws IOException {
		BufferedWriter save = new BufferedWriter(new FileWriter(savefile));
		String line = area.getText();
		save.write(line);
		save.close();
	}

	public static void main(String[] args) {
		MemoApplication_OnlyCode mo = new MemoApplication_OnlyCode();
	}
}

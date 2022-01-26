package �޸��屸��;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

/*
 * �ڹٴ� '�ϳ��� Ŭ����'�� ��� ���� �� �ִ�. �� ���� ��� ��� ����!!
 * �ڹٿ��� �������̽��� �ϳ��� �ƴ϶� '������'�� �ڽĿ��� ���� ��ų �� �ִ�.
 */

public class MemoApplication extends JFrame implements ActionListener {  //ActionListener�� �̺�Ʈ �ڵ鷯
	
	//�غ� �ܰ�
	JFrame main_fr = new JFrame("�޸��� �����");
	FileDialog saveOpen;
	FileDialog readOpen;
	JTextArea area;
	
	//������
	public MemoApplication() {
		//������ ����â ȭ�� �ٹ̱�
		JMenuBar main_br = new JMenuBar();
		JMenu file = new JMenu("����");
		JMenuItem file_load = new JMenuItem("����");
		JMenuItem file_save = new JMenuItem("����");
		area = new JTextArea();
		
		// �� ����, ���� �̶�� JmenuItem ��ü�� Ŭ���ϸ� => '�̺�Ʈ�� �߻��ߴ�' �ǹ�
		// file_load�� ����Ű�� ��ü�� this�� '�̺�Ʈ ��鷯 ������ ��ü�� ������ѳ��ڴ�' ��� �ǹ�
		file_load.addActionListener(this);  //implements ActionListener�� �̸� ���� ������ ��� this���.
		file_save.addActionListener(this);  //����ڰ� Ŭ���Ҷ����� ��ٸ����ִ� => Ŭ���ϸ� actionPerformedȣ�� => e�� �޴´�.
		
		//���۳�Ʈ ���̱�
		file.add(file_load); file.add(file_save);
		main_br.add(file);
		main_fr.setJMenuBar(main_br);
		main_fr.add(area);
		
		main_fr.setBounds(300, 300, 500, 400);
		main_fr.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //���� â �ݱ�
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {  //JmanuItem�� ActionEvent�� �������ִ�.
		//�������̽� �������̵� �ϰ� �������ʹ� �����ڰ� ä���ֱ�(�������� ��)
		String itemPressed = e.getActionCommand();  //"����" or "����"�� itemPressed�� ������.
		
		if(itemPressed.equals("����")) {
			String name = readName();  //����� ���� �޼ҵ� ȣ��
			
			//���Ͽ� ���� �� �ݵ�� '����ó��' ����� �Ѵ�.
			try {
				save(name);  //����� ���� �޼ҵ� ȣ��
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		if (itemPressed.equals("����")) {
			String name = readName(); // ����� ���� �޼ҵ� ȣ��

			// ���Ͽ� ���� �� �ݵ�� '����ó��' ����� �Ѵ�.
			try {
				read(name); // ����� ���� �޼ҵ� ȣ��
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	} // end actionPerformed()
	
	public String readName() {
		readOpen = new FileDialog(main_fr, "��������", FileDialog.LOAD);
		readOpen.setVisible(true);
		String fileDir = readOpen.getDirectory();
		String fileName = readOpen.getFile();
		String readfilename;
		readfilename = fileDir + "//" + fileName;  // /Ư�����ڴ� �տ� /�ϳ� �� �ٿ������.
		return readfilename;
	}
	
	public void read(String readfile) throws IOException {  // throws�� ȣ���� ������ ���ܸ� �����ְڴ�. IOException�� ����¿���ó��.
		// ���� ����� �� ���� �� ������� �а� �ϱ� ���ؼ� => BufferedReader ���
		BufferedReader read = new BufferedReader(new FileReader(readfile));
		area.setText("");
		String line = read.readLine();
		
		while(line != null) {
			area.append(line + "\n");
			line = read.readLine();
		}
		read.close();  // �ڿ� ����
	}
	
	public void save(String savefile) throws IOException {  // throws�� ȣ���� ������ ���ܸ� �����ְڴ�. IOException�� ����¿���ó��.
		// ���� ����� �� ���� �� ������� ���� ��Ű�� ���ؼ� => BufferedWriter ���
		BufferedWriter save = new BufferedWriter(new FileWriter(savefile));
		String line = area.getText();
		save.write(line);
		save.close(); // �ڿ� ����
	}
	
	//FileDialog�� �޸��忡�� ������ �������� ������ â
	public String saveName() {
		saveOpen = new FileDialog(main_fr, "��������", FileDialog.SAVE);
		saveOpen.setVisible(true);
		String fileDir = saveOpen.getDirectory();
		String fileName = saveOpen.getFile();
		String savefilename;
		savefilename = fileDir + "//" + fileName + ".txt";  // /Ư�����ڴ� �տ� /�ϳ� �� �ٿ������.
		return savefilename;  // savefilename�� ���� 63�� �ִ� name�� �Ҵ��Ѵ�.
	}
	
	public static void main(String[] args) {
		//��ü ����
		MemoApplication ma = new MemoApplication();
	}
}

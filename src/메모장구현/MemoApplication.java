package 메모장구현;

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
 * 자바는 '하나의 클래스'만 상속 받을 수 있다. ※ 다중 상속 허용 안함!!
 * 자바에서 인터페이스는 하나가 아니라 '여러개'를 자식에게 구현 시킬 수 있다.
 */

public class MemoApplication extends JFrame implements ActionListener {  //ActionListener는 이벤트 핸들러
	
	//준비 단계
	JFrame main_fr = new JFrame("메모장 만들기");
	FileDialog saveOpen;
	FileDialog readOpen;
	JTextArea area;
	
	//생성자
	public MemoApplication() {
		//윈도우 실행창 화면 꾸미기
		JMenuBar main_br = new JMenuBar();
		JMenu file = new JMenu("파일");
		JMenuItem file_load = new JMenuItem("열기");
		JMenuItem file_save = new JMenuItem("저장");
		area = new JTextArea();
		
		// ※ 열기, 저장 이라는 JmenuItem 객체를 클릭하면 => '이벤트가 발생했다' 의미
		// file_load가 가리키는 객체와 this는 '이벤트 헨들러 리스너 객체를 연결시켜놓겠다' 라는 의미
		file_load.addActionListener(this);  //implements ActionListener를 미리 구현 시켰을 경우 this사용.
		file_save.addActionListener(this);  //사용자가 클릭할때까지 기다리고있다 => 클릭하면 actionPerformed호출 => e가 받는다.
		
		//컴퍼넌트 붙이기
		file.add(file_load); file.add(file_save);
		main_br.add(file);
		main_fr.setJMenuBar(main_br);
		main_fr.add(area);
		
		main_fr.setBounds(300, 300, 500, 400);
		main_fr.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //실행 창 닫기
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {  //JmanuItem은 ActionEvent를 가지고있다.
		//인터페이스 오버라이드 하고 나서부터는 개발자가 채워넣기(개발자의 몫)
		String itemPressed = e.getActionCommand();  //"열기" or "저장"을 itemPressed가 가져옴.
		
		if(itemPressed.equals("저장")) {
			String name = readName();  //사용자 정의 메소드 호출
			
			//파일에 저장 시 반드시 '예외처리' 해줘야 한다.
			try {
				save(name);  //사용자 정의 메소드 호출
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		if (itemPressed.equals("열기")) {
			String name = readName(); // 사용자 정의 메소드 호출

			// 파일에 저장 시 반드시 '예외처리' 해줘야 한다.
			try {
				read(name); // 사용자 정의 메소드 호출
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	} // end actionPerformed()
	
	public String readName() {
		readOpen = new FileDialog(main_fr, "문서열기", FileDialog.LOAD);
		readOpen.setVisible(true);
		String fileDir = readOpen.getDirectory();
		String fileName = readOpen.getFile();
		String readfilename;
		readfilename = fileDir + "//" + fileName;  // /특수문자는 앞에 /하나 더 붙여줘야함.
		return readfilename;
	}
	
	public void read(String readfile) throws IOException {  // throws는 호출한 쪽으로 예외를 던져주겠다. IOException는 입출력예외처리.
		// 파일 입출력 중 열기 시 고속으로 읽게 하기 위해서 => BufferedReader 사용
		BufferedReader read = new BufferedReader(new FileReader(readfile));
		area.setText("");
		String line = read.readLine();
		
		while(line != null) {
			area.append(line + "\n");
			line = read.readLine();
		}
		read.close();  // 자원 해제
	}
	
	public void save(String savefile) throws IOException {  // throws는 호출한 쪽으로 예외를 던져주겠다. IOException는 입출력예외처리.
		// 파일 입출력 중 저장 시 고속으로 저장 시키기 위해서 => BufferedWriter 사용
		BufferedWriter save = new BufferedWriter(new FileWriter(savefile));
		String line = area.getText();
		save.write(line);
		save.close(); // 자원 해제
	}
	
	//FileDialog는 메모장에서 저장을 눌렀을때 나오는 창
	public String saveName() {
		saveOpen = new FileDialog(main_fr, "문서저장", FileDialog.SAVE);
		saveOpen.setVisible(true);
		String fileDir = saveOpen.getDirectory();
		String fileName = saveOpen.getFile();
		String savefilename;
		savefilename = fileDir + "//" + fileName + ".txt";  // /특수문자는 앞에 /하나 더 붙여줘야함.
		return savefilename;  // savefilename은 라인 63에 있는 name에 할당한다.
	}
	
	public static void main(String[] args) {
		//객체 생성
		MemoApplication ma = new MemoApplication();
	}
}

package 메모장구현;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 * 윈도우 프로그램(GUI)
 * 고객관리 시스템 어플리케이션 개발 시 메뉴 만들기
 */

public class MenuMain extends JFrame {  //윈도우 실행창 틀(컨테이너)
	
	//준비 단계
	JFrame main_fr = new JFrame("고객관리 시스템 메뉴 만들기");
	JMenuBar mb;  //메뉴바
	JMenu file, sort, help;  //파일, 정렬, 도움말
	JMenuItem fopen, fsave, fexit, proinfo;  //열기, 저장, 닫기, 프로그램 정보
	JCheckBoxMenuItem sno, sname, schul, sjob;  //번호, 이름, 출신지역, 직업
	
	//생성자 구현
	public MenuMain() {
		mb = new JMenuBar();
		
		file = new JMenu("파일");
		sort = new JMenu("정렬");
		help = new JMenu("도움말");
		
		fopen = new JMenuItem("열기");
		fsave = new JMenuItem("저장");
		fexit = new JMenuItem("닫기");
		
		//논리적 그룹화 => 어느 하나만 체크 할 수 있다.
		ButtonGroup bg = new ButtonGroup();
		sno = new JCheckBoxMenuItem("번호");
		sname = new JCheckBoxMenuItem("이름");
		schul = new JCheckBoxMenuItem("출신지역");
		sjob = new JCheckBoxMenuItem("직업");
		
		bg.add(sno);
		bg.add(sname);
		bg.add(schul);
		bg.add(sjob);
		
		proinfo = new JMenuItem("프로그램 정보");
		
		//객체 붙이기
		file.add(fopen);  //열기
		file.add(fsave);  //저장
		file.addSeparator();  //구분선 넣기
		file.add(fexit);  //닫기
		
		sort.add(sno);  //번호
		sort.add(sname);  //이름
		sort.add(schul);  //출신지
		sort.add(sjob);  //직업
		
		help.add(proinfo);  //프로그램 정보
		
		mb.add(file);  //파일
		mb.add(sort);  //정렬
		mb.add(help);  //도움말
		
		main_fr.setJMenuBar(mb);  //메뉴바
		main_fr.setBounds(200, 200, 500, 300);  //x좌표, y좌표, 넓이, 높이
		main_fr.setVisible(true);  //실행창을 보여달라
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //프로그램 정상종료 시켜라(닫기)
	}
	
	public static void main(String[] args) {
		//객체 생성
		MenuMain menuMain = new MenuMain();
	}
}

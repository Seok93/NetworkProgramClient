import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ClientStart extends JPanel {
	// UI용 멤버 변수
	private JLabel gameName1;
	private JLabel gameName2;
	private JButton start;
	private JButton close;
	private ClientMain mainFrame;
	private JLabel image;
	private ImageIcon backGround;
	private DataManager dManage;
	private boolean c = false;
	// 연결할 서버 ip, port 번호 고정 관리용 멤버 변수
	private final String ip = "127.0.0.1";
	private final int port = 30009;

	// 소켓 통신용 스트림 멤버 변수
	private DataInputStream dis;
	private DataOutputStream dos;

	public ClientStart(ClientMain mainFrame, DataManager dManage) {
		this.mainFrame = mainFrame;
		this.dManage = dManage;
		setLayout(null);

		gameName1 = new JLabel("네트워크");
		gameName1.setBounds(160, 90, 120, 80);
		gameName1.setFont(new Font("TimesRoman", Font.BOLD, 20));
		add(gameName1);

		gameName2 = new JLabel("게임 천국");
		gameName2.setBounds(158, 110, 120, 80);
		gameName2.setFont(new Font("TimesRoman", Font.BOLD, 20));
		add(gameName2);

		start = new JButton(new ImageIcon("startButton.jpg"));
		start.setSize(60, 40);
		start.setLocation(120, 180);
		add(start);

		close = new JButton(new ImageIcon("exitButton.jpg"));
		close.setSize(60, 40);
		close.setLocation(200, 180);
		add(close);

		backGround = new ImageIcon("board.png"); // 배경화면 삽입
		image = new JLabel(backGround);
		image.setBounds(0, 0, 400, 400);
		add(image);

		start.addActionListener(new MyActionListener());
		close.addActionListener(new MyActionListener());

	}

	public void network() {
		// 서버에 접속
		if (c == false) {
			c = true;
			try {
				dManage.setSocket(new Socket(ip, port)); // 소켓 생성
				if (dManage.getSocket() != null) // socket이 null값이 아닐때 즉! 연결되었을때
				{
					dManage.Connection(); // 연결 메소드를 호출
				}
			} catch (UnknownHostException e) {
				JOptionPane.showMessageDialog(null, "주소가 이상해!", "오류", JOptionPane.WARNING_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "소켓 접속 에러!", "오류", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	/*
	 * public void Connection() { // 실직 적인 메소드 연결부분
	 * 
	 * dis = dManage.getDis(); dos = dManage.getDos();
	 * 
	 * // send_Message(id); // 정상적으로 연결되면 나의 id를 전송
	 * 
	 * Thread th = new Thread(new Runnable() { // 스레드를 돌려서 서버로부터 메세지를 수신
	 * 
	 * @SuppressWarnings("null")
	 * 
	 * @Override public void run() { while (true) { try { byte[] b = new byte[128];
	 * dis.read(b); String msg = new String(b); msg = msg.trim();
	 * System.out.println("꼐속 돌고 있어요!\n"); // textArea.append(msg + "\n"); //
	 * textArea.setCaretPosition(textArea.getText().length()); } catch (IOException
	 * e) { // textArea.append("메세지 수신 에러!!\n"); // 서버와 소켓 통신에 문제가 생겼을 경우 소켓을 닫는다
	 * dManage.closeSocket();
	 * 
	 * } } // while문 끝 }// run메소드 끝 }); dManage.setThread(th); th.start(); }
	 */

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource(); // 선택된 버튼 알아내기
			if (source == start) {
				network(); // 소켓연결을 위한 메소드
				mainFrame.change("login");
				System.out.println("");
			} else if (source == close) {
				System.exit(0);
			}
		}
	}
}

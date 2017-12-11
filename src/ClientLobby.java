import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//FocusListener를 사용해야한다. 마우스 입력을 받을 때
public class ClientLobby extends JPanel {
	private ClientMain mainFrame;
	
	private JScrollPane topPanel;
	//private JTable roomList;	
	private JScrollPane downPanel;
	private JTextArea readChat;
	private JButton roomMakeButton;
	private JButton joinButton;
	private JButton sendButton;
	private JTextField writeChat;
	
	private DataManager dManager;

	public ClientLobby(ClientMain mainFrame, DataManager dManager) {
		this.mainFrame = mainFrame;
		this.dManager = dManager;
		setLayout(null);
		
		//방목록 관리 
		topPanel = new JScrollPane(new JTextArea("방 목록"));
		topPanel.setSize(200, 120);
		topPanel.setLocation(90, 10);
		add(topPanel);
		
		//(타)사용자 입력 메세지 출력 창
		readChat = new JTextArea();
		readChat.setEnabled(false);
		dManager.setTemp(readChat);
		downPanel = new JScrollPane(readChat);
		downPanel.setSize(200, 120);
		downPanel.setLocation(90, 170);
		add(downPanel);

		//사용자 메세지 입력 창
		writeChat = new JTextField();
		writeChat.setBounds(100, 300, 150, 30);
		writeChat.setText("Hello");
		add(writeChat);
		
		//방만들기 버튼 생성 및 설정
		roomMakeButton = new JButton(new ImageIcon("makeRoomButton.jpg")); // 방만들기 버튼
		roomMakeButton.setSize(60, 40);
		roomMakeButton.setLocation(100, 130);
		add(roomMakeButton);

		//방참가 버튼 생성 및 설정
		joinButton = new JButton(new ImageIcon("enterRoomButton.jpg")); // 방만들기 버튼
		joinButton.setSize(60, 40);
		joinButton.setLocation(220, 130);
		add(joinButton);
		
		//메세지 송신 버튼 생성 및 설정
		sendButton = new JButton(new ImageIcon("gameLobbySendButton.jpg")); // 채팅 보내기 버튼
		sendButton.setSize(60, 30);
		sendButton.setLocation(300, 300);
		add(sendButton);

		ImageIcon backGround = new ImageIcon("lobbyBg.jpg"); // 배경화면 삽입
		JLabel image = new JLabel(backGround);
		image.setBounds(0, 0, 400, 400);
		add(image);
		
		//버튼에 이벤트 추가
		roomMakeButton.addActionListener(new MyActionListener());
		joinButton.addActionListener(new MyActionListener());
		sendButton.addActionListener(new MySendToServer());
		writeChat.addActionListener(new MySendToServer());
	}
	
	public void send_Message(String str) { // 서버로 메세지를 보내는 메소드
		try {
			byte[] bb;
			bb = str.getBytes();
			
			(dManager.getDos()).write(bb); //.writeUTF(str);
			//System.out.println("브로드캐스트중!");
		} catch (IOException e) {
			readChat.append("메세지 송신 에러!!\n");
		}	
	}

	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource(); // 선택된 버튼 알아내기
			if (source == roomMakeButton) {
				JOptionPane.showInputDialog(null, "방 제목을 입력하세요", JOptionPane.OK_CANCEL_OPTION);
			}
		}
	}
	
	class MySendToServer implements ActionListener // 내부클래스로 액션 이벤트 처리 클래스
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// 액션 이벤트가 sendBtn일때 또는 textField 에세 Enter key 치면
			if (e.getSource() == sendButton || e.getSource() == writeChat) 
			{
				String msg = null;
				//msg = String.format("[%s] %s\n", id, textField.getText());
				msg = String.format("%s\n", writeChat.getText());
				//System.out.println("잘돌고 있음 \n");
				send_Message(msg);
				writeChat.setText(""); // 메세지를 보내고 나면 메세지 쓰는창을 비운다.
				writeChat.requestFocus(); // 메세지를 보내고 커서를 다시 텍스트 필드로 위치시킨다				
			}
		}
	}
}

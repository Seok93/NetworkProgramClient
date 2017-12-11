import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientLogin extends JPanel {

	private DataManager dManager;
	private JTextField userID; // ID를 입력받을곳
	private JTextField userPSWD; // IP를 입력받을곳
	private ClientMain mainFrame;
	// private Thread thread;

	public ClientLogin(ClientMain mainFrame, DataManager dManager) {
		this.mainFrame = mainFrame;
		this.dManager = dManager;
		// thread = (this.dManager).getThread();
		// if(thread != null)
		// thread.start();
		setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(53, 57, 90, 34);
		add(lblNewLabel);

		userID = new JTextField();
		userID.setBounds(100, 64, 150, 21);
		add(userID);
		userID.setColumns(10);

		JLabel lblServerIp = new JLabel("PASSWORD");
		lblServerIp.setBounds(12, 111, 90, 34);
		add(lblServerIp);

		userPSWD = new JTextField();
		userPSWD.setColumns(10);
		userPSWD.setBounds(100, 118, 150, 21);
		add(userPSWD);

		JButton btnNewButton = new JButton(new ImageIcon("loginButton.jpg"));
		btnNewButton.setBounds(280, 70, 60, 60);
		add(btnNewButton);

		ImageIcon backGround = new ImageIcon("board.png"); // 배경화면 삽입
		JLabel image = new JLabel(backGround);
		image.setBounds(0, 0, 400, 400);
		add(image);

		ConnectAction action = new ConnectAction();
		btnNewButton.addActionListener(action);
		userPSWD.addActionListener(action);
	}

	class ConnectAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String id = userID.getText().trim(); // 공백이 있지 모르니 공백 제거 trim() 사용
			String pass = userPSWD.getText().trim(); // 공백이 있을지 모르므로 공백제거
			String msg = String.format("/confirmID %s %s", id, pass);
			// System.out.println(msg);

			dManager.send_Message(msg);
			// System.out.println(dManager.getMsg());

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (dManager.getMsg().equals("true")) {
				dManager.setMsg("\n");
				mainFrame.change("lobby");
			} else {
				JOptionPane.showMessageDialog(null, "아이디나 비밀번호가 맞지 않습니다.", "오류", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientMakeRoom extends JPanel {
	private JComboBox game;
	private JComboBox people;
	private JComboBox room;
	private JTextField rnText;
	private ClientMain mainFrame;
	private JButton makeRoom;
	private JButton backRoom;
	private JLabel logout;
	private JLabel image;
	private ImageIcon backGround;
	private Image img;

	private DataManager dManager;

	String[] gameList = { "snake", "gomoku", "airplane" };
	String[] peoples = { "1", "2", "3", "4" };

	public ClientMakeRoom(ClientMain mainFrame, DataManager dManager) {
		this.mainFrame = mainFrame;
		this.dManager = dManager;
		setLayout(null);

		JLabel ga = new JLabel("종목");
		ga.setBounds(81, 70, 117, 65);
		add(ga);

		game = new JComboBox(gameList);
		game.setBounds(173, 90, 116, 21);
		add(game);

		JLabel su = new JLabel("인원");
		su.setBounds(81, 90, 117, 65);
		add(su);

		people = new JComboBox();
		for (int i = 0; i < peoples.length; i++)
			people.addItem(peoples[i]);
		people.setBounds(173, 110, 116, 21);
		add(people);

		JLabel rn = new JLabel("방제");
		rn.setBounds(81, 110, 117, 65);
		add(rn);

		rnText = new JTextField();
		rnText.setBounds(173, 130, 116, 21);
		add(rnText);

		backRoom = new JButton(new ImageIcon("exitButton.jpg"));
		backRoom.setSize(60, 40);
		backRoom.setLocation(110, 180);
		add(backRoom);

		makeRoom = new JButton(new ImageIcon("makeRoomButton.jpg"));
		makeRoom.setSize(60, 40);
		makeRoom.setLocation(200, 180);
		add(makeRoom);

		backGround = new ImageIcon("board.png"); // 배경화면 삽입
		image = new JLabel(backGround);
		image.setBounds(0, 0, 400, 400);
		add(image);

		/*
		 * game.addActionListener(new addActionListener() { public void
		 * actionPerformed(Action e) { JComboBox cb = (JComboBox)e.getSource(); int
		 * index = cb.getSelectedIndex(); } });
		 */

		backRoom.addActionListener(new MyActionListener());
		makeRoom.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource(); // 선택된 버튼 알아내기

			if (source == backRoom) {
				JOptionPane.showMessageDialog(null, "로그아웃 했습니다.");
				mainFrame.change("login");
			} else if (source == makeRoom) {
				mainFrame.change("lobby");
			}
		}
	}
}

import javax.swing.JFrame;

public class ClientMain extends JFrame {

	private ClientStart start = null;
	private ClientLogin login = null;
	private ClientLobby lobby = null;
	private ClientMakeRoom makeRoom = null;
	private ClientGame game = null;

	private DataManager dManager = null;

	public ClientMain() {
		dManager = new DataManager();

		start = new ClientStart(this, dManager);
		login = new ClientLogin(this, dManager);
		lobby = new ClientLobby(this, dManager);
		makeRoom = new ClientMakeRoom(this, dManager);
		game = new ClientGame();

		
		setTitle("네트워크 게임 천국");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocation(800, 300);
		add(new ClientStart(this, dManager));
		setVisible(true);
	}

	public void change(String panelName) {

		if (panelName.equals("start")) {
			getContentPane().removeAll();
			getContentPane().add(start);
			revalidate();
			repaint();
		} else if (panelName.equals("login")) {
			getContentPane().removeAll();
			getContentPane().add(login);
			revalidate();
			repaint();
		} else if (panelName.equals("lobby")) {
			getContentPane().removeAll();
			getContentPane().add(lobby);
			revalidate();
			repaint();
		} else if (panelName.equals("makeRoom")) {
			getContentPane().removeAll();
			getContentPane().add(makeRoom);
			revalidate();
			repaint();
		} else if (panelName.equals("game")) {
			getContentPane().removeAll();
			getContentPane().add(game);
			revalidate();
			repaint();
		}

	}

	public static void main(String[] args) {
		ClientMain frame = new ClientMain();
	}
}

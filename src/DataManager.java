import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JTextArea;

//데이터 저장용
public class DataManager {
	private Socket socket; // 연결소켓
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Thread thread;
	private String msg;
	private String ID;
	
	
	//임시 변수
	private JTextArea temp;
	//
	public void setSocket(Socket socket) {
		this.socket = socket;

		if (this.socket != null) {
			try { // 스트림 설정
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);
			} catch (IOException e) {
				// textArea.append("스트림 설정 에러!!\n");
			}
		}
	}
	//
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
	public void setID(String id) {
		this.ID = id;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	//임시 메소드
	public void setTemp(JTextArea temp) {
		this.temp = temp;
	}
	
	//
	public Socket getSocket() {
		return socket;
	}
	
	//
	public DataInputStream getDis() {
		return dis;
	}
	
	//
	public DataOutputStream getDos() {
		return dos;
	}
	//
	public Thread getThread() {
		return thread;
	}
	
	public String getMsg() {
		return msg;
	}

	
	
	public void Connection() { // 실직 적인 메소드 연결부분

		try { // 스트림 설정
			is = socket.getInputStream();
			dis = new DataInputStream(is);
			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
		} catch (IOException e) {
			System.out.println("스트림 설정 에러!!\n");
		}

		thread = new Thread(new Runnable() { // 스레드를 돌려서 서버로부터 메세지를 수신
			@SuppressWarnings("null")
			@Override
			public void run() {
				//int count = 0;
				while (true) {
					try {
						byte[] b = new byte[128];
						dis.read(b);
						msg = new String(b);
						msg = msg.trim();
						if(temp != null) {
							temp.append(msg + '\n');
						}
						//System.out.println("계속 돌고 있다."+(count++));
					} catch (IOException e) {
						System.out.println("메세지 수신 에러!!\n");
						// 서버와 소켓 통신에 문제가 생겼을 경우 소켓을 닫는다
						try {
							os.close();
							is.close();
							dos.close();
							dis.close();
							socket.close();
							break; // 에러 발생하면 while문 종료
						} catch (IOException e1) {

						}

					}
				} // while문 끝
			}// run메소드 끝
		});
		thread.start();
	}
	
	/*
	public void closeSocket() {
		try {
			os.close();
			is.close();
			dos.close();
			dis.close();
			socket.close();
		} catch (IOException e1) {

		}
	}
	*/
	
	public void send_Message(String str) { // 서버로 메세지를 보내는 메소드
		try {
			byte[] bb;
			bb = str.getBytes();
			dos.write(bb); //.writeUTF(str);
		} catch (IOException e) {
			System.out.println("메세지 송신 에러!!\n");
		}
	}
}

package vttp2022.sdf.assessment.task02.client;

public class Main {
	public static void main( String[] args ) {
		String host = "localhost";
		int port = 80;
		switch (args.length) {
			default:
			case 2:
				host = args[0];
				port = Integer.parseInt(args[1]);
				break;
			case 1:
				port = Integer.parseInt(args[0]);
				break;
			case 0:
		}
		Session sess = new Session(host, port);
		sess.run();
	}
}

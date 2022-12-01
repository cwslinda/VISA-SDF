package vttp2022.sdf.assessment.task02.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Session implements Runnable {

	private Logger logger = Logger.getLogger(Session.class.getName());

	private final String host;
	private final int port;

	private InputStream is;
	private ObjectInputStream ois;
	private OutputStream os;
	private ObjectOutputStream oos;

	public Session() {
		this.host = "localhost";
		this.port = 80;
	}

	public Session(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		
		Socket sock = null;

		logger.info("Connecting to server %s on port %s".formatted(host, port));

		try {

			sock = new Socket(host, port);

			initializeStreams(sock);

			final String input = read();
			final String terms[] = input.split(" ");

			final String requestId = terms[0];
			final List<String> nums = Arrays.asList(terms[1].split(","));

			final float result = (float)(nums.stream()
					.mapToInt(v -> Integer.parseInt(v.trim()))
					.reduce(0, (acc, v) -> acc + v)) / nums.size();

			logger.log(Level.INFO, "[%s] REQ: values: %s, result: %f"
					.formatted(requestId, nums, result));

			final byte[] seed = BigInteger.valueOf(System.currentTimeMillis()).toByteArray();
			final Random rand = new SecureRandom(seed);
			final int sleep = rand.nextInt(4, 41);
			logger.info("[%s] Sleeping for %d sec".formatted(requestId, sleep));
			Thread.sleep(sleep * 1000);

			write(requestId.trim());
			write("Fred Flintstone");
			write("fred@gmail.com");
			write(result);

			final boolean ok = readBoolean();

			logger.log(Level.INFO, "[%s] RESP: status: %b".formatted(requestId, ok));

			if (!ok) {
				String errMsg = read();
				logger.log(Level.WARNING, "[%s] RESP: error message: %s".formatted(requestId, errMsg));
			}

		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Exception in client", ex);
		} finally {
			if (null != sock)
				try { sock.close(); } catch (Exception ex) { }
		}
	}

	private String read() throws IOException {
		return ois.readUTF();
	}

	private boolean readBoolean() throws IOException {
		return ois.readBoolean();
	}

	private void write(String data) throws IOException {
		oos.writeUTF(data);
		oos.flush();
	}
	private void write (float data) throws IOException {
		oos.writeFloat(data);
		oos.flush();
	}

	private void initializeStreams(Socket sock) throws Exception {
		os = sock.getOutputStream();
		oos = new ObjectOutputStream(os);
		is = sock.getInputStream();
		ois = new ObjectInputStream(is);
	}
}

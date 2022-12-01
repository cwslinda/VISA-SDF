package vttp2022.sdf.assessment.task02.client;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StressMain {

	public static void main(String[] args) {
		final ScheduledExecutorService svc = Executors.newScheduledThreadPool(4);
		final Random rand = new SecureRandom();
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

		while (true) {
			int delay = rand.nextInt(3, 6);
			svc.schedule(new Session(host, port), delay, TimeUnit.SECONDS);
			try { Thread.sleep(1000); } catch (Exception ex) { }
		}
	}
}

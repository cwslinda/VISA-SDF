package vttp2022.sdf.assessment.task01.mailmerge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TemplateFile {

	private final List<String> lines = new LinkedList<>();

	public TemplateFile(String templateFile) throws Exception {
		try (FileReader fr = new FileReader(templateFile)) {
			BufferedReader br = new BufferedReader(fr);
			String line;
			while (null != (line = br.readLine()))
				lines.add(line);
		}
	}

	public String apply(Map<String, String> bindings) {
		return lines.stream()
			.map(line -> {
				String eff = line;
				for (String key: bindings.keySet()) {
					if (eff.contains(key))
						eff = eff.replaceAll(key, bindings.get(key));
				}
				return eff;
			})
			.reduce("", (acc, line) -> "%s\n%s".formatted(acc, line));
	}

	public List<String> getLines() {
		return Collections.unmodifiableList(lines);
	}
}

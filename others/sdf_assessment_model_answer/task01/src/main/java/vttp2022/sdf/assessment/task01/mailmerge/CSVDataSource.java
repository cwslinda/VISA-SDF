package vttp2022.sdf.assessment.task01.mailmerge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVDataSource {

	private final TemplateFile template;
	private final String csvFile;

	public CSVDataSource(String csvFile, TemplateFile template) {
		this.template = template;
		this.csvFile = csvFile;
	}

	public void process() throws Exception {
		try (FileReader fr = new FileReader(csvFile)) {
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			List<String> headers = createHeader(line);

			while (null != (line = br.readLine())) {
				Map<String, String> bindings = makeBinding(headers, splitValues(line));
				String doc = template.apply(bindings);
				System.out.printf("--------------------\n%s\n\n", doc);
			}
		}
	}

	private Map<String, String> makeBinding(List<String> headers, List<String> values) {
		Map<String, String> bindings = new HashMap<>();
		for (int i = 0; i < headers.size(); i++)
			bindings.put(headers.get(i), values.get(i));
		return bindings;
	}

	private List<String> splitValues(String line) {
		return Arrays.asList(line.split(","));
	}

	private List<String> createHeader(String header) {
		return Arrays.asList(header.split(",")).stream()
				.map(v -> "__%s__".formatted(v.trim()))
				.toList();
	}
}

package vttp2022.sdf.assessment.task01.mailmerge;

public class Main {

	public static void main( String[] args ) throws Exception {

		final String csvFile = args[0];
		final String templateFile = args[1];

		TemplateFile template = new TemplateFile(templateFile);
		CSVDataSource csv = new CSVDataSource(csvFile, template);
		csv.process();
	}
}

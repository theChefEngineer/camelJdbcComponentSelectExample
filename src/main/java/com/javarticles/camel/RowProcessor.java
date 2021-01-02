package com.javarticles.camel;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class RowProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		Map<String, Object> row = exchange.getIn().getBody(Map.class);
		Message in = exchange.getIn();
//		FileWriter myWriter = new FileWriter("filename.txt");
//
//		System.out.println("Processing " + row);
		Article article = new Article();

		article.setAuthor((String) row.get("AUTHOR"));
		article.setCategory((String) row.get("CATEGORY"));
		article.setName((String) row.get("NAME"));
		article.setTags((String) row.get("TAGS"));
		article.setId((Long) row.get("ID"));

//		myWriter.write(article.toString());
//		myWriter.close();

//		System.out.println("==============>>>>" + (String) row.get("NAME"));
		// exchange.getOut().setHeader(Exchange.FILE_NAME, ("report.txt"));

		exchange.getIn().setBody(article.toString());
	}
}
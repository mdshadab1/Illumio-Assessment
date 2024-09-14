package com.illumio;

import com.illumio.service.FlowLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class IllumioApplication implements CommandLineRunner {

	@Value("${app.input.flow-log-file}")
	private String flowLogFile;

	@Value("${app.input.lookup-file}")
	private String lookupFile;

	@Value("${app.output.file}")
	private String outputFile;

	@Autowired
	private FlowLogService flowLogService;

	public static void main(String[] args) {
		SpringApplication.run(IllumioApplication.class, args);
	}

	@Override
	public void run(String... args) {
		flowLogService.processFlowLogs(flowLogFile, lookupFile, outputFile);
	}
}
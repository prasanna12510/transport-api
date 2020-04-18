package com.cloudsre.services.transport_service.domain.initializers;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloudsre.services.transport_service.domain.Line;
import com.cloudsre.services.transport_service.domain.infrastructure.persistence.LineRepository;


@Component
public class LineInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(LineInitializer.class);
	
	 @Autowired
	    public LineInitializer(LineRepository repository) throws Exception {

	        if (repository.count() != 0) {
	            return;
	        }

	        List<Line> lineList = readLines();
	        log.info("Importing {} Lines into DataBase…", lineList.size());
	        repository.saveAll(lineList);
	        log.info("Successfully imported {} Lines.", repository.count());
	    }


	public static List<Line> readLines() throws Exception {

        ClassPathResource resource = new ClassPathResource("data/lines.csv");
        Scanner scanner = new Scanner(resource.getInputStream());
        String line = scanner.nextLine();
        scanner.close();

        FlatFileItemReader<Line> itemReader = new FlatFileItemReader<Line>();
        itemReader.setResource(resource);

        // DelimitedLineTokenizer defaults to comma as its delimiter
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(line.split(","));
        tokenizer.setStrict(false);

        DefaultLineMapper<Line> lineMapper = new DefaultLineMapper<Line>();
        lineMapper.setFieldSetMapper(fields -> {

            Long lineId = Long.valueOf(fields.readString("line_id"));
            String lineName = fields.readString("line_name");

            return new Line(lineId, lineName);
        });

        lineMapper.setLineTokenizer(tokenizer);
        itemReader.setLineMapper(lineMapper);
        itemReader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
        itemReader.setLinesToSkip(1);
        itemReader.open(new ExecutionContext());

        List<Line> lineLines = new ArrayList<>();
        Line lines = null;

        do {

            lines = itemReader.read();

            if (lines != null) {
                lineLines.add(lines);
            }

        } while (lines != null);

        return lineLines;
    }

}

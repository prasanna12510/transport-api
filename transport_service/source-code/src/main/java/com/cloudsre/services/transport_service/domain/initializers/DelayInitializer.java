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

import com.cloudsre.services.transport_service.domain.Delay;
import com.cloudsre.services.transport_service.domain.infrastructure.persistence.DelayRepository;

@Component
public class DelayInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(DelayInitializer.class);
	
	
	@Autowired
    public DelayInitializer(DelayRepository repository) throws Exception {

        if (repository.count() != 0) {
            return;
        }

        List<Delay> delayList = readDelays();
        log.info("Importing {} Delays into DataBase…", delayList.size());
        repository.saveAll(delayList);
        log.info("Successfully imported {} Delays.", repository.count());
    }
	
	public static List<Delay> readDelays() throws Exception {
		
		ClassPathResource resource = new ClassPathResource("data/delays.csv");
        Scanner scanner = new Scanner(resource.getInputStream());
        String line = scanner.nextLine();
        scanner.close();

        FlatFileItemReader<Delay> itemReader = new FlatFileItemReader<Delay>();
        itemReader.setResource(resource);

        // DelimitedLineTokenizer defaults to comma as its delimiter
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(line.split(","));
        tokenizer.setStrict(false);

        DefaultLineMapper<Delay> lineMapper = new DefaultLineMapper<Delay>();
        lineMapper.setFieldSetMapper(fields -> {

            String lineName = fields.readString("line_name");
            Long delay = Long.valueOf(fields.readString("delay"));
            return new Delay(lineName, delay);
        });

        lineMapper.setLineTokenizer(tokenizer);
        itemReader.setLineMapper(lineMapper);
        itemReader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
        itemReader.setLinesToSkip(1);
        itemReader.open(new ExecutionContext());

        List<Delay> delayList = new ArrayList<>();
        Delay delay = null;

        do {

            delay = itemReader.read();

            if (delay != null) {
                delayList.add(delay);
            }

        } while (delay != null);

        return delayList;
    }

		
	}



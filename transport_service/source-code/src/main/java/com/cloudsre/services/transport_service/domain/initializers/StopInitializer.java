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

import com.cloudsre.services.transport_service.domain.Stop;
import com.cloudsre.services.transport_service.domain.infrastructure.persistence.StopRepository;


@Component
public class StopInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(StopInitializer.class);
	
	
	@Autowired
    public StopInitializer(StopRepository repository) throws Exception {

        if (repository.count() != 0) {
            return;
        }

        List<Stop> stopList = readStops();
        log.info("Importing {} Stops into DataBase…", stopList.size());
        repository.saveAll(stopList);
        log.info("Successfully imported {} Stops.", repository.count());
    }
	
	public static List<Stop> readStops() throws Exception {

        ClassPathResource resource = new ClassPathResource("data/stops.csv");
        Scanner scanner = new Scanner(resource.getInputStream());
        String line = scanner.nextLine();
        scanner.close();

        FlatFileItemReader<Stop> itemReader = new FlatFileItemReader<Stop>();
        itemReader.setResource(resource);

        // DelimitedLineTokenizer defaults to comma as its delimiter
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(line.split(","));
        tokenizer.setStrict(false);

        DefaultLineMapper<Stop> lineMapper = new DefaultLineMapper<Stop>();
        lineMapper.setFieldSetMapper(fields -> {

            int x = fields.readInt("x");
            int y = fields.readInt("y");

            Long stopId = Long.valueOf(fields.readString("stop_id"));

            return new Stop(stopId, x, y);
        });

        lineMapper.setLineTokenizer(tokenizer);
        itemReader.setLineMapper(lineMapper);
        itemReader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
        itemReader.setLinesToSkip(1);
        itemReader.open(new ExecutionContext());

        List<Stop> stopList = new ArrayList<>();
        Stop stop = null;

        do {

            stop = itemReader.read();

            if (stop != null) {
                stopList.add(stop);
            }

        } while (stop != null);

        return stopList;
    }

}

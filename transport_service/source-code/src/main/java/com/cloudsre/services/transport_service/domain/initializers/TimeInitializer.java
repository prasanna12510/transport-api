package com.cloudsre.services.transport_service.domain.initializers;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
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

import com.cloudsre.services.transport_service.domain.Time;
import com.cloudsre.services.transport_service.domain.infrastructure.persistence.TimeRepository;
import com.cloudsre.services.transport_service.utils.DateUtils;


@Component
public class TimeInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(TimeInitializer.class);
	
	@Autowired
    public TimeInitializer(TimeRepository repository) throws Exception {

        if (repository.count() != 0) {
            return;
        }

        List<Time> timeList = readTimes();
        log.info("Importing {} Times into DataBase…", timeList.size());
        repository.saveAll(timeList);
        log.info("Successfully imported {} Times.", repository.count());
    }
	
	public static List<Time> readTimes() throws Exception {

        ClassPathResource resource = new ClassPathResource("data/times.csv");
        Scanner scanner = new Scanner(resource.getInputStream());
        String line = scanner.nextLine();
        scanner.close();

        FlatFileItemReader<Time> itemReader = new FlatFileItemReader<Time>();
        itemReader.setResource(resource);

        // DelimitedLineTokenizer defaults to comma as its delimiter
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(line.split(","));
        tokenizer.setStrict(false);

        DefaultLineMapper<Time> lineMapper = new DefaultLineMapper<Time>();
        lineMapper.setFieldSetMapper(fields -> {

            Long lineId = fields.readLong("line_id");
            Long stopId = fields.readLong("stop_id");


            Timestamp timestamp= DateUtils.converToTimeStamp(fields.readString("time"));
            return new Time(lineId, stopId, timestamp);
        });

        lineMapper.setLineTokenizer(tokenizer);
        itemReader.setLineMapper(lineMapper);
        itemReader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
        itemReader.setLinesToSkip(1);
        itemReader.open(new ExecutionContext());

        List<Time> timeList = new ArrayList<>();
        Time time = null;

        do {

            time = itemReader.read();

            if (time != null) {
                timeList.add(time);
            }

        } while (time != null);

        return timeList;
    }

}

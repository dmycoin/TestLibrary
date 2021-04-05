package com.TimeHelper;

import com.service.BookReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class BookReaderTimeHelper {
    private static final Logger logger = LoggerFactory.getLogger(BookReaderService.class);
    public static int diffDates(LocalDate date1, LocalDate date2) {
        Period period = Period.between(date2, date1);
        int diff = period.getDays();
        logger.info("days: {}", diff);
        return diff;
    }
}

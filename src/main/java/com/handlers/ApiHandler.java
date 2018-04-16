package com.handlers;

import com.config.ApplicationConfig;
import com.model.DayOfWeek;
import com.model.RateRange;
import com.model.TimeRange;
import com.model.exceptions.ApplicationException;
import com.model.exceptions.InvalidInputException;
import com.model.exceptions.RateNotAvailableException;
import com.model.rest.RangeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class ApiHandler {
    private final static Logger LOG = LoggerFactory.getLogger(ApiHandler.class);
    private ApplicationConfig appConfig = ApplicationConfig.getInstance();

    public int handleRate(final RangeRequest rangeRequest) throws ApplicationException {
        final LocalDateTime fromTime = checkIfValidInput(rangeRequest.getFromTime());
        final LocalDateTime toTime = checkIfValidInput(rangeRequest.getToTime());
        checkFromTimeBeforeToTime(fromTime, toTime);

        final TimeRange r = new TimeRange(fromTime.toLocalTime(), toTime.toLocalTime());
        final DayOfWeek dow = DayOfWeek.values()[fromTime.getDayOfWeek().getValue()];
        LOG.info("Searching for rate for dow={}, range={}", dow, r);
        return getRateForRange(r, dow);
    }

    private int getRateForRange(final TimeRange timeRange, final DayOfWeek dow) throws RateNotAvailableException {
        final Optional<RateRange> first = appConfig.getRateRanges().get(dow)
                .stream()
                .filter(rate -> rate.getRangeRateIsValidFor().containsRange(timeRange))
                .findFirst();

        if (first.isPresent()) {
            LOG.info("Found rate. Range={} rate={}", timeRange, first.get().getPrice());
            return first.get().getPrice();
        }
        throw new RateNotAvailableException("");
    }

    private void checkFromTimeBeforeToTime(final LocalDateTime fromTime, final LocalDateTime toTime) throws InvalidInputException {
        if (toTime.isBefore(fromTime)) {
            throw new InvalidInputException("\"from\" time is before \"to\" time");
        }
    }

    private LocalDateTime checkIfValidInput(final String rangeRequest) throws InvalidInputException {
        try {
            return LocalDateTime.parse(rangeRequest, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException ex) {
            LOG.info("Invalid input range rangeRequest={}", rangeRequest);
            throw new InvalidInputException(String.format("%s is not a valid ISO date", rangeRequest));
        }
    }
}

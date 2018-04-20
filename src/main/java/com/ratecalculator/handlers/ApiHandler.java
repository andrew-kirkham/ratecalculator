package com.ratecalculator.handlers;

import com.ratecalculator.config.ApplicationConfig;
import com.ratecalculator.model.DayOfWeek;
import com.ratecalculator.model.RateRange;
import com.ratecalculator.model.TimeRange;
import com.ratecalculator.model.exceptions.ApplicationException;
import com.ratecalculator.model.exceptions.InvalidInputException;
import com.ratecalculator.model.exceptions.RateNotAvailableException;
import com.ratecalculator.model.rest.RangeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * ApiHandler
 *
 * Handle requests from the ApiController
 */
public class ApiHandler {
    private final static Logger LOG = LoggerFactory.getLogger(ApiHandler.class);
    private ApplicationConfig appConfig = ApplicationConfig.getInstance();

    /**
     * Calculate rate for a given rangeRequest
     * @param rangeRequest - the user input
     * @return - the rate for the range
     * @throws ApplicationException - Invalid user input
     */
    public int calculateRate(final RangeRequest rangeRequest) throws ApplicationException {
        //validate input
        checkForNull(rangeRequest);
        final LocalDateTime fromTime = checkIfValidInput(rangeRequest.getFromTime());
        final LocalDateTime toTime = checkIfValidInput(rangeRequest.getToTime());
        //cant calculate backwards times
        checkFromTimeBeforeToTime(fromTime, toTime);

        final TimeRange r = new TimeRange(fromTime.toLocalTime(), toTime.toLocalTime());
        final DayOfWeek dow = DayOfWeek.values()[fromTime.getDayOfWeek().getValue()];
        LOG.info("Searching for rate for dow={}, range={}", dow, r);
        return getRateForRange(r, dow);
    }

    private void checkForNull(final RangeRequest rangeRequest) throws InvalidInputException {
        if (rangeRequest == null || rangeRequest.getFromTime() == null || rangeRequest.getToTime() == null) {
            throw new InvalidInputException("rate must be supplied");
        }
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
        throw new RateNotAvailableException("A rate for the time range does not exist");
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

package com.ecommerce.back.shared.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GeneralLogsImpl implements GeneralLogs {

    private static final Logger log = LoggerFactory.getLogger(GeneralLogsImpl.class);

    /**
     * @param message
     */
    @Override
    public void logInfo(String message) {
        log.info(message);

    }

    /**
     * @param message
     * @param throwable
     */
    @Override
    public void logError(String message, Throwable throwable) {
        log.error(message, throwable);

    }

    /**
     * @param message
     */
    @Override
    public void logWarning(String message) {
        log.warn(message);

    }
}

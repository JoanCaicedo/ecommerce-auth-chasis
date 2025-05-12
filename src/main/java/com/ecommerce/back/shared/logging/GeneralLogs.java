package com.ecommerce.back.shared.logging;

public interface GeneralLogs {
    void logInfo(String message);
    void logError(String message, Throwable throwable);
    void logWarning(String message);
}

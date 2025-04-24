package com.ecommerce.back.shared.validation;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Getter
public class RequestValidator {

        protected String error = "";
        protected Map<String, String> data;

        public RequestValidator validate(String value, String paramName) {
            data = new HashMap<>();
            data.put("data", value);
            data.put("paramName", paramName);
            return this;
        }

        public RequestValidator isEmpty() {
            if (data.get("data") == null || data.get("data").isEmpty()) {
                error = String.format("%s is empty", data.get("paramName"));
            }
            return this;
        }

        public RequestValidator maxLength() {
            if (data.get("data").length() > 255) {
                error = String.format("%s must be less than 255 characters", data.get("paramName"));
            }
            return this;
        }

        public RequestValidator maxLength(int length) {
            if (data.get("data").length() > length) {
                error = String.format("%s must be less than %d", data.get("paramName"), length);
            }
            return this;
        }

        public RequestValidator isValidDate() {
            String date = data.get("data");
            String regex = "^\\d{4}-\\d{2}-\\d{2}$";
            if (date == null || !Pattern.matches(regex,date)) {
                error = String.format("%s is not a valid date, it must be YYYY/MM/DD ", data.get("paramName"));
            }
            return this;
        }

        public RequestValidator isValidEmail() {
            String email = data.get("data");
            String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (email == null || !Pattern.matches(regex,email)) {
                error = String.format("%s is not a valid email", data.get("paramName"));
            }
            return this;
        }

}

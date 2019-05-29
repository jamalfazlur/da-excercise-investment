package com.jamal.dainvestment.util;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * This is a Javadoc comment
 * Response Error
 */

@Getter
@Setter
@XmlRootElement(name = "error")
public class ErrorResponse {
    private String message;
    private List<String> details;

    /**
     * This is a constructor for error response
     * @param message as error message,
     * @param details as detail
     */
    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
}

package com.your.cellar.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The BaseResponse class is used to send result back.
 *
 * @author adams.sundberg@gmail.com
 * @version 1.0
 * @since 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    /** return code */
    private int returnCode;

    /** message */
    private String message;

    /** actual response */
    private T result;
}

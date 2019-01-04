package com.petrego.domain;

/**
 * <p>
 *     A list of codes presented by the API to the consuming application. The codes are documented by the API documentation,
 *     so the documentation is not returned with the codes. This means that the client is free to apply whatever text it
 *     pleases to the error code.
 * </p>
 * <p>
 *     The developer should try to create as many codes as would be useful in supporting the deployed application, you
 *     want as many as possible, you want them specific, and you want them descriptive.
 * </p>
 * <p>
 *     The hope is that eventually, these codes could be shown on the Website error pages, so that when the inevitable
 *     screenshot from support arrives in your inbox, it will actually (and unintentionally) be useful for once.
 * </p>
 */
public enum MessageCode {

    /**
     * 1XX - informational http status codes
     */
    CONTINUE(100),
    SWITCHING_PROTOCOLS(101),
    PROCESSING(102),

    /**
     * 2XX - success http status codes
     */
    OK(200),
    CREATED(201),

    /**
     * 3XX - redirection http status codes
     */

    /**
     * 4XX - client error http status codes
     */
    BAD_REQUEST(400),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),

    /*401 Unauthorized
    402 Payment Required
    405 Method Not Allowed
    406 Not Acceptable
    407 Proxy Authentication Required
    408 Request Timeout
    409 Conflict
    410 Gone
    411 Length Required
    412 Precondition Failed
    413 Payload Too Large
    414 Request-URI Too Long
    415 Unsupported Media Type
    416 Requested Range Not Satisfiable
    417 Expectation Failed
    418 I'm a teapot
    421 Misdirected Request
    422 Unprocessable Entity
    423 Locked
    424 Failed Dependency
    426 Upgrade Required
    428 Precondition Required
    429 Too Many Requests
    431 Request Header Fields Too Large
    444 Connection Closed Without Response
    451 Unavailable For Legal Reasons
    499 Client Closed Request*/

    /**
     * 5XX - server error http status codes
     */

    /**
     * 1xxx - application informational codes
     */
    MESSAGE(1000),

    /**
     * 2xxx - application success codes
     */
    PROCESSED(2000),

    /**
     * 40xx - application error codes
     */
    ERROR(4000),
    FATAL_ERROR(4001),
    EXISTING_ENTITY(4002),
    ENTITY_CONSTRAINT_VIOLATION(4003),
    ENTITY_NOT_FOUND(4004);

    private final int messageCode;

    MessageCode(final int messageCode) {
        this.messageCode = messageCode;
    }

    public int getCode() {
        return messageCode;
    }

    @Override
    public String toString() {
        return String.valueOf(messageCode);
    }
}

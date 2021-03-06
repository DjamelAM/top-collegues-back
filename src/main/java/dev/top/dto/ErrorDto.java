package dev.top.dto;

public class ErrorDto {
	private ErrorCode code;

	private String message;

	public ErrorDto(ErrorCode code, String msg) {
		this.code = code;
		this.message = msg;
	}

	public ErrorDto(ErrorCode code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public ErrorCode getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(ErrorCode code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}

package com.wanda.creditapp.security;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class AuthenticationRequestWrapper extends HttpServletRequestWrapper {

	private byte[] requestBody = new byte[0];
	private boolean bufferFilled = false;

	/**
	 * - Constructs a request object wrapping the given request.
	 * <p/>
	 * - @param request The request to wrap - @throws IllegalArgumentException
	 * if the request is null
	 */
	public AuthenticationRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public byte[] getRequestBody() throws IOException {
		if (bufferFilled) {
			return Arrays.copyOf(requestBody, requestBody.length);
		}

		InputStream inputStream = super.getInputStream();

		byte[] buffer = new byte[102400]; // 100kb buffer

		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			requestBody = concat(this.requestBody, Arrays.copyOfRange(buffer, 0, bytesRead)); // <1>
		}

		bufferFilled = true;

		return requestBody;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new CustomServletInputStream(getRequestBody());
	}
	
	public static byte[] concat(byte[]... arrays) {
		int length = 0;
		for (byte[] array : arrays) {
			length += array.length;
		}
		byte[] result = new byte[length];
		int pos = 0;
		for (byte[] array : arrays) {
			System.arraycopy(array, 0, result, pos, array.length);
			pos += array.length;
		}
		return result;
	}

	private static class CustomServletInputStream extends ServletInputStream {

		private ByteArrayInputStream buffer;

		public CustomServletInputStream(byte[] contents) {
			this.buffer = new ByteArrayInputStream(contents);
		}

		@Override
		public int read() throws IOException {
			return buffer.read();
		}

		@Override
		public boolean isFinished() {
			return buffer.available() == 0;
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setReadListener(ReadListener listener) {
			throw new RuntimeException("Not implemented");
		}
	}

	
}

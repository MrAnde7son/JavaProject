package io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Compresses a given data stream of type OutputStream
 * @author Itamar Mizrahi Mizrahi
 */
public class MyCompressorOutputStream extends OutputStream {

	private OutputStream out;
	
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}
	
	@Override
	public void write(int b) throws IOException {
		out.write(b);
	}
	
	@Override
	public void write(byte[] arr) throws IOException {
		int count = 0;
		int i = 0;
		byte currByte = arr[i];
		for (; i < arr.length; i++) {
			if (arr[i] != currByte) {
				while (count >= 255) {
					out.write(255);
					out.write(currByte);
					count -= 255;
				}
				out.write(count);
				out.write(currByte);
				currByte = arr[i];
				count = 1;
			}
			else {
				count++;
			}
		}
		out.write(count);
		out.write(currByte);
		
	}
}

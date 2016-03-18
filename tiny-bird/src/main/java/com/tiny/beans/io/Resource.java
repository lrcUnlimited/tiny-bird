package com.tiny.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author li
 * 
 */
public interface Resource {
	public InputStream getInputStream() throws IOException;

}

package com.tiny.beans.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 通过url加载resource
 * 
 * @author li
 * 
 */
public class UrlResource implements Resource {
	private URL url;

	public UrlResource(URL url) {
		this.url = url;
	}

	public InputStream getInputStream() throws IOException {
		URLConnection urlConnection = url.openConnection();
		urlConnection.connect();
		return urlConnection.getInputStream();
	}

}

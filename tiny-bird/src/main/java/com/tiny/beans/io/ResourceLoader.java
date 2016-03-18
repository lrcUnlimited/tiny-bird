package com.tiny.beans.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 加载resource
 * 
 * @author li
 * 
 */
public class ResourceLoader {
	public Resource getResource(String location) throws MalformedURLException {
		URL url = this.getClass().getClassLoader().getResource(location);
		UrlResource resource = new UrlResource(url);
		return resource;

	}

}

/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;

import net.sf.jasperreports.engine.util.SimpleFileResolver;

/**
 * Extends the default SimpleFileResolver by adding 
 * the decode of the path as url, when the simple file
 * resolver can't resolve it
 * 
 * @author Orlandin Marco
 *
 */
public class URLFileResolver extends SimpleFileResolver {

	public URLFileResolver(File parentFolder) {
		super(parentFolder);
	}
	
	public URLFileResolver(List<File> parentFolders) {
		super(parentFolders);
	}
	
	/**
	 * Search a resources by searching it inside the workspace (this for example 
	 * can resolve the linked resources)
	 * 
	 * @param filename the name of the resource
	 * @param resource the project requesting the resource (the resource is searched inside the parent)
	 * @return the resource or null if it can't be found
	 */
	public File resoolveInTheWorkspace(String filename, IResource resource){
		IFile file = resource.getParent().getFile(new Path(filename));
		if (file != null && file.exists()) return file.getLocation().toFile();
		//fallback with the standard resolver
		else return resolveFile(filename);
	}

	@Override
	public File resolveFile(String fileName) {
		File fileToBeOpened = super.resolveFile(fileName);
		//The simple file resolver can't resolve the filename, try to check if it's an url
		if (fileToBeOpened == null){
			try {
				URL fileURL = new URL(fileName);
				File f = new File(fileURL.toURI());
				if (f.exists()) fileToBeOpened = f;
			} catch (Exception e) {
				// do not care about it 
			}
		}
		return fileToBeOpened;
	}
	
	
}

package com.pdfsplitting;

import java.io.IOException;

/**
 * Interface for PDF File Operations
 * 
 * @author marioszocs
 */
public interface PDFFileOperations {

	public void splitPDFAfterPages();

	public void splifPDFBySize();

	public void extractEmailAddress();
}

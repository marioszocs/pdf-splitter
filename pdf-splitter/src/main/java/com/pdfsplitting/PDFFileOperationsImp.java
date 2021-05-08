package com.pdfsplitting;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * Operation with PDF files
 *
 * @author marioszocs
 */
class PDFFileOperationsImp implements PDFFileOperations {

	// Init settings for methods
	final static String FOLDER_PATH = PdfUtilities.setInputFolderPath();
	final static String OUTPUT_FOLDER_PATH = PdfUtilities.setOutputFolderPath();
	static File myFolder = new File(FOLDER_PATH);
	static File[] pdfsInMyFolder = myFolder.listFiles();
	static int numberOfPdfsInFolder = pdfsInMyFolder.length;
	private static final String VALID_EMAIL_ADDRESS_REGEX = "\\s[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}\\s";

	/**
	 * Method for Split PDF file to multiple PDF files after specific pages
	 */
	public void splitPDFAfterPages() {

		try {

			final int SPLIT_PDF_NUMBER = PdfUtilities.setPageNumberAftreSplit();

			System.out.println("Number of Pdfs in input folder: " + numberOfPdfsInFolder);

			for (int i = 0; i < numberOfPdfsInFolder; i++) {
				File pdf = new File(FOLDER_PATH + "/" + pdfsInMyFolder[i].getName());

				PDDocument doc = PDDocument.load(pdf);
				Splitter splitter = new Splitter(); // Instantiating Splitter class

				splitter.setSplitAtPage(SPLIT_PDF_NUMBER);
				List<PDDocument> Pages = splitter.split(doc); // splitting the pages of a PDF document
				Iterator<PDDocument> iterator = Pages.listIterator(); // Creating an iterator

				int j = 1;

				while (iterator.hasNext()) {
					PDDocument pd = iterator.next();
					pd.save(OUTPUT_FOLDER_PATH + "/" + pdf.getName() + j++ + ".pdf");
					// pd.close();
				}
				System.out.println("Splitting PDF: -> " + pdf.getName() + " <- after every " + SPLIT_PDF_NUMBER
						+ " pages is DONE!");
				System.out.println();

			}

		} catch (Exception e) {
			System.out.println("Upps something went wrong with the method: splitPDFAfterPages() -> " + e);
		}
	}

	/**
	 * Method for Split PDF by specific size
	 */
	public void splifPDFBySize() {

		int maxAllowedPdfSizeInKb = PdfUtilities.setMaxAllowablePdfSizeInKb();
		System.out.println(""); //Just new line
		try {

			for (int j = 0; j < numberOfPdfsInFolder; j++) {
				PdfReader PDF = new PdfReader(FOLDER_PATH + "/" + pdfsInMyFolder[j].getName());
				Document document = new Document();

//				PdfCopy copy = new PdfCopy(document,new FileOutputStream(OUTPUT_FOLDER_PATH + "/" + pdfsInMyFolder[j].getName()));
//				document.open();

				int numberOfPages = PDF.getNumberOfPages();
				int pdfSizeInKilobytes = (int) (PDF.getFileLength() / 1024);
				int pdfNameNumbering = 1; // To generate file name dynamically
				int pdfSizeInBytes; // To get PDF size in bytes
				String pdfName = pdfsInMyFolder[j].getName(); // To get PDF name
				String finalPdfName = pdfName.substring(0, pdfName.length() - 4); // PDF name formatting
				float combinedsize = 0; // To convert this to Kilobytes and estimate new PDF size

				//System.out.println("PDF with name: " + pdfsInMyFolder[j].getName() + " current size is: " + pdfSizeInKilobytes + " kB!");

				// The size of the pdf file must be larger than the maxAllowedPdfSizeInKb
				if (pdfSizeInKilobytes > maxAllowedPdfSizeInKb) {
					PdfCopy copy = new PdfCopy(document, new FileOutputStream(OUTPUT_FOLDER_PATH + "/" + pdfsInMyFolder[j].getName()));
					document.open();

					for (int i = 1; i < numberOfPages; i++) {
						// Generate new file only for second time when first document size exceeds limit
						// and incoming loop counter is not 1
						if (combinedsize == 0 && i != 1) {
							document = new Document();
							pdfNameNumbering++;
							String FileName = finalPdfName + pdfNameNumbering + ".pdf"; // Dynamic file name
							copy = new PdfCopy(document, new FileOutputStream(OUTPUT_FOLDER_PATH + "/" + FileName));
							document.open();
						}
						copy.addPage(copy.getImportedPage(PDF, i)); // Import pages from original document
						pdfSizeInBytes = (int) copy.getCurrentDocumentSize(); // Estimate PDF size in bytes
						combinedsize = (float) pdfSizeInBytes / 1024; // Convert bytes to kilobytes

						// Close document if the page is the last page or if limit reaches
						if (combinedsize > maxAllowedPdfSizeInKb || i == numberOfPages) {
							document.close(); // Splitted document close
							combinedsize = 0; // Reset variable to generate next file, if required
						}
					}

					document.close(); //Close the last document
					System.out.println("OK -> PDF: " + pdfName + " split by size: " + maxAllowedPdfSizeInKb + "kB is completed. Number of new documents created: " + pdfNameNumbering);

				} else {
					System.out.println("NOK -> PDF: " + pdfsInMyFolder[j].getName() + " is smaller than max allowed size: " + maxAllowedPdfSizeInKb + " kB!");
				}

			}
		} catch (Exception e) {
			System.out.println("Upps something went wrong with the method: splifPDFBySize() -> " + e);
		}
	}

	/**
	 * Method for extracting email addresses from PDF
	 */
	public void extractEmailAddress() {

		try {

			StringBuilder sbOut = new StringBuilder();

			System.out.println("Number of Pdfs in input folder: " + numberOfPdfsInFolder);

			for (int i = 0; i < numberOfPdfsInFolder; i++) {
				File pdf = new File(FOLDER_PATH + "/" + pdfsInMyFolder[i].getName());

				// Load PDF Document
				PDDocument doc = PDDocument.load(pdf);

				// Instantiate StringBuilder and PDFTextStripper class
				StringBuilder sb = new StringBuilder();
				PDFTextStripper stripper = new PDFTextStripper();

				// Add text to the StringBuilder from the PDF
				sb.append(stripper.getText(doc));

				// Set Pattern for e-mail address
				Pattern pattern = Pattern.compile(VALID_EMAIL_ADDRESS_REGEX, Pattern.CASE_INSENSITIVE);

				// Retrieve email address
				Matcher matcher = pattern.matcher(sb);
				while (matcher.find()) {
					sbOut.append(matcher.group());
					// Append a newline to StringBuilder
					sbOut.append(System.getProperty("line.separator"));
				}

				if (doc != null) {
					doc.close();
					// System.out.println("Close the document...");
				}
			}

			// Save extracted emails to txt file
			try {
				FileWriter writer = new FileWriter(OUTPUT_FOLDER_PATH + "/EmailAddresses.txt", true);
				BufferedWriter bufferedWriter = new BufferedWriter(writer);
				bufferedWriter.write(sbOut.toString());
				bufferedWriter.newLine();
				bufferedWriter.close();
			} catch (IOException e) {
				System.out.println("Upps something went wrong at saving email addresses to a txt file -> " + e);
			}

			System.out.println("\nEmail addresses are extracted and saved in the txt file!");

		} catch (Exception e) {
			System.out.println("Upps something went wrong with the method: extractEmailAddress() -> " + e);
		}

	}

}

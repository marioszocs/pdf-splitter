package com.pdfsplitting;

import java.util.Scanner;

/**
 * Utility class for PDF splitting
 *
 * @author marioszocs
 */
public class PdfUtilities {

	private static Scanner scanner = new Scanner(System.in); // Instantiating Scanner class
	public static String inputFolderPath = null;
	public static String outputFolderPath = null;
	public static int maxAllowedSize;
	public static int pageNumberAftreSplit;

	/**
	 * Function to set input folder path
	 *
	 * @return inputFolderPath
	 */
	public static String setInputFolderPath() {
		System.out.print("Enter Input Folder path: ");

		try {
			inputFolderPath = scanner.next();
			inputFolderPath = inputFolderPath.replace("\\", "/");
		} catch (Exception e) {
			System.out.println("Upps something went wrong with the method: setInputFolderPath() -> " + e);
		}
		return inputFolderPath;
	}

	/**
	 * Function to set output folder path
	 *
	 * @return outputFolderPath
	 */
	public static String setOutputFolderPath() {
		System.out.print("Enter Output Folder path: ");
		try {
			outputFolderPath = scanner.next();
			outputFolderPath = outputFolderPath.replace("\\", "/");
		} catch (Exception e) {
			System.out.println("Upps something went wrong with the method: setOutputFolderPath() -> " + e);
		}
		return outputFolderPath;
	}

	/**
	 * Function to set the maximum size of the pdf in KB
	 *
	 * @return maxAllowedSize
	 */
	public static int setMaxAllowablePdfSizeInKb() {
		System.out.print("Enter the maximum size of the pdfs in kilobytes: ");
		try {
			maxAllowedSize = scanner.nextInt();
		} catch (Exception e) {
			System.out.println("Upps something went wrong with the method: setMaxAllowablePdfSizeInKb() -> " + e);
		}
		return maxAllowedSize;
	}

	/**
	 * Function to set how many pages to split the PDF file
	 *
	 * @return pageNumberAftreSplit
	 */
	public static int setPageNumberAftreSplit() {
		System.out.print("Enter the number, how many pages to split the PDF file: ");
		try {
			pageNumberAftreSplit = scanner.nextInt();
		} catch (Exception e) {
			System.out.println("Upps something went wrong with the method: setPageNumberAftreSplit() -> " + e);
		}
		return pageNumberAftreSplit;
	}

}

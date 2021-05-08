package com.pdfsplitting;

import java.util.Scanner;

public class Main {

	private static Scanner scanner = new Scanner(System.in); // Instantiating Scanner class

	public static void main(String[] args) {

		System.out.println(
				"In the first step, you must specify the input and output folders path for the PDF operations!\n");

		PDFFileOperationsImp pdfOperations = new PDFFileOperationsImp();

		System.out.println("\nChoose a number which action you want to do!");
		System.out.println("Choose 1 -> if you want split PDF file after specific pages.");
		System.out.println("Choose 2 -> if you want extract emails from PDF file.");
		System.out.println("Choose 3 -> if you want split PDF by specific size.\n");

		System.out.print("Choose a number: ");

		switch (scanner.nextInt()) {
		case 1:
			pdfOperations.splitPDFAfterPages();
			break;

		case 2:
			pdfOperations.extractEmailAddress();
			break;

		case 4:
			pdfOperations.splifPDFBySize();
			break;

		default:
			
			System.out.println("Sorry, There are no operations associated with the specified number!");
			
			
			
		}

	}
}

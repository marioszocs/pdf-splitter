# PDF Splitter

The **PDF Splitter** is a desktop application built in Java for splitting PDF files by size, by pages, and extracting email addresses from PDF documents. This project utilizes the **PDFBox** and **iTextPDF** libraries to perform these operations effectively.

---

## Features
- **Split PDFs by size**: Break large PDF files into smaller chunks of a specified size.
- **Split PDFs by pages**: Divide a PDF into multiple parts after a given number of pages.
- **Extract email addresses**: Retrieve and save all email addresses found in a PDF document to a `.txt` file.

---

## How It Works
### Main Operations:
1. **Split PDF After Specific Pages**:
   - Select the number of pages after which the PDF should be split.
   - The resulting PDFs will be saved in the output folder.

2. **Split PDF by Specific Size**:
   - Specify the maximum allowable size for each split PDF in kilobytes.
   - The application will create multiple PDFs, ensuring each part adheres to the size limit.

3. **Extract Email Addresses**:
   - Scans the text within PDF files for valid email addresses.
   - Extracted emails are saved in a `.txt` file for easy access.

---

## Requirements
- **Java 8** or higher.
- **Maven** for dependency management.

---

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/pdf-splitter.git
   cd pdf-splitter
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   java -cp target/pdfsplitting-0.0.1-SNAPSHOT.jar com.pdfsplitting.Main
   ```

---

## Example Screenshots

### Split PDF by Size
![PDF Split by Size](https://user-images.githubusercontent.com/11271085/117545322-89f8e200-b025-11eb-8727-b70582fe6807.PNG)

### Input Selection
![Input Example](https://user-images.githubusercontent.com/11271085/117545354-b6acf980-b025-11eb-809d-2d05f261eee2.PNG)

### Output Example
![Output Example](https://user-images.githubusercontent.com/11271085/117545364-c0cef800-b025-11eb-845a-c9dc683ef457.PNG)

---

## Libraries Used
- **[Apache PDFBox](https://pdfbox.apache.org/)**: For handling PDF documents.
- **[iTextPDF](https://itextpdf.com/)**: For advanced PDF processing.

---

## Project Structure
```
marioszocs-pdf-splitter/
├── pom.xml                  # Maven build configuration
├── README.md                # Project documentation
├── src/main/java/com/pdfsplitting/
│   ├── Main.java            # Entry point of the application
│   ├── PDFFileOperations.java # Interface for PDF operations
│   ├── PDFFileOperationsImp.java # Implementation of PDF operations
│   ├── PdfUtilities.java    # Utility methods for PDF handling
└── .gitignore               # Ignored files
```

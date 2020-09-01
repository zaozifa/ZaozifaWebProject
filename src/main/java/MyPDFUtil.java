import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

/**
 * 截取pdf 的第from页至第end页，组成一个新的文件名
 */
public class MyPDFUtil {

    public static void main(String[] args) {
        partitionPdfFile("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\pdf.pdf", "Chapter04.pdf", 31, 32);
    }

    /**
     * 截取pdfFile的第from页至第end页，组成一个新的文件名
     *
     * @param pdfFile
     * @param from
     * @param end
     */
    public static void partitionPdfFile(String pdfFile,
                                        String newFile, int from, int end) {
        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(pdfFile);
            int n = reader.getNumberOfPages();
            if (end == 0) {
                end = n;
            }
            ArrayList<String> savepaths = new ArrayList<String>();
            String staticpath = pdfFile.substring(0, pdfFile.lastIndexOf("\\") + 1);
            String savepath = staticpath + newFile;
            savepaths.add(savepath);
            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(savepaths.get(0)));
            document.open();
            for (int j = from; j <= end; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}  
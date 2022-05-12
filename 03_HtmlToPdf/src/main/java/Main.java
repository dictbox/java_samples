import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.font.FontProvider;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

/**
 * HTML 转换成 PDF 文档。
 */
public class Main {
    public static void main(String[] args) {
        String url = "https://wanlei.net";

        //生成PDF
        makePdf(url);
    }

    public static void makePdf(String url) {
        try {
            //注：如果是网页上下载的情况，可以直接写入到：response.getOutputStream()中。
            OutputStream outputStream = new FileOutputStream("out.pdf");

            try {
                //还有一种ITextRender的方式，稍后加上。

                ConverterProperties properties = new ConverterProperties();
                //PageSize pageSize = new PageSize(850, 1700);
                //pdf.setDefaultPageSize(pageSize);
                //ConverterProperties properties = new ConverterProperties();
                //MediaDeviceDescription mediaDeviceDescription = new MediaDeviceDescription(MediaType.SCREEN);
                //mediaDeviceDescription.setWidth(pageSize.getWidth());
                //properties.setMediaDeviceDescription(mediaDeviceDescription);

                //这个中文需要font-asia包支持。
                FontProvider fontProvider = new DefaultFontProvider();
                PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
                fontProvider.addFont(font.getFontProgram(), "UniGB-UCS2-H");
                //fontProvider.addFont(FontProgramFactory.createRegisteredFont("微软雅黑"));
                properties.setFontProvider(fontProvider);

                HtmlConverter.convertToPdf(new URL(url).openStream(), outputStream, properties);
            } catch (IOException e) {
                System.out.println(e.toString());
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }

            System.out.println("生成PDF成功");
        } catch (Exception e) {
            System.out.println("html转换为pdf失败");
        }
    }
}

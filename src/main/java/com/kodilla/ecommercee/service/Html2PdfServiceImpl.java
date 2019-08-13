package com.kodilla.ecommercee.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.kodilla.ecommercee.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

@Service
@Log
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Html2PdfServiceImpl implements Html2PdfService {

    private final TemplateEngine templateEngine;

    @Override
    public InputStreamResource html2PdfGenerator(Order order) {

        Context context = new Context();
        context.setVariable("number", order.getId());
        context.setVariable("date", order.getDate());

        final String html = templateEngine.process("confirmation", context);

        log.log(INFO, html);


        final String DEST = "src/main/resources/confirmations/FA-2018-09-04-0001.pdf";
        System.out.println(DEST);
        try {
            HtmlConverter.convertToPdf(html, new FileOutputStream(DEST));
            return new InputStreamResource(new FileInputStream(DEST));

        } catch (IOException e) {
            log.log(SEVERE, e.getMessage(), e);
            return null;
        }
    }

}
package com.kodilla.ecommercee.service.impl;

import com.itextpdf.html2pdf.HtmlConverter;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.service.Html2PdfService;
import com.kodilla.ecommercee.service.UserService;
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
import java.util.Optional;


import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

@Service
@Log
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Html2PdfServiceImpl implements Html2PdfService {

    private final TemplateEngine templateEngine;
    private final UserService userService;

    @Override
    public InputStreamResource html2PdfGenerator(Order order) {

        Context context = new Context();
        context.setVariable("number", order.getId());
        context.setVariable("date", order.getDate());
        Optional<User> user = userService.getUser(order.getUser().getId());
        if(user.isPresent()){
            context.setVariable("address", user.get().getAddress());
            context.setVariable("email", user.get().getEmail());
            context.setVariable("username", user.get().getUsername());
        }

        final String htmlCode = templateEngine.process("confirmation", context);

        log.log(INFO, htmlCode);


        final String DEST = "src/main/resources/confirmations/order-00" + order.getId() +".pdf";
        System.out.println(DEST);
        try {
            HtmlConverter.convertToPdf(htmlCode, new FileOutputStream(DEST));
            return new InputStreamResource(new FileInputStream(DEST));

        } catch (IOException e) {
            log.log(SEVERE, e.getMessage(), e);
            return null;
        }
    }
}
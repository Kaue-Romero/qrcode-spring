package com.leivingson.qrcode_generator.controller;

import com.google.zxing.WriterException;
import com.leivingson.qrcode_generator.dto.qrcode.QrcodeGenerateRequest;
import com.leivingson.qrcode_generator.dto.qrcode.QrcodeGenerateResponse;
import com.leivingson.qrcode_generator.service.QrCodeGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/qrcode")
public class QrCodeController {

    private final QrCodeGeneratorService qrCodeGeneratorService;

    public QrCodeController(QrCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    @PostMapping
    public ResponseEntity<QrcodeGenerateResponse> generate(@RequestBody QrcodeGenerateRequest request) {
        try {
            QrcodeGenerateResponse response = this.qrCodeGeneratorService.generateAndUploadQrCode(request.text());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error generating QR code", e);
        } catch (IOException e) {
            throw new RuntimeException("Error uploading QR code", e);
        } catch (WriterException e) {
            throw new RuntimeException("Error writing QR code", e);
        }
    }
}

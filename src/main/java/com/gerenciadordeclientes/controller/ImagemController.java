package com.gerenciadordeclientes.controller;


import com.gerenciadordeclientes.domain.Imagem;
import com.gerenciadordeclientes.service.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    private final ImagemService imagemService;

    @Autowired
    public ImagemController(ImagemService imagemService) {
        this.imagemService = imagemService;
    }

    @PostMapping
    public Imagem upload(@RequestParam("nome") String nome, @RequestParam("imagem") MultipartFile imagem) throws IOException {
        return imagemService.salvarImagem(nome, imagem);
    }
}

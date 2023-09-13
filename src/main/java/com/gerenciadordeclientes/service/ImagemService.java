package com.gerenciadordeclientes.service;


import com.gerenciadordeclientes.model.Imagem;
import com.gerenciadordeclientes.repository.ImagemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImagemService {

    private final ImagemRepository imagemRepository;

    public ImagemService(ImagemRepository imagemRepository) {
        this.imagemRepository = imagemRepository;
    }

    public Imagem salvarImagem(String nome, MultipartFile imagem) throws IOException {
        Imagem novaImagem = new Imagem();
        novaImagem.setNome(nome);
        novaImagem.setDados(imagem.getBytes());

        return imagemRepository.save(novaImagem);
    }

}

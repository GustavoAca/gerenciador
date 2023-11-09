package com.gerenciadordeclientes.service;


import com.gerenciadordeclientes.domain.Imagem;
import com.gerenciadordeclientes.repository.ImagemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class ImagemService {

    private final ImagemRepository imagemRepository;

    public ImagemService(ImagemRepository imagemRepository) {
        this.imagemRepository = imagemRepository;
    }

    public Imagem salvarImagem(String nome, MultipartFile imagem) throws IOException {
        byte[] dados = Base64.getDecoder().decode(imagem.getBytes());
        Imagem novaImagem = new Imagem();
        novaImagem.setNome(nome);
        novaImagem.setDados(dados);

        return imagemRepository.save(novaImagem);
    }

}

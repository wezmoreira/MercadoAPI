package com.github.wezmoreira.site.util;

import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class CodigoSku{

    public static final int SET_TAMANHO = 10;
    public static final int NUMERO = 100;

    public String skuId(){
        Random random = new Random();
        Set set = new HashSet<Integer>(SET_TAMANHO);
        while(set.size()< SET_TAMANHO) {
            while (!set.add(random.nextInt(NUMERO)));
        }
        assert set.size() == SET_TAMANHO;
        String nome = "wrm";
        var numeroAleatorio = set.toString().replaceAll("[\\,\\[\\]\\ \\:,]","");
        return nome+numeroAleatorio;
    }
}

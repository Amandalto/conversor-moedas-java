package com.conversor.servico;

import com.conversor.modelo.TaxaDeCambio;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApiCambio {

    
    private static final String API_KEY = "d51d2b1bf4656c219f6a3f77";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";

    /**
     * Busca as taxas de câmbio para uma moeda base específica.
     * @param moedaBase Ex: "USD", "BRL", "EUR"
     * @return Um objeto TaxaDeCambio contendo as taxas.
     * @throws IOException Se houver erro de rede.
     * @throws InterruptedException Se a requisição for interrompida.
     */
    public TaxaDeCambio buscarTaxas(String moedaBase) throws IOException, InterruptedException {

        // Monta a URL completa
        String url = API_URL + API_KEY + "/latest/" + moedaBase;

        // 1. Cria o Cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // 2. Cria a Requisição (Request)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // 3. Envia a Requisição e obtém a Resposta (Response)
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        // 4. Pega o corpo da resposta (o JSON)
        String jsonBody = response.body();

        // 5. Usa o Gson para converter o JSON
        // na nossa classe TaxaDeCambio
        Gson gson = new Gson();
        return gson.fromJson(jsonBody, TaxaDeCambio.class);
    }
}
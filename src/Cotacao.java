import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Cotacao {
    private Moeda moedaOrigem;
    private Moeda moedaDestino;
    private String url;

    public Cotacao(Moeda moedaOrigem, Moeda moedaDestino) {
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.url = "https://v6.exchangerate-api.com/v6/7b5ee830d73cf06b65c41ce0/pair/" +
                moedaOrigem.getSigla() + "/" + moedaDestino.getSigla();
    }

    public MoedaConvertida converter(double valor) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.url + "/" + valor))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String responseJson = response.body();

            Gson gson = new Gson();
            return gson.fromJson(responseJson, MoedaConvertida.class);
        } catch (Exception e) {
            throw new RuntimeException("Problema na conexão");
        }
    }

    public Moeda getMoedaOrigem() {
        return moedaOrigem;
    }

    public Moeda getMoedaDestino() {
        return moedaDestino;
    }

    @Override
    public String toString() {
        return moedaOrigem + " ===> " + moedaDestino;
    }
}

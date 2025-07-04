import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorMoeda {

    public static double obterCambio(String base, String currency, double amount) {
        try {
            String url = "https://v6.exchangerate-api.com/v6/" + API + "/pair/" + base + "/" + currency + "/" + amount;

            URI uri = URI.create(url);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Erro HTTP: " + response.statusCode());
                return -1;
            }

            Gson gson = new Gson();
            JsonObject json = gson.fromJson(response.body(), JsonObject.class);

            if (json.has("conversion_result")) {
                return json.get("conversion_result").getAsDouble();
            } else {
                System.out.println("Erro: campo 'conversion_result' não encontrado.");
                return -1;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar o câmbio: " + e.getMessage());
            return -1;
        }
    }
}


package com.example.miniecommerce.service.MellhorEnvioServices;

import com.example.miniecommerce.domain.Pedido;
import com.example.miniecommerce.web.dto.in.PedidoEnviarRequestDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class MelhorEnvioApiService {

    private final MelhorEnvioAuthService melhorEnvioAuthService;
    private final RestTemplate restTemplate = new RestTemplate();

    public MelhorEnvioApiService(MelhorEnvioAuthService authService) {
        this.melhorEnvioAuthService = authService;
    }

    public String criarEnvio(Pedido pedido, PedidoEnviarRequestDto dto) {

        Map<String, Object> payload = Map.of(
                "from", Map.of("name", "Minha Loja", "phone", "11999999999"),
                "to", Map.of("name", dto.destinatarioNome(),
                        "address", dto.destinatarioEndereco(),
                        "zipcode", dto.destinatarioCep()),
                "products", pedido.getItens().stream()
                        .map(item -> Map.of("name", item.getProduto().getNome(),
                                "quantity", item.getQuantidade()))
                        .toList()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(melhorEnvioAuthService.getToken());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://sandbox.melhorenvio.com.br/api/v2/me/shipment",
                request,
                Map.class
        );

        // pega algum identificador (ex.: número da etiqueta)
        return response.getBody().get("id").toString();
    }
}






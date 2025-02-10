package br.com.fiap.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import br.com.fiap.model.vo.PokemonVO;


public class PokeApiService {
	public PokemonVO getPokemon(String nomePokemon) {
		
		PokemonVO pokemon = null;
		
		HttpGet request = new HttpGet("https://pokeapi.co/api/v2/pokemon/" + nomePokemon);
		
		try(
			CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
			CloseableHttpResponse response = httpClient.execute(request)
				)	{
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) { // pokemon encontrado
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String result = null;
					try {
						result = EntityUtils.toString(entity);
						 Gson gson = new Gson();
		                 pokemon = gson.fromJson(result, PokemonVO.class);
					} catch (ParseException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} else if (statusCode == HttpStatus.SC_NOT_FOUND) { // pokemon nao encontrado na api
				System.out.println("Pokemon não encontrado.");
			} else {
				System.out.println("Erro ao acessar a API: " + statusCode);
			} 
		}
	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			} 
		return pokemon;
	}
}


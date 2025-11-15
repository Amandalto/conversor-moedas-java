package com.conversor.modelo;

import com.google.gson.annotations.SerializedName;
import java.util.Map;


public record TaxaDeCambio(
    String base_code,
    @SerializedName("conversion_rates") 
    Map<String, Double> taxasDeConversao 
) {}
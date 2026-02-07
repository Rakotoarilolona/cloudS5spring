package com.carte.clouds5spring.hutil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;

public class Hjson {
    
    private static final ObjectMapper objectMapper = createObjectMapper();
    
    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Configuration pour un JSON lisible (indentation)
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // Désactiver l'échec sur les propriétés inconnues (optionnel)
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // Support pour les dates Java 8+ (LocalDate, LocalDateTime, etc.)
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
    
    /**
     * Transforme un objet en JSON
     * @param obj L'objet à transformer (peut être n'importe quel objet)
     * @return String JSON formatée
     * @throws RuntimeException si la transformation échoue
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la transformation en JSON"+e.getMessage(), e);
        }
    }
    
    /**
     * Transforme une liste d'objets en JSON
     * @param objects La liste d'objets à transformer
     * @return String JSON formatée représentant un tableau
     * @throws RuntimeException si la transformation échoue
     */
    public static String toJson(List<Object> objects) {
        try {
            return objectMapper.writeValueAsString(objects);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la transformation de la liste en JSON"+e.getMessage(), e);
        }
    }
    
    /**
     * Version générique de la méthode pour les listes avec type spécifique
     * Cette méthode offre plus de flexibilité avec les génériques
     * @param <T> Type des objets dans la liste
     * @param objects Liste d'objets de type T
     * @return String JSON formatée
     */
    public static <T> String toJsonList(List<T> objects) {
        try {
            return objectMapper.writeValueAsString(objects);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la transformation de la liste en JSON"+e.getMessage(), e);
        }
    }
    public static String formatJson(String data, String status, String message)
    {
        String safeStatus = escapeJsonString(status);
        String safeMessage = escapeJsonString(message);
        String safeData = (data == null || data.isBlank()) ? "null" : data;

        // data is expected to already be a JSON value (object/array), so it must not be quoted.
        return "{ \"status\": \"" + safeStatus + "\", \"data\": " + safeData + ", \"message\": \"" + safeMessage + "\" }";
    }

    private static String escapeJsonString(String value) {
        if (value == null) {
            return "";
        }

        return value
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t");
    }
}
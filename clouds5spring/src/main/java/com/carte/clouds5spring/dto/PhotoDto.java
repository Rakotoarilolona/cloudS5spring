package com.carte.clouds5spring.dto;

import com.carte.clouds5spring.entity.Photo;

public class PhotoDto {
    private Integer id;
    private Integer routeProblemeId;

    /**
     * Optionnel: contenu binaire de l'image.
     * Jackson le sérialise en Base64 côté JSON.
     */
    private byte[] bytes;

    public PhotoDto() {
    }

    public PhotoDto(Integer id, Integer routeProblemeId, byte[] bytes) {
        this.id = id;
        this.routeProblemeId = routeProblemeId;
        this.bytes = bytes;
    }

    public static PhotoDto fromEntity(Photo photo, boolean includeBytes) {
        if (photo == null) {
            return null;
        }
        Integer rpId = null;
        if (photo.getRouteProbleme() != null) {
            rpId = photo.getRouteProbleme().getId();
        }
        return new PhotoDto(photo.getId(), rpId, includeBytes ? photo.getBytes() : null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRouteProblemeId() {
        return routeProblemeId;
    }

    public void setRouteProblemeId(Integer routeProblemeId) {
        this.routeProblemeId = routeProblemeId;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}

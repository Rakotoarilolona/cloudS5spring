package com.carte.clouds5spring.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.carte.clouds5spring.dto.PhotoDto;
import com.carte.clouds5spring.dto.RouteProblemeDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "routeprobleme")
public class RouteProbleme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "surface", precision = 15, scale = 2)
    private BigDecimal surface;

    @Column(name = "budget", precision = 15, scale = 2)
    private BigDecimal budget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_routeentreprise")
    private RouteEntreprise routeEntreprise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_routestatus")
    private RouteStatus routeStatus;

    private String firebaseId;

    private LocalDateTime updatedAt;

    // Lié a un user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "longitude", precision = 15, scale = 6)
    private BigDecimal longitude;

    @Column(name = "latitude", precision = 15, scale = 6)
    private BigDecimal latitude;

    public String getProblemeDescription() {
        return problemeDescription;
    }

    public void setProblemeDescription(String problemeDescription) {
        this.problemeDescription = problemeDescription;
    }

    @Column(name = "problemedescription", length = 255)
    private String problemeDescription;

    @JsonIgnore
    @OneToMany(mappedBy = "routeProbleme", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos = new java.util.ArrayList<>();



    public RouteProbleme() {}

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        if (photos == this.photos) {
            return;
        }

        if (this.photos == null) {
            this.photos = new java.util.ArrayList<>();
        } else {
            this.photos.clear();
        }

        if (photos == null) {
            return;
        }

        for (Photo photo : photos) {
            if (photo != null) {
                photo.setRouteProbleme(this);
                this.photos.add(photo);
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getSurface() {
        return surface;
    }

    public void setSurface(BigDecimal surface) {
        this.surface = surface;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public RouteEntreprise getRouteEntreprise() {
        return routeEntreprise;
    }

    public void setRouteEntreprise(RouteEntreprise routeEntreprise) {
        this.routeEntreprise = routeEntreprise;
    }

    public RouteStatus getRouteStatus() {
        return routeStatus;
    }

    public void setRouteStatus(RouteStatus routeStatus) {
        this.routeStatus = routeStatus;
    }

    public String getFirebaseId() {
        return firebaseId;
    }
    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
    public BigDecimal getLatitude() {
        return latitude;
    }
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public RouteProblemeDto toDto() {
        RouteProblemeDto dto = new RouteProblemeDto();
        if(this.getId() != null) {
            dto.setId(this.getId());
        }
        if(this.getSurface() != null) {
            dto.setSurface(this.getSurface());
        }
        if(this.getBudget() != null) {
            dto.setBudget(this.getBudget());
        }
        if(this.getRouteEntreprise() != null && this.getRouteEntreprise().getId() != null) {
            dto.setRouteEntrepriseId(this.getRouteEntreprise().getId());
            dto.setRouteEntrepriseName(this.getRouteEntreprise().getLabel());
        }
        if(this.getRouteStatus() != null && this.getRouteStatus().getId() != null) {
            dto.setRouteStatusId(this.getRouteStatus().getId());
            dto.setRouteStatusName(this.getRouteStatus().getLabel());
        }
        if(this.getLongitude() != null) {
            dto.setLongitude(this.getLongitude());
        }
        if(this.getLatitude() != null) {
            dto.setLatitude(this.getLatitude());
        }
        if(this.getProblemeDescription() != null) {
            dto.setProblemeDescription(this.getProblemeDescription());
        }

        if (this.getPhotos() != null && !this.getPhotos().isEmpty()) {
            java.util.ArrayList<PhotoDto> photoDtos = new java.util.ArrayList<>(this.getPhotos().size());
            for (Photo p : this.getPhotos()) {
                photoDtos.add(PhotoDto.fromEntity(p, false));
            }
            dto.setPhotos(photoDtos);
        }

        return dto;
    }
}

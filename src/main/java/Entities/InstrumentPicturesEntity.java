package Entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "INSTRUMENT_PICTURES", schema = "APP")
public class InstrumentPicturesEntity {
    @Basic
    @Column(name = "IMAGE_URL", nullable = false, length = 1600)
    private String imageUrl;
    @Basic
    @Column(name = "INSTRUMENT_ID", nullable = false)
    private int instrumentId;

    public InstrumentPicturesEntity() {
    }

    public InstrumentPicturesEntity(String imageUrl, Integer instrumentId) {
        setImageUrl(imageUrl);
        setInstrumentId(instrumentId);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(int instrumentId) {
        this.instrumentId = instrumentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstrumentPicturesEntity that = (InstrumentPicturesEntity) o;

        if (instrumentId != that.instrumentId) return false;
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = imageUrl != null ? imageUrl.hashCode() : 0;
        result = 31 * result + instrumentId;
        return result;
    }

    @Override
    public String toString() {
        return "IMAGE_URL: " + imageUrl + " INSTRUMENT_ID: " + instrumentId;
    }
}

package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class InstrumentPicturesEntityPK implements Serializable {
    @Column(name = "IMAGE_URL", nullable = false, length = 1600)
    @Id
    private String imageUrl;

    @Column(name = "INSTRUMENT_ID", nullable = false)
    @Id
    private int instrumentId;

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

        InstrumentPicturesEntityPK that = (InstrumentPicturesEntityPK) o;

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
}

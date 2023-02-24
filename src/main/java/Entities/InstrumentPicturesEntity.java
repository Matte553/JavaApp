package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "INSTRUMENT_PICTURES", schema = "APP")
@IdClass(InstrumentPicturesEntityPK.class)
public class InstrumentPicturesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IMAGE_URL")
    private String imageUrl;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "INSTRUMENT_ID")
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
}

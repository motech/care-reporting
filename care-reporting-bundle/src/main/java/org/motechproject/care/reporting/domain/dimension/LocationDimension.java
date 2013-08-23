package org.motechproject.care.reporting.domain.dimension;

import javax.persistence.*;


@Entity
@Table(name = "location_dimension", uniqueConstraints = @UniqueConstraint(columnNames = {"state", "district", "block"}))
public class LocationDimension implements java.io.Serializable {

	private int id;
    private String state;
    private String district;
	private String block;

    public LocationDimension() {

    }

    public LocationDimension(String state, String district, String block) {
        this.state = state;
        this.district = district;
        this.block = block;
    }

    @Id
	@Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Column(name = "district")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(name = "block")
    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}

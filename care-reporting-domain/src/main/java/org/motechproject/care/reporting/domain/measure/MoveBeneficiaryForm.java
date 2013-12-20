package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "move_beneficiary_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class MoveBeneficiaryForm extends Form{
    private int id;
    private Flw flw;
    private MotherCase motherCase;
    private Date timeEnd;
    private Date timeStart;
    private Date dateModified;
    private Date creationTime = new Date();
    private String confirmMove;
    private Integer newWard;
    private Integer newAwcc;
    private String confirmAgain;

    public MoveBeneficiaryForm() {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
   @Cascade({	CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
    public Flw getFlw() {
        return this.flw;
    }

    public void setFlw(Flw flw) {
        this.flw = flw;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id")
   @Cascade({	CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
    public MotherCase getMotherCase() {
        return this.motherCase;
    }

    public void setMotherCase(MotherCase motherCase) {
        this.motherCase = motherCase;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_end")
    public Date getTimeEnd() {
        return this.timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_start")
    public Date getTimeStart() {
        return this.timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_modified")
    public Date getDateModified() {
        return this.dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Column(name = "confirm_move")
    public String getConfirmMove() {
        return confirmMove;
    }

    public void setConfirmMove(String confirmMove) {
        this.confirmMove = confirmMove;
    }

    @Column(name = "new_ward")
    public Integer getNewWard() {
        return newWard;
    }

    public void setNewWard(Integer newWard) {
        this.newWard = newWard;
    }

    @Column(name = "new_awcc")
    public Integer getNewAwcc() {
        return newAwcc;
    }

    public void setNewAwcc(Integer newAwcc) {
        this.newAwcc = newAwcc;
    }

    @Column(name = "confirm_again")
    public String getConfirmAgain() {
        return confirmAgain;
    }

    public void setConfirmAgain(String confirmAgain) {
        this.confirmAgain = confirmAgain;
    }

    @Override
    public String toString() {
        return FormToString.toString(this);
    }
}

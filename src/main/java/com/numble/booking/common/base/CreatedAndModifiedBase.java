package com.numble.booking.common.base;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <pre>
 * Class Name : CreatedAndModifiedBase
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-15	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-15
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CreatedAndModifiedBase {
    @Column(updatable = false)
    @CreatedBy
    private Long createdBy;

    @Column(updatable = false)
    @CreatedDate
    private LocalDate createdDate;

    @Column(updatable = false)
    @CreatedDate
    private LocalTime createdTime;

    @Column
    @LastModifiedBy
    private Long lastModifiedBy;

    @Column
    @LastModifiedDate
    private LocalDate lastModifiedDate;

    @Column
    @LastModifiedDate
    private LocalTime lastModifiedTime;

    /**
     * 작성자 set
     * 수정자도 같이 set 한다.
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
        this.createdDate = LocalDate.now();
        this.createdTime = LocalDateTime.now().toLocalTime();
        setLastModifiedBy(createdBy);
    }

    /**
     * 수정자 set
     * 작성자가 null일경우(새로 생성되었을 경우) 작성자도 set한다.
     */
    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = LocalDate.now();
        this.lastModifiedTime = LocalDateTime.now().toLocalTime();
        if (this.createdBy == null) {
            this.createdBy = lastModifiedBy;
        }
    }
}

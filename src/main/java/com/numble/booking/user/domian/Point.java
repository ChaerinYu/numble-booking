package com.numble.booking.user.domian;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <pre>
 * Class Name : Point
 * Description : 사용자가 갖고 있는 포인트
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
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point {

    @Id
    @Column(name = "pointId", nullable = false)
    @GeneratedValue(generator = "pointSeqGenerator")
    @GenericGenerator(name = "pointSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    // 포인트
    private Integer point;
}

package com.numble.booking.util;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * <pre>
 * Class Name : SeqGenerator
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
public class SeqGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {

        String seq = (System.currentTimeMillis() - 1500000000000L)
                + StringUtils.leftPad(Integer.toString((int)(Math.random()*9999)), 4, "0"); // ( currentTimeMillis 13자리 - 1500000000000L ) + random 4자리로 16자리 맞추기

        return Long.parseLong(seq);
    }
}

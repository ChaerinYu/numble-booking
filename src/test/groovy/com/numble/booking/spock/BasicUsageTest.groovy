package com.numble.booking.spock

import spock.lang.Specification

/**
 * <pre>
 * Class Name : BasicUsageTest 
 * Description : 
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-22 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-06-22
 **/
class BasicUsageTest extends Specification {

    def "maximum of two numbers"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b | c
        5 | 1 | 5
        3 | 9 | 9
    }
}

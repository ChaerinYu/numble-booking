package com.numble.booking.common.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * Class Name : Profiles
 * Description : 프로파일
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-07-03 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-07-03
 */
@Getter
@RequiredArgsConstructor
public enum Profiles {
    /**
     * 테스트 코드
     */
    TEST(Constants.TEST),

    /**
     * 로컬
     */
    LOCAL(Constants.LOCAL),

    /**
     * 개발
     */
    DEVELOPMENT(Constants.DEVELOPMENT),

    /**
     * 운영 배포용
     */
    PRODUCTION(Constants.PRODUCTION);


    private final String name;

    public static class Constants {

        /** The Constant TEST-CODE. */
        public static final String TEST = "test";

        /** The Constant LOCAL. */
        public static final String LOCAL = "local";

        /** The Constant DEVELOPMENT. */
        public static final String DEVELOPMENT = "dev";

        /** The Constant PRODUCTION. */
        public static final String PRODUCTION = "prd";
    }
}

package org.chomookun.arch4vue.common;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class PageableUtils {

    /**
     * Converts pageable to Content-Range header value.
     * @param unit unit
     * @param pageable pageable
     * @param totalSize total size
     * @return content range header value
     */
    public static String toContentRange(String unit, Pageable pageable, Number totalSize) {
        StringBuilder contentRange = new StringBuilder("");
        // unit
        contentRange.append(Optional.ofNullable(unit).orElse("unit"));
        // range
        contentRange.append(" ");
        if(pageable == null || pageable.isUnpaged()) {
            contentRange.append("*");
        }else{
            contentRange.append(pageable.getOffset())
                    .append("-")
                    .append(pageable.getOffset() + pageable.getPageSize());
        }
        // size
        contentRange.append("/");
        if(totalSize == null) {
            contentRange.append("*");
        }else{
            contentRange.append(totalSize);
        }
        return contentRange.toString();
    }

    /**
     * Converts pageable to Content-Range header value.
     * @param unit unit
     * @param pageable pageable
     * @return content range header value
     */
    public static String toContentRange(String unit, Pageable pageable) {
        return toContentRange(unit, pageable, null);
    }

}
package com.quincy.java.base.threadreadtxt;

import java.util.Objects;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: FileContentZone
 * Author: quincy
 * Date: 2020/6/4 下午7:45
 * History:
 * @author quincy
 */

public class FileContentZone {
    private long start;
    private long end;

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileContentZone that = (FileContentZone) o;
        return start == that.start &&
                end == that.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}

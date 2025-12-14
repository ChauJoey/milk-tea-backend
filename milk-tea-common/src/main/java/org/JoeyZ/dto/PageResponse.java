package org.JoeyZ.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PageResponse<T> {
    private long total;
    private int page;
    private int pageSize;
    private List<T> data;

    public PageResponse(long total, int page, int pageSize, List<T> data) {
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
        this.data = data;
    }

}

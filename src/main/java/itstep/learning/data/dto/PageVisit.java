package itstep.learning.data.dto;

import java.sql.Timestamp;

public class PageVisit {

    private final int id;
    private final String pageUrl;
    private final Timestamp visitDate;

    public PageVisit(int id, String pageUrl, Timestamp visitDate) {
        this.id = id;
        this.pageUrl = pageUrl;
        this.visitDate = visitDate;
    }

    public int getId() {
        return id;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public Timestamp getVisitDate() {
        return visitDate;
    }
}
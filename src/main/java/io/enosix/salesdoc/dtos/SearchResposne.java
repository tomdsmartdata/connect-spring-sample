package io.enosix.salesdoc.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchResposne<T> {

    @JsonProperty("results")
    public List<T> results;

    @JsonProperty("pagingInfo")
    public PagingInfo pagingInfo;

    @JsonProperty("messages")
    public Messages[] messages;
}

class PagingInfo {
    public int totalRecords;
    public int pageSize;
    public int pageNumber;
}

class Messages {
    public String type;
    public String message;
}
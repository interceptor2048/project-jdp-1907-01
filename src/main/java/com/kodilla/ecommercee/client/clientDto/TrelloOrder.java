package com.kodilla.ecommercee.client.clientDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloOrder {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String orderName;

    @JsonProperty("desc")
    private String descriptionOfOrder;

    @JsonProperty("shortUrl")
    private String orderShortUrl;

    @JsonProperty("idList")
    private String listId;
}

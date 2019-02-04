package org.knowm.xchange.coinmarketcap.pro.v1.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class CoinMarketCapCurrencyInfo {

  private String symbol;
  private CoinMarketCapUrls urls;
  private String name;
  private String logo;
  private int id;
  private String category;
  private String slug;
  private CoinMarketCapPlatform platform;
  private List<String> tags;
  private String dateAdded;

  public CoinMarketCapCurrencyInfo(
      @JsonProperty("symbol") String symbol,
      @JsonProperty("urls") CoinMarketCapUrls urls,
      @JsonProperty("name") String name,
      @JsonProperty("logo") String logo,
      @JsonProperty("id") int id,
      @JsonProperty("category") String category,
      @JsonProperty("slug") String slug,
      @JsonProperty("platform") CoinMarketCapPlatform platform,
      @JsonProperty("tags") List<String> tags,
      @JsonProperty("date_added") String dateAdded) {
    this.symbol = symbol;
    this.urls = urls;
    this.name = name;
    this.logo = logo;
    this.id = id;
    this.category = category;
    this.slug = slug;
    this.platform = platform;
    this.tags = tags;
    this.dateAdded = dateAdded;
  }

  public String getSymbol() {
    return symbol;
  }

  public CoinMarketCapUrls getUrls() {
    return urls;
  }

  public String getName() {
    return name;
  }

  public String getLogo() {
    return logo;
  }

  public int getId() {
    return id;
  }

  public String getCategory() {
    return category;
  }

  public String getSlug() {
    return slug;
  }

  public CoinMarketCapPlatform getPlatform() {
    return platform;
  }

  public List<String> getTags() {
    return tags;
  }

  public String getDateAdded() {
    return dateAdded;
  }

  @Override
  public String toString() {
    return "CoinMarketCapCurrencyInfo{"
        + "symbol='"
        + symbol
        + '\''
        + ", urls="
        + urls
        + ", name='"
        + name
        + '\''
        + ", logo='"
        + logo
        + '\''
        + ", id="
        + id
        + ", category='"
        + category
        + '\''
        + ", slug='"
        + slug
        + '\''
        + ", platform="
        + platform
        + ", tags="
        + tags
        + ", dateAdded='"
        + dateAdded
        + '\''
        + '}';
  }
}

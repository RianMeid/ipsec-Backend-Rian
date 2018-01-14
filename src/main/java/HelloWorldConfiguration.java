/**
 * Created by Rian on 06/12/2017.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.bundles.assets.AssetsBundleConfiguration;
import io.dropwizard.bundles.assets.AssetsConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class HelloWorldConfiguration extends Configuration implements AssetsBundleConfiguration {
  @Valid
  @NotNull
  @JsonProperty
  private final AssetsConfiguration assets = new AssetsConfiguration();

  @NotEmpty
  private String template;

  @NotEmpty
  private String defaultName = "GUEST";

  @JsonProperty
  public String getTemplate() {
    return template;
  }

  @JsonProperty
  public void setTemplate(String template) {
    this.template = template;
  }

  @JsonProperty
  public String getDefaultName() {
    return defaultName;
  }

  @JsonProperty
  public void setDefaultName(String name) {
    this.defaultName = name;
  }

  public AssetsConfiguration getAssetsConfiguration() {
    return assets;
  }
}
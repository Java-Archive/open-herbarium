package org.infinitenature.leafletzoomify;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IMAGE_PROPERTIES")
@XmlAccessorType(XmlAccessType.FIELD)
public class ZoomifyImageProperties {
  @XmlAttribute(name = "WIDTH")
  private int width;
  @XmlAttribute(name = "HEIGHT")
  private int height;

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ZoomifyImageProperties [width=");
    builder.append(width);
    builder.append(", height=");
    builder.append(height);
    builder.append("]");
    return builder.toString();
  }

}

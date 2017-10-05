package org.openherbarium.module.backend.imageservice;

import org.rapidpm.frp.model.Result;

/**
 *
 */
public interface ImageService {

  public Result<byte[]> loadImageTile(ImageKey imageKey);

}

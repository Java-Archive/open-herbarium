package org.openherbarium.module.ui.component.mainview.searchview;

import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.mock.client.MetadataServiceMOCKClient;
import org.rapidpm.ddi.ResponsibleFor;
import org.rapidpm.ddi.implresolver.ClassResolver;

@ResponsibleFor(MetadataService.class)
public class MetadataServiceClassResolver implements ClassResolver<MetadataService> {
  @Override
  public Class<? extends MetadataService> resolve(final Class<MetadataService> interf) {
    return MetadataServiceMOCKClient.class;
  }
}

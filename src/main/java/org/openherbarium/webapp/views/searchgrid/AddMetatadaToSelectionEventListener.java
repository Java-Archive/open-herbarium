package org.openherbarium.webapp.views.searchgrid;

import org.openherbarium.webapp.model.Metadata;

@FunctionalInterface
public interface AddMetatadaToSelectionEventListener {
  void addMetadataToSelection(Metadata metadata);
}

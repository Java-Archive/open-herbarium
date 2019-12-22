package org.openherbarium.webapp.views.selection;

import org.openherbarium.webapp.model.Metadata;

@FunctionalInterface
public interface RemoveMetadataFromSelectionListener {

  void removeMetadata(Metadata metadata);

}

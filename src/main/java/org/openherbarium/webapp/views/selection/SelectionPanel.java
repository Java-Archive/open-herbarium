package org.openherbarium.webapp.views.selection;

import java.util.ArrayList;
import java.util.List;
import org.openherbarium.webapp.model.Metadata;
import org.openherbarium.webapp.views.searchgrid.AddMetatadaToSelectionEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SelectionPanel extends Composite<VerticalLayout>
    implements AddMetatadaToSelectionEventListener, RemoveMetadataFromSelectionListener {
  private static final int MAX_METADATA = 2;
  private List<Metadata> metadatas = new ArrayList<>();


  private void render() {
    getContent().removeAll();
    for (Metadata metadata : metadatas) {
      getContent().add(new PreviewComponent(metadata, this));
    }

  }

  @Override
  public void addMetadataToSelection(Metadata metadata) {
    if (metadatas.size() >= MAX_METADATA) {
      metadatas.remove(0);
    }
    metadatas.add(metadata);
    render();

  }

  @Override
  public void removeMetadata(Metadata metadata) {
    metadatas.remove(metadata);
    render();

  }
}

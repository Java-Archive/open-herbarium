package org.openherbarium.webapp.model.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.infinitenature.werbeo.client.Werbeo;
import org.openherbarium.webapp.model.Metadata;
import org.openherbarium.webapp.model.MetadataFilter;
import org.openherbarium.webapp.model.MetadataService;
import org.openherbarium.webapp.model.Person;
import org.openherbarium.webapp.model.Scan;
import org.openherbarium.webapp.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetadataServiceMockImpl implements MetadataService {
  private static final Logger LOGGER = LoggerFactory.getLogger(MetadataServiceMockImpl.class);
  private static final List<Metadata> META_DATA = new ArrayList<>();
  static {
    for (int i = 1; i <= 800; i++) {
      META_DATA.add(new Metadata(i, UUID.randomUUID().toString(), getTaxon(i), getRecorder(i),
          getDeterminer(i), getDate(i), getScans(i)));
    }
  }

  private Werbeo werbeo;

  public MetadataServiceMockImpl() {
    werbeo = new Werbeo("http://api.test.infinitenature.org");
  }

  @Override
  public List<Metadata> find(SortField sortField, SortOrder sortOrder, int limit, int offset,
      MetadataFilter metadataFilter) {
    LOGGER.info("Filter: {}, SortField: {}, SortOrder: {}, limit: {}, offset: {}", metadataFilter,
        sortField, sortOrder, limit, offset);
    Stream<Metadata> filteredStream = createFilteredStream(metadataFilter);
    Stream<Metadata> sortedSteam = filteredStream.sorted(createComparator(sortField, sortOrder));
    return sortedSteam.skip(offset).limit(limit).collect(Collectors.toList());
  }

  private Comparator<Metadata> createComparator(SortField sortField, SortOrder sortOrder) {
    Comparator<Metadata> comparator;
    switch (sortField) {
      case DATE:
        comparator = Comparator.comparing(Metadata::getDate);
        break;
      case DETERMINER:
        comparator = Comparator.comparing(Metadata::getDeterminer);
        break;
      case RECORDER:
        comparator = Comparator.comparing(Metadata::getRecorder);
        break;
      case TAXON:
      default:
        comparator = Comparator.comparing(Metadata::getTaxonName);
        break;
    }
    if (SortOrder.DESC == sortOrder) {
      comparator = comparator.reversed();
    }
    return comparator;
  }

  @Override
  public long count(MetadataFilter metadataFilter) {
    LOGGER.info("Filter: {}", metadataFilter);
    return createFilteredStream(metadataFilter).count();
  }

  private Stream<Metadata> createFilteredStream(MetadataFilter filter) {
    Stream<Metadata> stream = META_DATA.stream();
    if (StringUtils.isNotBlank(filter.getTaxon())) {
      stream = stream.filter(
          metadata -> StringUtils.containsIgnoreCase(metadata.getTaxonName(), filter.getTaxon()));
    }
    if (StringUtils.isNotBlank(filter.getDeterminer())) {
      stream =
          stream.filter(metadata -> matchesName(metadata.getDeterminer(), filter.getDeterminer()));
    }
    if (StringUtils.isNotBlank(filter.getRecorder())) {
      stream = stream.filter(metadata -> matchesName(metadata.getRecorder(), filter.getRecorder()));
    }
    if (filter.getFrom() != null) {
      stream = stream.filter(metadata -> metadata.getDate().equals(filter.getFrom())
          || metadata.getDate().isAfter(filter.getFrom()));
    }
    if (filter.getTo() != null) {
      stream = stream.filter(metadata -> metadata.getDate().equals(filter.getTo())
          || metadata.getDate().isBefore(filter.getTo()));
    }
    return stream;
  }

  private boolean matchesName(Person person, String name) {
    return StringUtils.containsIgnoreCase(person.getFirstName(), name)
        || StringUtils.containsIgnoreCase(person.getLastName(), name);
  }

  private static Set<Scan> getScans(int i) {
    Set<Scan> scans = new TreeSet<>();
    scans.add(new Scan(100 + 1, "name-" + i));
    return scans;
  }

  private static LocalDate getDate(int i) {
    return LocalDate.of(1900 + (i % 50), (i % 12) + 1, (i % 28) + 1);
  }

  private static Person getRecorder(int i) {
    return new Person("FirstName-" + i, "LastName-" + i);
  }

  private static Person getDeterminer(int i) {
    return new Person("FirstName-" + i, "LastName-" + i);
  }


  private static String getTaxon(int i) {
    return "Taxon-" + i;
  }

}

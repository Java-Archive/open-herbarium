package org.openherbarium.module.backend.metadataservice.mock.client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.openherbarium.module.api.HasLogger;
import org.openherbarium.module.api.config.Configuration;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.Person;
import org.openherbarium.module.backend.metadataservice.api.Scan;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.openherbarium.module.backend.metadataservice.mock.api.MetadataServiceMOCK;

/**
 *
 */
public class MetadataServiceMOCKClient implements MetadataServiceMOCK, HasLogger {
    private static final String[] FIRST_NAMES = new String[]{"Sven", "Daniel", "Hendrik", "Horst", "Marco", "Markus", "Stefan", "Lukas"};
    private static final String[] LAST_NAMES = new String[]{"Müller", "Meier", "Knutsen", "Patton", "Leber", "Schuhmacher"};
    private static final String[] TAXON_NAMES_FIRST_PARTS = new String[]{"Carex", "Bartos", "Cranel"};
    private static final String[] TAXON_NAMES_SECOND_PARTS = new String[]{"Finea", "Rudea", "Flavella", "Bohemica"};
    private static final char[] LETTERS = new char[]{'A', 'B', 'C', 'D', 'E'};

  private static final List<Metadata> MOCK_DATA = Collections.unmodifiableList(createMockData(1000));
    @Inject
    private Configuration configuration;

    @PostConstruct
    public void init() {
    }

    private String targetURL() {
        final String targetURL = configuration.getMetaServiceUrl() + PATH_BASE + "/" + MetadataServiceMOCK.PATH_METHODE_FIND;
        logger().debug(" MetadataServiceMOCKClient - target URL " + targetURL);

        System.out.println("targetURL = " + targetURL);
        return targetURL;
    }

    @Override
    public long count(String taxon,
                      String determiner, String recorder) {
        return getMetadataStreamFiltered(determiner, recorder, taxon).count();
    }

    private Stream<Metadata> getMetadataStreamFiltered(String determiner, String recorder, String taxon) {
        return MOCK_DATA.stream().filter(metadata -> filter(metadata.getDeterminer(), determiner)).
                filter(metadata -> filter(metadata.getRecorder(), recorder)).filter(metadata -> filter(metadata.getTaxonName(), taxon));
    }

    public boolean filter(String taxon, String taxonFilter) {
        return (StringUtils.isNotBlank(taxonFilter) && (StringUtils.containsIgnoreCase(taxon, taxonFilter)));
    }

    public boolean filter(Person person, String nameFilter) {
        return (StringUtils.isNotBlank(nameFilter)
                && (StringUtils.containsIgnoreCase(person.getFirstName(),
                nameFilter))
                || StringUtils.containsIgnoreCase(person.getLastName(),
                nameFilter));
    }

    @Override
  public List<Metadata> find(String sortField, SortOrder sortOrder, int limit, int offset,
      String taxon, String determiner,
      String recorder) {
        return getMetadataStreamFiltered(determiner, recorder, taxon).skip(offset).limit(limit).collect(Collectors.toList());
    }

    private static List<Metadata> createMockData(int limit) {
      final List<Metadata> metadataList = new ArrayList<>();
      for (int i = 0; i < limit; i++) {
          final Random random = new Random();
          final Metadata metadata = new Metadata();
          final int daysOffset = random.nextInt(2000);
          if (i % 2 == 0) {
              metadata.setDate(LocalDate.now().minusDays(daysOffset));
          } else {
              metadata.setDate(LocalDate.now().plusDays(daysOffset));
          }
          metadata.setTaxonName(TAXON_NAMES_FIRST_PARTS[random.nextInt(TAXON_NAMES_FIRST_PARTS.length - 1) + 1] + " " + TAXON_NAMES_SECOND_PARTS[random.nextInt(TAXON_NAMES_SECOND_PARTS.length - 1) + 1]);
          final Person determiner = new Person();
          determiner.setFirstName(FIRST_NAMES[random.nextInt(FIRST_NAMES.length)]);
          determiner.setLastName(LAST_NAMES[random.nextInt(LAST_NAMES.length)]);
          metadata.setDeterminer(determiner);
          final Person recorder = new Person();
          recorder.setFirstName(FIRST_NAMES[random.nextInt(FIRST_NAMES.length - 1) + 1]);
          recorder.setLastName(LAST_NAMES[random.nextInt(LAST_NAMES.length - 1) + 1]);
          metadata.setRecorder(recorder);
          metadata.setExternalId("externe ID");
          metadata.setId(random.nextInt(1000) - 1);
          final int scanNumber = random.nextInt(3) + 1;
          final Set<Scan> scanSet = new HashSet<>();
          for (int scan = 0; scan < scanNumber; scan++) {
              final int scanID = random.nextInt(5000) + 1;
              final Scan oneScan = new Scan();
              oneScan.setId(scanID);
              oneScan.setName(scanID + "_" + LETTERS[scan]);
              scanSet.add(oneScan);
          }
          metadata.setScans(scanSet);
          metadataList.add(metadata);
      }
      return metadataList;
    }

    public static class MOCKClientConfiguration implements Configuration {

        @Override
        public String getMetaServiceUrl() {
            return "http://" + "" + ":" + "" + "/mock";
        }

        @Override
        public String toString() {
            return getMetaServiceUrl();
        }
    }


}

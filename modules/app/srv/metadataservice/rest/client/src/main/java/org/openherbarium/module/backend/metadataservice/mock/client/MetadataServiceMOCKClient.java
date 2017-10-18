package org.openherbarium.module.backend.metadataservice.mock.client;

import org.openherbarium.module.api.HasLogger;
import org.openherbarium.module.api.config.Configuration;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.MetadataFilter;
import org.openherbarium.module.backend.metadataservice.api.Person;
import org.openherbarium.module.backend.metadataservice.api.Scan;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.openherbarium.module.backend.metadataservice.mock.api.MetadataServiceMOCK;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 */
public class MetadataServiceMOCKClient implements MetadataServiceMOCK, HasLogger {

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
    public long count(MetadataFilter filter) {
      // TODO Auto-generated method stub
      return 0;
    }
    
    @Override
    public List<Metadata> find(String sortField, SortOrder sortOrder, int limit, int offset, MetadataFilter filter) {
        return new ArrayList<>(MOCK_DATA);
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
          metadata.setTaxonName(taxonNameFirstParts[random.nextInt(taxonNameFirstParts.length - 1) + 1] + " " + taxonNameSecondParts[random.nextInt(taxonNameSecondParts.length - 1) + 1]);
          final Person determiner = new Person();
          determiner.setFirstName(firstNames[random.nextInt(firstNames.length)]);
          determiner.setLastName(lastNames[random.nextInt(lastNames.length)]);
          metadata.setDeterminer(determiner);
          final Person recorder = new Person();
          recorder.setFirstName(firstNames[random.nextInt(firstNames.length - 1) + 1]);
          recorder.setLastName(lastNames[random.nextInt(lastNames.length - 1) + 1]);
          metadata.setRecorder(recorder);
          metadata.setExternalId("externe ID");
          metadata.setId(random.nextInt(1000) - 1);
          final int scanNumber = random.nextInt(3) + 1;
          final Set<Scan> scanSet = new HashSet<>();
          for (int scan = 0; scan < scanNumber; scan++) {
              final int scanID = random.nextInt(5000) + 1;
              final Scan oneScan = new Scan();
              oneScan.setId(scanID);
              oneScan.setName(scanID + "_" + letters[scan]);
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

    private static final String[] firstNames = new String[]{"Sven", "Daniel", "Hendrik", "Horst", "Marco", "Markus", "Stefan", "Lukas"};
    private static final String[] lastNames = new String[]{"Müller", "Meier", "Knutsen", "Patton", "Leber", "Schuhmacher"};
    private static final String[] taxonNameFirstParts = new String[]{"Carex", "Bartos", "Cranel"};
    private static final String[] taxonNameSecondParts = new String[]{"Finea", "Rudea", "Flavella", "Bohemica"};
    private static final char[] letters = new char[]{'A', 'B', 'C', 'D', 'E'};
    
}

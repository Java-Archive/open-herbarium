package org.openherbarium.module.backend.metadataservice.mock.client;

import org.openherbarium.module.api.HasLogger;
import org.openherbarium.module.api.config.Configuration;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.Person;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.openherbarium.module.backend.metadataservice.mock.api.MetadataServiceMOCK;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 *
 */
public class MetadataServiceMOCKClient implements MetadataServiceMOCK, HasLogger {

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
    public List<Metadata> find(String sortField, SortOrder sortOrder, int limit, int offset) {
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
            metadata.setScans(new HashSet<>());
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

    private final String[] firstNames = new String[]{"Sven", "Daniel", "Hendrik", "Horst", "Marco", "Markus", "Stefan", "Lukas"};
    private final String[] lastNames = new String[]{"Müller", "Meier", "Knutsen", "Patton", "Leber", "Schuhmacher"};
    private final String[] taxonNameFirstParts = new String[]{"Carex", "Bartos", "Cranel"};
    private final String[] taxonNameSecondParts = new String[]{"Finea", "Rudea", "Flavella", "Bohemica"};
}

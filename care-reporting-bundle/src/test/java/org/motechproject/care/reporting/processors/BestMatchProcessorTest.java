package org.motechproject.care.reporting.processors;

import org.junit.Test;
import org.motechproject.care.reporting.model.MappingEntity;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;

public class BestMatchProcessorTest {

    BestMatchProcessor processor;

    @Test
    public void testGetBestMatch() throws Exception {

        MappingEntity allMatch = new MappingEntity("id", "ver", "seg");
        MappingEntity segmentPartialMatch = new MappingEntity("id", "ver", "*");
        MappingEntity versionPartialMatch = new MappingEntity("id", "*", "seg");
        MappingEntity idPartialMatch = new MappingEntity("*", "ver", "seg");
        MappingEntity idAndVersionPartialMatch = new MappingEntity("*", "*", "seg");
        MappingEntity idAndSegmentPartialMatch = new MappingEntity("*", "ver", "*");
        MappingEntity versionAndSegmentPartialMatch = new MappingEntity("id", "*", "*");
        MappingEntity allPartialMatch = new MappingEntity("*", "*", "*");
        List<MappingEntity> mappingEntities = asList(allMatch, segmentPartialMatch, versionPartialMatch, idPartialMatch, idAndVersionPartialMatch, idAndSegmentPartialMatch, versionAndSegmentPartialMatch, allPartialMatch);

        processor = new BestMatchProcessor(mappingEntities);

        assertEquals(allMatch, processor.getBestMatch("id", "ver", "seg"));
        assertEquals(segmentPartialMatch, processor.getBestMatch("id", "ver"));
        assertEquals(versionPartialMatch, processor.getBestMatch("id", "non_version", "seg"));
        assertEquals(idPartialMatch, processor.getBestMatch("non_id", "ver", "seg"));
        assertEquals(idAndVersionPartialMatch, processor.getBestMatch("non_id", "non_version", "seg"));
        assertEquals(idAndSegmentPartialMatch, processor.getBestMatch("non_id", "ver", "non_seg"));
        assertEquals(versionAndSegmentPartialMatch, processor.getBestMatch("id", "non_version", "non_seg"));
        assertEquals(allPartialMatch, processor.getBestMatch("non_id", "non_version", "non_seg"));
    }

}

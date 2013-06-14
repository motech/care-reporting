package org.motechproject.care.reporting.processors;


import org.motechproject.care.reporting.model.MappingEntity;

import java.util.List;

public class BestMatchProcessor {

    private final String WILD_CARD = "*";

    private final int IDENTIFIER_WEIGHT = 5;
    private final int SEGMENT_WEIGHT = 3;
    private final int VERSION_WEIGHT = 1;
    private final int MISMATCH_WEIGHT = -100;
    private final int ACTUAL_MATCH_RATIO = 2;
    private final int BEST_EXACT_MATCH_WEIGHT = getBestExactMatchWeight();

    private List<MappingEntity> mappingEntities;

    public BestMatchProcessor(List<MappingEntity> mappingEntities) {
        this.mappingEntities = mappingEntities;
    }

    public MappingEntity getBestMatch(String identifier, String version, String segmentName) {

        int bestMatchWeight = 0;

        MappingEntity bestMatchEntity = new MappingEntity();

        for (MappingEntity mappingEntity : mappingEntities) {
            int currentWeight = getWeight(mappingEntity, identifier, version, segmentName);

            if (currentWeight == BEST_EXACT_MATCH_WEIGHT)
                return mappingEntity;

            if (currentWeight > bestMatchWeight) {
                bestMatchWeight = currentWeight;
                bestMatchEntity = mappingEntity;
            }
        }

        return bestMatchEntity;
    }

    private int getWeight(MappingEntity mappingEntity, String identifier, String version, String segmentName) {

        int weight = 0;

        weight += getWeight(mappingEntity.getIdentifier(), identifier, IDENTIFIER_WEIGHT);
        weight += getWeight(mappingEntity.getSegment(), segmentName, SEGMENT_WEIGHT);
        weight += getWeight(mappingEntity.getVersion(), version, VERSION_WEIGHT);

        return weight;
    }

    private int getWeight(String actualValue, String matchingValue, int wildCardWeight) {

        int weight = 0;

        if (actualValue.equals(matchingValue))
            weight += (wildCardWeight * ACTUAL_MATCH_RATIO);
        else if (actualValue.equals(WILD_CARD))
            weight += wildCardWeight;
        else weight += MISMATCH_WEIGHT;

        return weight;
    }

    public MappingEntity getBestMatch(String identifier, String version) {
        return getBestMatch(identifier, version, null);
    }

    public int getBestExactMatchWeight() {
        return (IDENTIFIER_WEIGHT + SEGMENT_WEIGHT + VERSION_WEIGHT) * ACTUAL_MATCH_RATIO;
    }
}

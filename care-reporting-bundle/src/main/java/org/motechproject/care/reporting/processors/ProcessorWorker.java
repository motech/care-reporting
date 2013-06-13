package org.motechproject.care.reporting.processors;

import org.apache.commons.lang.StringUtils;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.service.MapperService;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.care.reporting.utils.ObjectUtils;

public class ProcessorWorker {
    protected Service service;
    protected MapperService mapperService;

    public ProcessorWorker(Service service, MapperService mapperService) {
        this.service = service;
        this.mapperService = mapperService;
    }

    protected void setFlwGroup(String ownerId, Object object) {
        if (StringUtils.isEmpty(ownerId)) {
            return;
        }

        FlwGroup motherCase = service.getGroup(ownerId);
        ObjectUtils.set(object, "flwGroup", motherCase);
    }

    protected void setMotherCase(String caseId, Object object) {
        if (StringUtils.isEmpty(caseId)) {
            return;
        }

        MotherCase motherCase = service.getMotherCase(caseId);
        ObjectUtils.set(object, "motherCase", motherCase);
    }

    protected void setChildCase(String caseId, Object object) {
        if (StringUtils.isEmpty(caseId)) {
            return;
        }

        ChildCase childCase = service.getChildCase(caseId);
        ObjectUtils.set(object, "childCase", childCase);
    }

    protected void setFlw(String flwId, Object object) {
        if (StringUtils.isEmpty(flwId)) {
            return;
        }

        Flw flw = service.getFlw(flwId);
        ObjectUtils.set(object, "flw", flw);
    }
}

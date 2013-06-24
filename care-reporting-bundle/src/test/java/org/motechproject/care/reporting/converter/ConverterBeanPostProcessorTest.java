package org.motechproject.care.reporting.converter;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;

import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionEqualsWithIgnore;

public class ConverterBeanPostProcessorTest extends SpringIntegrationTest {
    @Test
    public void shouldAutoConvertToFlw() throws Exception {
        Flw flw = new Flw();
        flw.setFlwId("89fda0284e008d2e0c980fb13f96c45a");
        template.save(flw);
        ConverterDemoObject target = new ConverterDemoObject();

        BeanUtils.setProperty(target, "flw", "89fda0284e008d2e0c980fb13f96c45a");

        assertReflectionEqualsWithIgnore(flw, target.flw);
    }

    @Test
    public void shouldAutoConvertToMotherCase() throws Exception {
        MotherCase motherCase = new MotherCase();
        motherCase.setCaseId("94d5374f-290e-409f-bc57-86c2e4bcc43f");
        template.save(motherCase);
        ConverterDemoObject object = new ConverterDemoObject();

        BeanUtils.setProperty(object, "motherCase", "94d5374f-290e-409f-bc57-86c2e4bcc43f");

        assertReflectionEqualsWithIgnore(motherCase, object.motherCase);
    }

    static class ConverterDemoObject {
        Flw flw;
        MotherCase motherCase;
    }
}

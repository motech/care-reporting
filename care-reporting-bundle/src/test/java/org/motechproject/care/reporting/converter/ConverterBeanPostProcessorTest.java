package org.motechproject.care.reporting.converter;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;

import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionEqualsWithIgnore;

public class ConverterBeanPostProcessorTest extends SpringIntegrationTest {
    @Test
    public void shouldAutoConvertToFlw() throws Exception {
        Flw flw = new Flw();
        flw.setFlwId("89fda0284e008d2e0c980fb13f96c45a");
        template.save(flw);

        FlwITObject target = new FlwITObject();

        BeanUtils.setProperty(target, "flw", "89fda0284e008d2e0c980fb13f96c45a");

        assertReflectionEqualsWithIgnore(flw, target.flw);
    }

    static class FlwITObject {
        Flw flw;
    }
}

package org.motechproject.care.reporting.migration.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.care.reporting.migration.utils.FileUtils;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class MigrationTaskTest {

    private MigrationTask migrationTask;

    @Mock
    private MigrationBatchProcessor migrationBatchProcessor;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        migrationTask = new MigrationTask(migrationBatchProcessor) {
            @Override
            public void migrate(String id) {

            }
        };
    }

    @Test
    public void shouldReadFileForIdsAndMigrateInBatch() {
        File file = FileUtils.createTempFileWithContent("\n  \n1 23  \n   \n  45   5 \n\n");

        migrationTask.migrate(file);

        ArgumentCaptor<List> idListCaptor = ArgumentCaptor.forClass(List.class);
        verify(migrationBatchProcessor).processInBatch(eq(migrationTask), idListCaptor.capture());

        List actualIdList = idListCaptor.getValue();
        assertEquals(2, actualIdList.size());
        assertEquals("1 23", actualIdList.get(0));
        assertEquals("45   5", actualIdList.get(1));
    }

}

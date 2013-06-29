package org.motechproject.care.reporting.migration.service;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.care.reporting.migration.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
        when(migrationBatchProcessor.processInBatch(eq(migrationTask), anyList())).thenReturn(HashMultimap.<String, String>create());
        boolean success = migrationTask.migrate(file);
        assertTrue(success);

        ArgumentCaptor<List> idListCaptor = ArgumentCaptor.forClass(List.class);
        verify(migrationBatchProcessor).processInBatch(eq(migrationTask), idListCaptor.capture());

        List actualIdList = idListCaptor.getValue();
        assertEquals(2, actualIdList.size());
        assertEquals("1 23", actualIdList.get(0));
        assertEquals("45   5", actualIdList.get(1));
    }

    @Test
    public void shouldWriteErrorIdsInAFile() throws IOException {
        File file = FileUtils.createTempFileWithContent("\n  \n1 23  \n   \n  45   5 \n\n666");
        Multimap<String, String> errorFileMap = LinkedHashMultimap.create();
        errorFileMap.put("error", "1 23");
        errorFileMap.put("error", "45");
        errorFileMap.put("404", "666");
        when(migrationBatchProcessor.processInBatch(any(MigrationTask.class), any(List.class))).thenReturn(errorFileMap);

        boolean success = migrationTask.migrate(file);

        assertFalse(success);

        File fileForError = new File(file.getAbsolutePath() + "_error");
        assertTrue(fileForError.exists());
        fileForError.deleteOnExit();

        File fileFor404 = new File(file.getAbsolutePath() + "_404");
        assertTrue(fileFor404.exists());
        fileFor404.deleteOnExit();

        List<String> actualFailedIdsForError = org.apache.commons.io.FileUtils.readLines(fileForError);
        assertEquals(2, actualFailedIdsForError.size());
        assertEquals("1 23", actualFailedIdsForError.get(0));
        assertEquals("45", actualFailedIdsForError.get(1));

        List<String> actualFailedIdsFor404 = org.apache.commons.io.FileUtils.readLines(fileFor404);
        assertEquals(1, actualFailedIdsFor404.size());
        assertEquals("666", actualFailedIdsFor404.get(0));
    }

}

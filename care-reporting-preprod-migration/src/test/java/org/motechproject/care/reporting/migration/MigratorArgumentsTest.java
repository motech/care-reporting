package org.motechproject.care.reporting.migration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.motechproject.care.reporting.migration.utils.FileUtils;

import java.io.File;
import java.io.IOException;


public class MigratorArgumentsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldValidateArguments() throws IOException {
        String fileName = "care_form_test_file";
        File tempFile = FileUtils.createTempFile(fileName);
        String absolutePath = tempFile.getAbsolutePath();

        new MigratorArguments(new String[]{"form", absolutePath}).validate();
    }

    @Test
    public void shouldValidateIfNumberOfArgumentsIsMoreThanTwo() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Only two arguments are allowed");
        new MigratorArguments(new String[]{"arg1", "arg2", "arg3"}).validate();
    }


    @Test
    public void shouldValidateIfNumberOfArgumentsIsLessThanTwo() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Only two arguments are allowed");
        new MigratorArguments(new String[]{"arg1"}).validate();
    }

    @Test
    public void shouldValidateIfMigrationTypeIsCorrect() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid migration type somemigrationType");
        new MigratorArguments(new String[]{"somemigrationType", "anyfile"}).validate();
    }

    @Test
    public void shouldValidateIfFileDoesNotExist() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("File anyfile does not exist");
        new MigratorArguments(new String[]{"form", "anyfile"}).validate();
    }

    @Test
    public void shouldValidateIfFileGivenIsNotAValidFile() {
        expectedException.expect(IllegalArgumentException.class);
        File tempDirectory = org.apache.commons.io.FileUtils.getTempDirectory();
        expectedException.expectMessage(String.format("%s is not a valid file", tempDirectory.getPath()));
        new MigratorArguments(new String[]{"form",tempDirectory.getPath()}).validate();
    }

}

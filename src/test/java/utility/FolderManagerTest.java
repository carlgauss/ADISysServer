package utility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class FolderManagerTest {

    String folderName;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        folderName = null;
    }

    @Test
    public void testCreateFolderIfNotExists() throws Exception {
        folderName = "folderNameTest";
        try {
            FolderManager.createFolderIfNotExists(folderName);
        } finally {
            File file = new File(folderName);
            assertThat(file.delete(), equalTo(true));
        }
    }

    //@Test(expected = FolderAlreadyExistsException.class)
    public void testCreateFolderIfNotExistsAndThenTryToCreateAgain() throws Exception {
        folderName = "AnotherFolderNameTest";
        try {
            FolderManager.createFolderIfNotExists(folderName);
            FolderManager.createFolderIfNotExists(folderName);
        } finally {
            File file = new File(folderName);
            assertThat(file.delete(), equalTo(true));
        }
    }


}
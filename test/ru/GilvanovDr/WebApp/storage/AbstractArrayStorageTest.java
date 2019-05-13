package ru.GilvanovDr.WebApp.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;

public class AbstractArrayStorageTest extends AbstractStorageTest {
    private Storage storage;

    AbstractArrayStorageTest(Storage storage) {
        super(storage);
        this.storage = storage;
    }

    @Test(expected = StorageException.class)
    public void overFlow() {
        int range = AbstractArrayStorage.STORAGE_SIZE;
        try {
            for (int i = 4; i <= range; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }
}

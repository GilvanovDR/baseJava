/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    void clear() {
        for (int i=0; i<size();i++) storage[i] = null;
    }

    void save(Resume r) {
        try{
            storage[size()] = r;
        } catch (java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("Array is full, you need delete one or more Resume");
        }


    }

    Resume get(String uuid) {
        Resume resume = new Resume();
        for (int i=0; i<size(); i++) {
            if (storage[i].uuid == uuid){
                resume = storage[i];
                break;
            }
        }
        return resume;
    }

    void delete(String uuid) {
        for (int i=0; i<size(); i++)
            if (storage[i].uuid == uuid)
            {
                System.arraycopy(storage,i+1,storage,i,size());
                break;
            }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] storageWoNull = new Resume[size()];
        System.arraycopy(storage,0,storageWoNull,0,size()); //for (int i=0;i<size();i++) storage[i]=storageWoNull[i];
        return storageWoNull;
    }

    int size() {
        int i =0;
        for (;i<10000;i++ ) if (storage[i] == null) break;
        return i;
    }
}

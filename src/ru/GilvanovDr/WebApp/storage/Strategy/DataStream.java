/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp.storage.Strategy;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.ContactType;
import ru.GilvanovDr.WebApp.model.Resume;
import ru.GilvanovDr.WebApp.model.Section;
import ru.GilvanovDr.WebApp.model.SectionType;

import java.io.*;
import java.util.Map;


public class DataStream implements SerializationStrategy {
    @Override
    public void doWrite(Resume r, OutputStream oi) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(oi)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType,String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType,Section> sections = r.getSections();
            dos.writeInt(sections.size());
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        Resume resume;
        try (DataInputStream dis = new DataInputStream(is)) {
            resume = new Resume(dis.readUTF(), dis.readUTF());
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                ContactType contactType = ContactType.valueOf(dis.readUTF());
                String contact = dis.readUTF();
                resume.addContact(contactType,contact);
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {

            }
        }
        return resume;
    }
}
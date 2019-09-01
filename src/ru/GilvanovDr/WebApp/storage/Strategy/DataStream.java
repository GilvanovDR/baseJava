/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp.storage.Strategy;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DataStream implements SerializationStrategy {
    @Override
    public void doWrite(Resume r, OutputStream oi) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(oi)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> section : sections.entrySet()) {
                dos.writeUTF(section.getKey().name());
                dos.writeUTF(section.getValue().getClass().getCanonicalName());
                switch (section.getValue().getClass().getCanonicalName()) {
                    case "ru.GilvanovDr.WebApp.model.TextSection":
                        dos.writeUTF(section.getValue().toString());
                        break;

                    case "ru.GilvanovDr.WebApp.model.ListSection":
                        List<String> listItems = ((ListSection) section.getValue()).getItems();
                        dos.writeInt(listItems.size());
                        for (String s : listItems) {
                            dos.writeUTF(s);
                        }
                        break;

                    case "ru.GilvanovDr.WebApp.model.OrganizationSection":
                        List<Organization> listOrganizations = ((OrganizationSection) section.getValue()).getOrganizations();
                        dos.writeInt(listOrganizations.size());
                        for (Organization organization : listOrganizations) {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl());
                            List<Organization.Position> positions = organization.getPositions();
                            dos.writeInt(positions.size());
                            for (Organization.Position position : positions) {
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getDescription());
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        Resume resume;
        try (DataInputStream dis = new DataInputStream(is)) {
            resume = new Resume(dis.readUTF(), dis.readUTF());
            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                ContactType contactType = ContactType.valueOf(dis.readUTF());
                String contact = dis.readUTF();
                resume.addContact(contactType, contact);
            }
            sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                Section section = null;
                switch (dis.readUTF()) {
                    case "ru.GilvanovDr.WebApp.model.TextSection":
                        section = new TextSection(dis.readUTF());
                        break;

                    case "ru.GilvanovDr.WebApp.model.ListSection":
                        List<String> list = new ArrayList<>();
                        int listSize = dis.readInt();
                        for (int j = 0; j < listSize; j++) {
                            list.add(dis.readUTF());
                        }
                        section = new ListSection(list);
                        break;

                    case "ru.GilvanovDr.WebApp.model.OrganizationSection":
                        List<Organization> organizations = new ArrayList<>();
                        int organizationsSize = dis.readInt();
                        for (int j = 0; j < organizationsSize; j++) {
                            String orgName = dis.readUTF();
                            String orgUrl = dis.readUTF();
                            int positionSize = dis.readInt();
                            List<Organization.Position> positions = new ArrayList<>();
                            for (int k = 0; k < positionSize; k++) {
                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                LocalDate endDate = LocalDate.parse(dis.readUTF());
                                positions.add(new Organization.Position(startDate.getYear(), startDate.getMonth(), endDate.getYear(), endDate.getMonth(), dis.readUTF(), dis.readUTF()));
                            }
                            Organization organization = new Organization(new Link(orgName, orgUrl), positions);
                            organizations.add(organization);
                        }
                        section = new OrganizationSection(organizations);
                        break;
                }
                resume.addSection(sectionType, section);
            }
        }
        return resume;
    }
}
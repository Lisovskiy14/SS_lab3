package project.replacement.impl;

import project.physical.PhysicalMemory;
import project.physical.PhysicalPage;
import project.replacement.ReplacementAlgorithm;
import project.virtual.PageTableEntry;

import java.util.Random;

public class RandomAlgorithm implements ReplacementAlgorithm {
    private final Random random = new Random();

    @Override
    public void replace(PhysicalMemory physicalMemory, PageTableEntry entry) {
        int randomNumber = random.nextInt(physicalMemory.size());

        PhysicalPage page = physicalMemory.getPage(randomNumber);
        PageTableEntry prevEntry = page.getEntry();
        prevEntry.setPresence(false);
        prevEntry.setReferenced(false);
        prevEntry.setModified(false);

        entry.setPhysicalPageNumber(randomNumber);
        page.setEntry(entry);
    }
}

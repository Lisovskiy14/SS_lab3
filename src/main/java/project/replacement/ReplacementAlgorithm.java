package project.replacement;

import project.virtual.PageTableEntry;
import project.physical.PhysicalMemory;

public interface ReplacementAlgorithm {
    void replace(PhysicalMemory physicalMemory, PageTableEntry entry);
}

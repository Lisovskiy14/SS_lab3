package project;

import project.physical.PhysicalMemory;
import project.physical.PhysicalPage;
import project.replacement.impl.NRUAlgorithm;
import project.replacement.impl.RandomAlgorithm;
import project.util.DataUtil;
import project.virtual.PageTable;
import project.virtual.PageTableEntry;

public class Main {
    private static final int ALL_REF_NUMBER = 1000;

    public static void main(String[] args) {
        testRandomAlgorithm();
        Logger.reset();
        testNRUAlgorithm();
    }

    private static void testRandomAlgorithm() {
        Logger.filePath = "logRandom.txt";

        PhysicalMemory physicalMemory = new PhysicalMemory(new PhysicalPage[10]);
        Kernel kernel = new Kernel(physicalMemory, new RandomAlgorithm());
        MemoryRefreshChecker memoryRefreshChecker = new MemoryRefreshChecker(physicalMemory);

        PageTable pageTable = new PageTable(100);
        for (int i = 0; i < 100; i++) {
            pageTable.addEntry(new PageTableEntry(), i);
        }

        Process process = new Process(0, pageTable, kernel);

        for (int i = 0; i < ALL_REF_NUMBER; i++) {
            process.generateAccess();
            memoryRefreshChecker.checkForRefresh();
        }

        Logger.writeToFile(DataUtil.workingSetSize);
    }

    private static void testNRUAlgorithm() {
        Logger.filePath = "logNRU.txt";

        PhysicalMemory physicalMemory = new PhysicalMemory(new PhysicalPage[10]);
        Kernel kernel = new Kernel(physicalMemory, new NRUAlgorithm());
        MemoryRefreshChecker memoryRefreshChecker = new MemoryRefreshChecker(physicalMemory);

        PageTable pageTable = new PageTable(100);
        for (int i = 0; i < 100; i++) {
            pageTable.addEntry(new PageTableEntry(), i);
        }

        Process process = new Process(0, pageTable, kernel);

        for (int i = 0; i < ALL_REF_NUMBER; i++) {
            process.generateAccess();
            memoryRefreshChecker.checkForRefresh();
        }

        Logger.writeToFile(DataUtil.workingSetSize);
    }
}

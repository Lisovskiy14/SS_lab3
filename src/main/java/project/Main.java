package project;

import project.physical.PhysicalMemory;
import project.physical.PhysicalPage;
import project.replacement.impl.NRUAlgorithm;
import project.replacement.impl.RandomAlgorithm;
import project.util.DataUtil;
import project.virtual.PageTable;
import project.virtual.PageTableEntry;

public class Main {

    public static void main(String[] args) {
        testRandomAlgorithm();
        Logger.reset();
        testNRUAlgorithm();
    }

    private static void testRandomAlgorithm() {
        Logger.filePath = "logRandom.txt";

        PhysicalMemory physicalMemory = new PhysicalMemory(new PhysicalPage[DataUtil.RAM_SIZE]);
        Kernel kernel = new Kernel(physicalMemory, new RandomAlgorithm());
        MemoryRefreshChecker memoryRefreshChecker = new MemoryRefreshChecker(physicalMemory);

        PageTable pageTable = new PageTable(DataUtil.PAGE_TABLE_SIZE);
        for (int i = 0; i < DataUtil.PAGE_TABLE_SIZE; i++) {
            pageTable.addEntry(new PageTableEntry(), i);
        }

        Process process = new Process(0, pageTable, kernel);

        for (int i = 0; i < DataUtil.ALL_REF_NUMBER; i++) {
            process.generateAccess();
            memoryRefreshChecker.checkForRefresh();
        }

        Logger.writeToFile();
    }

    private static void testNRUAlgorithm() {
        Logger.filePath = "logNRU.txt";

        PhysicalMemory physicalMemory = new PhysicalMemory(new PhysicalPage[DataUtil.RAM_SIZE]);
        Kernel kernel = new Kernel(physicalMemory, new NRUAlgorithm());
        MemoryRefreshChecker memoryRefreshChecker = new MemoryRefreshChecker(physicalMemory);

        PageTable pageTable = new PageTable(DataUtil.PAGE_TABLE_SIZE);
        for (int i = 0; i < DataUtil.PAGE_TABLE_SIZE; i++) {
            pageTable.addEntry(new PageTableEntry(), i);
        }

        Process process = new Process(0, pageTable, kernel);

        for (int i = 0; i < DataUtil.ALL_REF_NUMBER; i++) {
            process.generateAccess();
            memoryRefreshChecker.checkForRefresh();
        }

        Logger.writeToFile();
    }
}

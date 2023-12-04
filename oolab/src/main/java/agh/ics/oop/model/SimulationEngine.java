package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SimulationEngine {
    private final List<Simulation> simulationList;
    private final List<Thread> simulationThreadsList;
    private ExecutorService simulationExecutor = null;
    public SimulationEngine(List<Simulation> simulations){
        simulationList = new ArrayList<>(simulations);
        simulationThreadsList = new ArrayList<>(simulationList.size());
    }

    public void runSync(){
        simulationList.forEach(Simulation::run);
    }

    public void runAsync()  {
        simulationList.forEach((simulation) -> {
            Thread thread = new Thread(simulation);
            thread.start();
            simulationThreadsList.add(thread);
        });
    }

    public void awaitSimulationsEnd() throws InterruptedException {
        for(Thread thread : simulationThreadsList){
            thread.join();
        }

        if(simulationExecutor != null) {
            simulationExecutor.shutdown();
            if (!simulationExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
                simulationExecutor.shutdownNow();
            }
        }
    }

    public void runAsyncInThreadPool() {
        simulationExecutor = Executors.newFixedThreadPool(4);
        simulationList.forEach((simulationExecutor::submit));
    }
}

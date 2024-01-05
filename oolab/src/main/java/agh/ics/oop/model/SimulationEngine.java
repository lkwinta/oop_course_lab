package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SimulationEngine {
    private final List<Simulation> simulationList;
    private final List<Thread> simulationThreadsList;
    private ExecutorService simulationExecutor = null;

    public SimulationEngine(){
        simulationList = new ArrayList<>();
        simulationThreadsList = new ArrayList<>();
        simulationExecutor = Executors.newFixedThreadPool(4);
    }

    public SimulationEngine(List<Simulation> simulations){
        simulationList = new ArrayList<>(simulations);
        simulationThreadsList = new ArrayList<>(simulationList.size());
        simulationExecutor = Executors.newFixedThreadPool(4);
    }

    public void runAllSync(){
        simulationList.forEach(Simulation::run);
    }

    public void runAllAsync()  {
        simulationList.forEach((simulation) -> {
            Thread thread = new Thread(simulation);
            thread.start();
            simulationThreadsList.add(thread);
        });
    }

    public void awaitAllSimulationsEnd() throws InterruptedException {
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

    public void runAllAsyncInThreadPool() {
        simulationList.forEach((simulationExecutor::submit));
    }

    public void runSingleAsyncInThreadPool(Simulation simulation) {
        simulationExecutor.submit(simulation);
    }

    public void runSingleAsync(Simulation simulation){
        Thread thread = new Thread(simulation);
        thread.start();
        simulationThreadsList.add(thread);
    }
}

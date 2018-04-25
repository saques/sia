package ar.edu.itba.sia.chainreaction.engine.frontier;

public class NonEmptyFrontierException extends RuntimeException{

    public NonEmptyFrontierException(){
        super("Frontier must be empty");
    }

}

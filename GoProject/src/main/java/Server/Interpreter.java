package Server;

public interface Interpreter {
    void execute(String command);
    void send(char color,int[][] fieldState);
    void send(char color,int putX,int putY);
}

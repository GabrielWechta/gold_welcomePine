package ProjectUtils;

public enum MessageType {
    KoException('k'),
    OutOfBoundsException('b'),
    SuicidalTurnException('s'),
    NotYourTurnException('n'),
    OccupiedPlaceException('o'),
    Turn('t'),
    QuitMessage('q'),
    GiveupMessage('g'),
    PassMessage('p'),
    InitializeMessage('i');
    public final char command;

    private MessageType(char command) {
        this.command = command;
    }

    char getCommand() {
        return this.command;
    }

    MessageType getType(char command) {
        switch (command) {
            case 'i':
                return MessageType.InitializeMessage;
            case 'k':
                return MessageType.KoException;

            case 'b':
                return MessageType.OutOfBoundsException;

            case 's':
                return MessageType.SuicidalTurnException;

            case 'n':
                return MessageType.NotYourTurnException;

            case 'o':
                return MessageType.OccupiedPlaceException;


            case 't':
                return MessageType.Turn;

            case 'q':
                return MessageType.QuitMessage;

            case 'g':
                return MessageType.GiveupMessage;

            case 'p':
                return MessageType.PassMessage;


            default :
                return MessageType.Turn;
        }
    }
}

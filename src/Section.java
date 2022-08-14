public class Section {
    //Este es la casilla

    private Section next;
    private int position;

    private Player player1;

    private Player player2;

    public Section(int position) {
        this.position=position;
    }

    public Section getNext() {
        return next;
    }

    public void setNext(Section next) {
        this.next = next;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    @Override
    public String toString(){
        if (player1==null && player2==null) {
            return "[" + position + "] ";
        } else if (player2==null) {
            return "["+player1.getName()+"] ";
        }else if (player1 == null) {
            return "["+player2.getName()+"] ";
        }else {
            return "["+player1.getName()+","+player2.getName()+"] ";
        }

    }
}

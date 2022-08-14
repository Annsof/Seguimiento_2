public class Board {
    private Section head;
    private Section tail;

    private final int rows;
    private final int columns;

    private int turn;

    private int diceValue;
    private boolean started;

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public Board(int rows, int columns){
        this.rows=rows;
        this.columns=columns;
        this.started=true;
    }
    public void create(String name1, String name2){
        int size = (rows*columns);
        fillBoard(size,0,null, 1);
        head.setPlayer1(new Player(name1));
        head.setPlayer2(new Player(name2));
    }
    private void fillBoard(int size, int index, Section current, int position){
        current =new Section(position);
        //caso base
        if(index==size){
            tail=current;
            return;
        }
        if(index==0 && head==null){
            head=current;
            tail=current;
        }
            tail.setNext(current);
            tail = current;
            fillBoard(size,index+1,current,position+1);

    }
    public String print(){
        return print(head,"",0);
    }
    //Metodo recursivo
    private String print(Section current, String hi, int index){
        //Caso base
        if(current==null){
            return hi;
        }
        if(index==columns){
            hi += "\n";
            index=0;
        }
        //Llamado recursivo
        hi += String.valueOf(current.toString());
        return print(current.getNext(),hi, index+1);
    }
    public String round(){
        String re = "";
        rollDice();
        Section founded = search(head);
        if(founded.getPosition()+diceValue>=(rows*columns)){
            started=false;
            if(founded.getPlayer1()!=null){
                re+="The player "+founded.getPlayer1().getName()+" has won the game!";
            }else{
                re+="The player "+founded.getPlayer2().getName()+" has won the game!";
            }
        }else {
            if (founded.getPosition() + diceValue > (rows * columns)) {
                diceValue = (rows * columns) - founded.getPosition();
            }
            re += move(founded, head, 1);
            turn = (turn + 1) % 2;
        }
        return re;
    }
    public String move(Section founded, Section current, int index){
        if(current.getNext()==null){

        }
        if (turn==0 && index==diceValue+founded.getPosition()){
            current.setPlayer1(founded.getPlayer1());
            founded.setPlayer1(null);
            return "The player "+current.getPlayer1().getName()+" rolled the dice and got a "+diceValue+", the player has moved to the position " +current.getPosition();
        }else if (turn==1 && index==diceValue+founded.getPosition()){
            current.setPlayer2(founded.getPlayer2());
            founded.setPlayer2(null);
            return "The player "+current.getPlayer2().getName()+" rolled the dice and got a "+diceValue+", the player has moved to the position " +current.getPosition();
        }
        return move(founded,current.getNext(),index+1);
    }
    public void rollDice(){
        diceValue = (int)((Math.random() * (6-1))+1);
    }
    public Section search( Section current){
        if (turn==0){
            if(current.getPlayer1()!=null){
                return current;
            }
        }else{
            if(current.getPlayer2()!=null){
                return current;
            }
        }
        return search(current.getNext());
    }
}
